package com.curso.api.spring_securiy_course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = "com.curso.api.spring_securiy_course")
public class SpringSecuriyCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecuriyCourseApplication.class, args);
	}
	/*@Bean
	public CommandLineRunner createPasswordCommand(PasswordEncoder passwordEncoder){
		return args -> {
			System.out.println(passwordEncoder.encode("clave123"));
			System.out.println(passwordEncoder.encode("clave456"));
			System.out.println(passwordEncoder.encode("clave789"));
		};
	}*/

}
