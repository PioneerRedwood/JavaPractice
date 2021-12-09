# JavaPractice
updated 2021.12.09

해당 레포지토리는 `나이스신용정보 IPP 일학습병행 인턴십`동안 개인적으로 수행한 기록을 남긴 것입니다.
- Spring Framework - 전자정부프레임워크에 쓰이는 언어인 Java에 익숙해지기 위해
- 개발을 멈추지 않기 위해
- 기간: 2021.07 - 2021.12

​	Java practice in NICE, What I want to do with other languages(C++, Python).

실행 시 별도의 IDE는 없어서 CMD에서 javac 명령어를 통해 일일이 java 파일을 컴파일하고 실행하는 방식



## #1 EasyTalk

##### Simple Network Chatting program

- Input/Output Stream with Socket
- GUI by javax.swing library
- 단순한 소켓을 기반으로 한 채팅 프로그램입니다. 
- swing 라이브러리를 사용해 사용자 그래픽 인터페이스를 디자인했습니다.


## #2 NetworkCrawler

##### Simple Process and Java Runtime with Windows command

- Process
- Runtime
- Windows command lines (netstat, tasklist .. )
- 프로세스, 런타임 도중에 Windows 명령어를 사용하는 것을 시도했습니다. 


## #3 JSP & Webapp

##### Simple Apache & JSP 
- Sub process executing network commands in Web
- Print result on HTML -- FAIL..
- 웹 내에서 네트워크 명령어를 수행한 결과를 HTML 형태로 반환하려고 시도했습니다.


## #4 File system
##### Simple Logger
- Log file creation, Directory setting (specified directory must be real)
- log4j라는 라이브러리에 영감을 받았습니다.


## 그외 시도들
### 8th commit contents 2021-08-04
- 일부 폴더 및 파일 리팩토링, 각 프로젝트 별 README.MD 추가 및 수정
- 예정 진행 사항 - Spring Framework를 이용한 REST API 서버 구축

### #5 Springboot Web
#### Spring Initializer with build.gradle
- vscode 내 Spring Initializer & Gradle 기반 웹 서비스 테스트
- vscode를 원격으로 Spring 환경을 구축하는 부분이 사소한 문제가 많아 잠정 중단(2021-08-06)

### 원인
- 사내에선 외부와 차단된 네트워크를 사용하는 개발 PC로는 한계가 있었습니다.
- 최신 maven 빌드 시스템을 사용하기에 힘든 환경입니다.
