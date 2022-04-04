"use strict";
console.log("Hello Typscript");
console.log("Hello Typscript");

// tsc hello.ts를 진행하면 자바스크립트파일이 생성되는데
// 이과정을 트랜스 컴파일 과정이라고한다.
// node hello.js를 실행하면 출력된다.

// tsc --init으로 tsconfig.json파일을 생성한다.
// 생성하는 이유는 컴파일 이후의 파일을 src폴더와 빌드 폴더 이렇게 나누어서 저장하기위함

// npm init -y - package.json 파일 생성

// npm install nodemon concurrently 
// nodemon은 파일수정시 즉시 반영
// concurrently는 병행실행 빌드와 실행 병행됨

// package.json script 실행 
// 참고로 tsc -w 는 tsc 트랜스컴파일 과정을 보겠다는 옵션이다.
// "start:build": "tsc -w",
// "start:run": "nodemon build/index.js",
// "start": "concurrently npm:start:*"
