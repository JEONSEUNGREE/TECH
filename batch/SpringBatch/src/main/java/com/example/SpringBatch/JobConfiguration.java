package com.example.SpringBatch;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
//    스프링에서 해당 싱글톤 빈으로 만드는 과정
    public Job job() {
//        잡은 심플잡과 플로우잡 2개를 구현
        return jobBuilderFactory.get("job")
                .start(step1())
//                SimpleJobBuilder클래스에서 SimpleJobBuilder start 메서드를 디버깅해보면
//                위 스타트에서 스텝을 매개변수로 받고 스텝스 리스트에 스텝을 저장한다.
//                그 스텝은 구현체가 Tasklet이다.
//                심플잡은 컨테이너 역할을 한다고 보면된다.
                .next(step2())
//                동일하게 그다음으로 스텝2가 스텝스 리스트에 추가된다.
                .build();
//               잡이 두개의 스텝 즉 스텝스를 갖게 된다. (빌드메서드 시행 되는 시점에)
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        한번실행하고 끝내겠다는 것
//                        아닌경우 Continue로 둘 것
                        System.out.println("step1 was executed");
//                        이 스텝에는 사살상 개발자가 비즈니스로직이라던지 로직을 구현하는 부분이다.
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step2 was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

//    그리고 위에 설정한 잡을 실행해야되는데 이 부분은 스프링부트가 자동으로 실행해준다.
//    BatchAutoConfiguration클래스를 확인해보면 JobLauncherApplicationRunner에서 실행함.
//    SimpleJobLauncher에서 job.execute(jobExecution); job = SimpleJob
//    job.execute는 SimpleJob의 부모클래스 즉 AbstractJob로 부터 온 메서드로 해당 부모 클래스로 이동해서 일련의 과정읠 거친후 스텝 실행
//    doExecute - 실행 (SimpleJob에 오버 라이딩됨)
//    simpleJob => steps => step1 => tasklet
//    step2 => tasklet

}
