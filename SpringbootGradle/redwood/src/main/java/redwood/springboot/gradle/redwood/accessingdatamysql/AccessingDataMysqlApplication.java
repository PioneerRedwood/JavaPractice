package redwood.springboot.gradle.redwood.accessingdatamysql;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 해당 어노테이션을 통해 다음 전체 과정을 편리하게 추가
// - @Configuration: 어플리케이션 컨텍스트에 대한 Bean 정의를 소스로 클래스에 태그를 지정
// - @EnableAutoConfiguration: 클래스 경로에 기반하여 Spring Boot에 bean들을 추가하도록 요청
// - @ComponentScan: Spring에게 다른 컴포넌트, 설정과 서비스를 찾도록 요청
public class AccessingDataMysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMysqlApplication.class, args);
    }
}