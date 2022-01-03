package com.example.SpringBatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Transition
@RequiredArgsConstructor
@Configuration
public class API2 {

    /*
    Transition
    BatchStatus - JobExecution과 StepExecution의 속성으로 Job과 Step의 종료 후 최종 결과 상태가 무엇인지 정의

    ExistStatus 와 BatchStatus는 일반적으로 같다.
    JobExecution Exist_Code는 마지막에 실행한 Step의 ExistStatus를 보고 실패 성공여부를 설정한다.
    Step의 종료상태(ExitStatus)가 어떤 pattern과도 매칭되지 않으면 스프링 배치에서 예외 발생하고 Job은 실패함
    on() 메서드 안에 (string pattern)이 BatchStatus 와 매칭 하는 것이 아님
    pattern과 ExitStatus와 매칭이 되면 다음으로 실행할 Step을 지정할 수 있다.
    transition은 구체적인 것부터 그렇지 않은 순서로 적용된다.
    특수문자 2가지
    "*", "?"
    ex) c*t = cat, count 매칭됨 , c?t cat은 매칭되지만 count는 매칭되지않음

    Tansition API
    stop() FlowExecutionStatus가 STOPPED 상태로 종료되는 transition
    Job의 BatchStatus와 ExitStatus가 STOPPED으로 종료됨

    fail()
    FlowExecutionStatus가 FAIL 상태로 종료되는 transition
    Job의 BatchStatu와 ExitStatus가 FAILED로 종료됨

    end()
    FlowExecutionStatus가 Complete상태로 종료됨 (Job의 재시작이 불가능함)
    
    ex) 
    step1 -> A -> step2
    1실행 후 A가 나오면 2실행
    step2 -> * -> stop
    2실행후 결과에 상관없이 작업중단
     */

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return this.jobBuilderFactory.get("batchJob")
                .start(step1())
                    .on("*")
                    .to(step3())
                    .next(step4())
//                step1이 실패일경우 위 플로우가 실행
                .from(step1())
                    .on("*")
                    .to(step3())
                    .next(step4())
//              step1이 실행될경우 실행
                .from(step2())
                    .on("*")
                    .to(step5())
                    .end()
//              step2이 실행할경우
                .build();

    }

    private Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1() executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step2() executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step3() executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step4() executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step5() executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
