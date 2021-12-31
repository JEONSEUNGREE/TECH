package com.example.SpringBatch;

public class StepBuilderFactory {

    /*
    여태 StepBuilderFactory -> StepBuilder -> 익명 tasklet을 구현했는데 이때의 조건에 따라서 생성 Builder가 달라진다.
    tasklet -> TaskletStepBuilder
    chunk기반 -> SimpleStepBuilder 로 생성됨

    따라서 JobRepository는 빌더 클래스를 통해서 Step 객체에 전달되어 메타데이터를 기록하는데 사용된다.
     */
}
