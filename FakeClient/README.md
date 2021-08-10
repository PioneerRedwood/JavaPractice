# FAKECLIENT 🐱‍👤
FakeClient project is a fake client for explosion of Node.js Server in [nodejs-server](https://github.com/PioneerRedwood/nodejs-server).

-- 2021-08-05 Started--

## 1차 시도 (2021-08-06 ver)
- 단순히 TCP 통신하는 클라이언트 소켓을 생성한 뒤 무턱대고 주소, 포트 번호에 연결
- 결과: 실패

### 실행

<img src="https://user-images.githubusercontent.com/45554623/128480149-5be18192-97fb-4693-ae7e-8818518f1ba4.png">

## 2차 시도 (2021-08-10 ver)
추가된 코드 DDOS_TEST_JS.js
- nodejs는 서버/클라이언트 모두 WebSocketServer 혹은 WebSocket을 사용해서 연결하는 것 구현
- while 무한루프를 돌려서 클라이언트에서 서버에 요청을 보내는데 있어서 setTimeout을 통해 간격을 두려고 하기도 했으나 제대로 동작하지 않은 모습을 목격 -> 테스트 자체가 적절하지 않아보이는 생각을 시작
- FakeClient 만든 목적인 서버를 폭파시키는 것은 시각적으로도 확인하기 어려우며 테스트하기에도 만족할 만한 결과가 나오지 않을 것으로 판단
- 이제 더 이상 FakeClient의 업데이트는 없을 예정