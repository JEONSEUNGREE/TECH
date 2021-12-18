//package com.example.SpringBatch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Map;
//
//@RequiredArgsConstructor
//@Configuration
//public class JobExecution {
////    jobinstance에 대한 한번의 시도를 의미하는 객체로서 Job 샐행 중에 발생한 정보들을 저장하고 있는 객체
////    jobinstance 실행한다. -> jobExecution
////    jobExecution은 batch status = Filed 나 completed 등의 job 실행 결과 상태를 가지고 있음
////    JobInstance comple 인경우 jobinstance가 실행 완료된것으로 간주해서 재 실행이 불가함
////    jobExcution의 실행결과가 Failed면 Jobinstance실행이 완료되지않은것으로 간주해서 재실행이 가능함
////    jobparamter가 동일한 값으로 job을 실행할지라도 jobinstance를 계속 실행할 수 있음
////    여러개 생성된 만큼 BATCH_JOB_EXECUTION 테이블에 jobinstance에대한 성공/실패 내역을 가지고 있음
////    JobInstance와 JobExecution는 1:M의 관계다.
//
////    JobInstance는 Job이 실행하게되면 오직 한번만 실행됨 (조건 JobParameters가 동일한 내용으로 실행될때 )
////    JobExection이 실행되는 횟수
//
////    BatchStatus (completed) -> JobInstanceAleradyCompleteException Job시도결과 성공 완료인경우 JobInstatnce실행불가
//
//    //    BatchStatus (Failed) -> Job시도 결과 실패인경우 new JobInstance 생성
////    JobExecutoin이 여러번 생성된경우 completed가 되지않아서 계속 시도 됐다는 뜻이다.
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("job")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new CustomTasklet())
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("step 2 executed");
////                        throw new RuntimeException("step2 has failed");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//}
