# FAKECLIENT ๐ฑโ๐ค
FakeClient project is a fake client for explosion of Node.js Server in [nodejs-server](https://github.com/PioneerRedwood/nodejs-server).

-- 2021-08-05 Started--

## 1์ฐจ ์๋ (2021-08-06 ver)
- ๋จ์ํ TCP ํต์ ํ๋ ํด๋ผ์ด์ธํธ ์์ผ์ ์์ฑํ ๋ค ๋ฌดํฑ๋๊ณ  ์ฃผ์, ํฌํธ ๋ฒํธ์ ์ฐ๊ฒฐ
- ๊ฒฐ๊ณผ: ์คํจ

### ์คํ

<img src="https://user-images.githubusercontent.com/45554623/128480149-5be18192-97fb-4693-ae7e-8818518f1ba4.png">

## 2์ฐจ ์๋ (2021-08-10 ver)
์ถ๊ฐ๋ ์ฝ๋ DDOS_TEST_JS.js
- nodejs๋ ์๋ฒ/ํด๋ผ์ด์ธํธ ๋ชจ๋ WebSocketServer ํน์ WebSocket์ ์ฌ์ฉํด์ ์ฐ๊ฒฐํ๋ ๊ฒ ๊ตฌํ
- while ๋ฌดํ๋ฃจํ๋ฅผ ๋๋ ค์ ํด๋ผ์ด์ธํธ์์ ์๋ฒ์ ์์ฒญ์ ๋ณด๋ด๋๋ฐ ์์ด์ setTimeout์ ํตํด ๊ฐ๊ฒฉ์ ๋๋ ค๊ณ  ํ๊ธฐ๋ ํ์ผ๋ ์ ๋๋ก ๋์ํ์ง ์์ ๋ชจ์ต์ ๋ชฉ๊ฒฉ -> ํ์คํธ ์์ฒด๊ฐ ์ ์ ํ์ง ์์๋ณด์ด๋ ์๊ฐ์ ์์
- FakeClient ๋ง๋  ๋ชฉ์ ์ธ ์๋ฒ๋ฅผ ํญํ์ํค๋ ๊ฒ์ ์๊ฐ์ ์ผ๋ก๋ ํ์ธํ๊ธฐ ์ด๋ ค์ฐ๋ฉฐ ํ์คํธํ๊ธฐ์๋ ๋ง์กฑํ  ๋งํ ๊ฒฐ๊ณผ๊ฐ ๋์ค์ง ์์ ๊ฒ์ผ๋ก ํ๋จ
- ์ด์  ๋ ์ด์ FakeClient์ ์๋ฐ์ดํธ๋ ์์ ์์ 