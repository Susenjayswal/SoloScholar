package com.soloscholar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 * 
	 * @Autowired private BookService bookService;
	 * 
	 * @Autowired private UserRepository userRepository;
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	/*
	 * @Bean public CommandLineRunner initialCreate() { return (args) -> {
	 * 
	 * var admin = new User(1l,"admin", "admin","susen@gmail.com", "aadhar",
	 * "1234", 6767546576l,25, "male", "noida,uttar pradesh",
	 * passwordEncoder.encode("Temp@123"), "admin"); userRepository.save(admin); var
	 * staff = new User(2l,"staff", "staff","staff123@staff.in", "aadhar", "4321",
	 * 9876543210l, 26, "male","pune, maharasthra",
	 * passwordEncoder.encode("Temp@123"), "staff"); userRepository.save(staff);
	 * 
	 * 
	 * }; }
	 */
}