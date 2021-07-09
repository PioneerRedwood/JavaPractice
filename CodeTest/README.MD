# 프로젝트 분석 
    기간: 2021.07.07 - 2021.07.현재 진행 중
    내용: JSP, Javax.servlet 등을 이용한 웹 어플리케이션 분석
    DB, Server, Web .. 


## 2021.07.08 Log
#### 서브 프로세스 실행과 관련한 내용
    의도적으로 서브 프로세스를 재귀적으로 호출하여 프로세스 종료를 하지 않는 이상 메모리 해제가 되지 않는 버그를 만들어냈다. 시스템이 멈출 수 있는 치명적인 오류가 날 수 있다.
        C:\\UserDownload\JavaPractice\JavaTestCode.java
        - 	해당 블로그에선 메인 프로세스에서 런타임 시 
            다른 하위(서브) 프로세스 생성 시 발생할 수 있는 문제점을 경고.
        - 	생성된 각 프로세스는 동일한 프로세스 혹은 스레드에 있을 경우 교착상태와
            시스템 멈춤 상태와 같은 오류가 발생할 수 있다.
        -	하지만, 지금 진행하고 있는 프로젝트 내에서 과연 발생할까라는 의문이 생긴다.
        -	jdk\jre\lib\security\java.policy 프로젝트 보안 정책 설정 파일
        -	원격 프로세스 실행(호출)은 해킹에 취약함
    관련 클래스
        - ProcessTest.java
#### 위 내용의 결론: jdk 7 이상에서는 지원하는 똑똑한 메서드를 사용하자.

    

### NAVER D2 블로그
* 2021.07.08. NAVER D2 about Java handling process
* This blog is warning that 
* if use InputStream, ErrorStream, OutputStream in same subprocess,
* it can be stopped or fall into deadlock.
* We should avoid it. By applying to use a thread for each stream.
* But, I think personally, it would not be happened for our procedure. 
### Refered: https://d2.naver.com/helloworld/1113548

#### JSP, servlet 실습 요망
🤔 각 JSP 파일들을 실행할 때 app 내의 인스턴스 생성 및 관리를 서버 측에서 하는 걸까?

#### Process, Runtime, 응용 프로그램 실행 관련
    NetworkCrawler.java
    CommandStream.java

#### wkhtmltopdf 라이브러리
    ComparisonTest.java
- wkhtmltopdf 웹 페이지를 이미지 혹은 pdf 파일로 변환하는 오픈소스 라이브러리
* 해당 라이브러리를 실행하기 위해 process를 생성하고 
* process에서 실행의 결과를 stream으로 받는 과정에서 스레드를 쓰는 것과 쓰지 않는 것을 비교했다.
- 결과는 거의 차이 없음. 오히려 스레드를 안 쓴 쪽이 더 유리.