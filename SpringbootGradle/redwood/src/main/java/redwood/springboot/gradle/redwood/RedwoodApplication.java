package redwood.springboot.gradle.redwood;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedwoodApplication {

	/* 다른 어플리케이션 실행을 위해 주석 처리 	
	public static void main(String[] args) {
		SpringApplication.run(RedwoodApplication.class, args);
	} */

	// @Bean: Spring 컨테이너가 다루는 Bean 요소임을 명시
	@Bean
	public CommandLineRunner CommandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot :");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for(String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

}
