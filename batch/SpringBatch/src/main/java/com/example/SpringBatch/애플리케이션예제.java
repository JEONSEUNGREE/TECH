package com.example.SpringBatch;

public class 애플리케이션예제 {

    /*
    Job - 1
    기능 : 파일로 부터 데이터를 읽어서 DB 에 적재한다.
    세부내용 : 파일은 매일 새롭게 생성됨
    매일 정해진 시간에 파일 로드, 데이터 DB에 업데이트
    이미 처리한 파일은 다시 읽지 않도록 한다.

    프로세스
    File -> ItemReader -> ItemWriter -> DB

    Job - 2
    기능 : DB로부터 데이터를 읽어서 API 서버와 통신한다.
    내용 : Partitioning 기능 - 머티스레드 구조 CHunk 기반 프로세스 구현
    API 서버는 3개로 구성하여 요청을 처리
    제품내용과 API 통신 결과를 각 파일로 저장 (FlatFileWriter 상속)

    프로세스
    File -> ItemReader -> ItemProcessor -> ItemWriter -> File -> API

    Scheduler
    기능 : 시간을 설정하면 프로그램을 가동시킨다.
    내용 : 정해진 시간에 주기적으로 Job-1 과 Job-2를 실행시킨다. - Quatz 오픈소스활용
    
    */
}
