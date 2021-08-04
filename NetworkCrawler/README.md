## #2 NetworkCrawler

##### Simple Process and Java Runtime with Windows command

- Process
- Runtime
- Windows command lines (netstat, tasklist .. )

##### Log 폴더
- Logger.java를 통해 생성된 로그 파일 저장

##### pdfs 폴더
- wkhtmltopdf.exe 응용 프로그램을 통해 생성된 pdf파일 저장

##### CodeTest 폴더
- 응용 프로그램을 일련으로 실행시키면서 테스트한 작업 파일 저장
- 별도의 README.MD가 있음

사용 방법
JavaTestCode.java main class 안에 다음 코드 추가

```java
NetworkCrawler crawler = new NetworkCrawler();
crawler.crawl();


```

다음 명령어 실행

```
cd JavaPractice
javac NetworkCrawler\JavaTestCode.java
java NetworkCrawler.JavaTestCode


```

[사진은 보안 관계로 넣지 않음]