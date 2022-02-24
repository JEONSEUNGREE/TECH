package com.example.SpringBatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.batch.api.listener.JobListener;

@RequiredArgsConstructor
@Configuration
public class JobScope {
    
    /*
    스프링 컨테이너에서 빈이 관리되는 범위
    singleton, prototype, request, session, application 있으며 기본은 singleton으로 생성됨
    
    스프링 배치 스코프
    @JobScope, @StepScope
        프록시 모드를 기본 값으로 하는 스코프 @Scope(value ="job", proxyMode = ScopedProxyMode.TARGET_CLASS)
     Job과 Step 의 빈 생성과 실행에 관여하는 스코프
     해당스코프가 선언되면 빈이 생성이 어플리케이션 구동시점이 아닌 빈의 실행시점에 이루어진다.
     @Value를 주입해서 빈의 실행시점에 값을 참조할 수 있음 일종의 Lazy Binding이 가능해진다.
     @Value("#{jobParameters[파라미터명]}"}}
     @Value 어노테이션 사용시 반드시 JobScope, StepScope지정필요하고 실제 빈등록시는 null로 nullpointException처리를 해준다.

     @JobScope, @StepScope 어노테이션이 붙은 빈선언은 내부적으로 빈의 Proxy 객체가 생성된다.
    */

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return this.jobBuilderFactory.get("batchJob")
                .start(step1(null))
                .next(step2())
                .listener(new JobListenerCustom())
                .build();

    }

    @Bean
    @org.springframework.batch.core.configuration.annotation.JobScope
    private Step step1(@Value("#{jobParameters['name'}")String name) {

        System.out.println("message " + name);

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
