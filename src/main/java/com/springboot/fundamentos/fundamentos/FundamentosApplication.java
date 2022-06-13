package com.springboot.fundamentos.fundamentos;

import com.springboot.fundamentos.fundamentos.bean.MyBean;
import com.springboot.fundamentos.fundamentos.bean.MyBeanWithDependency;
import com.springboot.fundamentos.fundamentos.bean.MyBeanWithProperties;
import com.springboot.fundamentos.fundamentos.component.ComponentDependency;
import com.springboot.fundamentos.fundamentos.entity.User;
import com.springboot.fundamentos.fundamentos.pojo.UserPojo;
import com.springboot.fundamentos.fundamentos.repository.UserRepository;
import com.springboot.fundamentos.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private Log LOGGERNombre = LogFactory.getLog(FundamentosApplication.class);

	//aca se agregan las interfaces
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;


	//inyectamos dependencias
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties= myBeanWithProperties;
		this.userPojo= userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	//implementamos
	@Override
	public void run(String... args) {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	// Se crea la dependencia "private UserService userService;"
	// y se llama como atributo en el constructor y luego dentro
	// del constructor llamamos la propiedad
	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try{
			userService.saveTransactional(users);
		} catch (Exception e){
			LOGGERNombre.error("Esto es una excepcion dentro del metodo transaccional" + e);
		}
		userService.getAllUsers().stream()
				.forEach(user ->
						LOGGERNombre.info("Este es el usuario transaccional" + user));

	}

	private void getInformationJpqlFromUser(){
		/*LOGGERNombre.info("Usuario con el metodo findByUserEmail" +
				userRepository.findByUserEmail("julie@domain.com").
				orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGERNombre.info("Usuario con metodo sort" + user));

		userRepository.findByName("John")
				.stream()	//para mostrar datos
				.forEach(user -> LOGGERNombre.info("Usuario con Query Method" + user));	//por cada uno de John se mostrara

		//En caso de no encontrarse, se pone el orElseThrow con una landa(->)
		LOGGERNombre.info("Usuario con Query Method findEmailAndName" + userRepository.findByEmailAndName("daniela@domain.com", "Daniela").
				orElseThrow(()-> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%user%")
				.stream()
				.forEach(user -> LOGGERNombre.info("Usuario findByNameLike " + user));

		userRepository.findByNameOrEmail(null, "user9@domain.com")
				.stream()
				.forEach(user -> LOGGERNombre.info("Usuario findByNameOrEmail " + user));
		*/

		userRepository.findByBirthdateBetween(LocalDate.of(2016, 3, 1), LocalDate.of(2020, 3, 1))
				.stream()
				.forEach(user -> LOGGERNombre.info("Usuario con intervalo de fechas " + user));

		userRepository.findByNameLikeOrderByIdDesc("%user%")
				.stream()
				.forEach(user -> LOGGERNombre.info("Usuario encontrado con like y ordenado" + user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGERNombre.info("Usuario encontrado con containing" + user));

		LOGGERNombre.info("El usuario a partir del named parameter es " + userRepository.getAllByBirthdateAndEmail(LocalDate.of
								(2013, 03, 3),"daniela@domain.com")
				.orElseThrow(() ->
						new RuntimeException("No encontro el usuario a partir del Named Parameter")));


	}

	private void saveUsersInDataBase(){
		User user1 = new User("John", "john@domain.com", LocalDate.of(2011, 01, 1));
		User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2012, 02, 2));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2013, 03, 3));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(2014, 04, 4));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2015, 05, 5));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2016, 06, 6));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2017, 07, 7));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2018, 8, 8));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2019, 9, 9));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2020, 10, 9));
		// Se crea lista para hacer registro de usuarios mas rapido
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		//Debemos inyectar el repoditorio para poder persistir la info
		// Guardamos los registros medianteuna lista para que sea mas rapido
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+ " - " + userPojo.getPassword());
		try{
			int value = 10/0;
			LOGGERNombre.debug("Mi valor: " + value);
		} catch (Exception e){
			LOGGERNombre.error("Esto es un error al dividir entre 0 " + e.getMessage());
		}
	}


}
