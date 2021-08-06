# #5 Springboot Webapp
- Springboot는 code-server를 통해 집에 있는 데스크탑에서 원격으로 실행한 vscode에서 작성
- Special thanks for [clroot](https://github.com/clroot)
- [Spring 가이드](https://spring.io/guides/) 참고

### [Spring Quick Start](https://spring.io/quickstart)
- Spring 공식 홈페이지 내부 빠른 시작으로 제작
- 빌드 툴은 Gradle 사용
- RedwoodApplication.java 내부

```java
@Bean
	public CommandLineRunner CommandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot :");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for(String beanName : beanNames) {
				System.out.println(beanName);
			}
		};g
	}
```

실행 방법
```
$ cd /home/JavaPractice/SpringbootGradle/redwood/
$ ./gradlew bootRun
```

실행 사진

<img src="https://user-images.githubusercontent.com/45554623/128309310-a3bc0fa5-dbb5-49f9-a69b-a73f698c8fbb.png">


### Spring guide accessing data mysql

#### mysql 설치
```
$ sudo apt-get install mysql-server
$ sudo service mysql start
$ mysql --password
-- 패스워드 등록 --

mysql > create database 'DB이름';                              -- DB 생성
mysql > create user '유저이름'@'%' identified by '패스워드';    -- 유저 생성
mysql > grant all on 'DB이름'.* to '유저이름'@'%';              -- 모든 권한 부여
```

#### application.properties mysql 정보 넣어주기
- mysql 설치와 계정 관리를 지속하다 Spring을 원격 vscode에서 하는데 무리를 느낌
- 엎친데 덮친격으로 ./gradlew build, bootRun 등의 명령어가 먹히지 않아 개발 멈춤
- [server_basic(with node.js)](https://github.com/PioneerRedwood/nodejs-server) & [JavaPractice.FakeClient](https://github.com/PioneerRedwood/JavaPractice/tree/main/FakeClient) 리포지토리로 이동

- MySql 다룬 부분
```
@기본
sudo apt-get install mysql-server 설치
sudo apt-get purge mysql-server 삭제
sudo rm -f -r /var/lib/mysql 관련 디렉토리 강제 삭제

@DB 생성

@사용자 생성
sudo service mysql restart 재시작
sudo mysql -u root 루트로 접속
mysql> use mysql; mysql 스키마 사용
mysql> create user 'redwood'@'localhost' identified by '1234'; 아이디 생성
mysql> grant all privileges on *.* to 'redwood'@'localhost'; 모든 DB 권한 부여
mysql> drop user 'redwood'@'localhost'; 사용자 삭제
```