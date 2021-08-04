## #3 JSP & Webapp

### 아파치 톰캣 8.5-1 서버를 설치해서 JSP를 동작시키는 테스트를 진행

#### common 폴더
- 해당 프로젝트에서 사용되는 공통적인 요소가 들어가는 부분
- 다소 뒤엉킨 구조..

#### service 폴더
- 실제 서버에서 서비스하는 웹 페이지를 실행하는 부분
- 사실상 응용 프로그램을 웹 서버 측(?)에서 실행시키고 결과를 html에 뿌려주는 형식으로 구현
- 인코딩 형식이 안 맞아서 한글이 깨짐
- 차라리 깨지는 것보다 영어로 출력하는게 어떠한가 해서 시도해봤으나 그것도 제대로 안됨
  - 명령 프롬프트 자체 출력 언어 변경 => 실패
  - html 파일 자체 인코딩 형식 UTF-8로 변경 => 실패
  - 문제는 명령어를 실행시키고 결과를 InputStream으로 읽어온 후 화면에 출력한 내용을 가져오는 형식 자체가 한글이 깨지는 형식인 듯함 => 해결 방법 찾지 못함

##### panzoom test
- css 확대 기능을 제공하는 오픈소스인 [panzoom](https://github.com/timmywil/panzoom/)

<img src="https://user-images.githubusercontent.com/45554623/128125999-8163a264-6c45-4005-aa3a-4f8c3d558b39.png">


#### static 폴더
- 프로젝트 내에서 사용하는 정적인 파일 저장 위치
  - [호랑이](https://www.bbc.com/korean/news-53303810) awesome_tiger.jpg