package com.example.SpringBatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class API3 {
    
    /*
    사용자 정의 ExitStatus에서 exitCode를 새롭게 정의해서 설정
    StepExectuionListener의 afterStep() 메서드에서 Custom exitCode 생성 후 새로운 ExitStatus 반환
    Step 실행 후 완료 시점에서 현재 exitCode를 사용자 정의 exitCode로 수정할 수 있음
     */

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return this.jobBuilderFactory.get("batchJob")
                .start(step1())
                    .on("*")
                    .to(step2())
                    .on("PASS")
                    .stop()
                .end()
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
                .listener(new PassCheckingListener())
                .build();
    }



}
