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
//// 도메인 이해하기
//@Configuration
//@RequiredArgsConstructor
//
//public class JobParameter {
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
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
////                  StepContribution에 들어와서 StepExecution안에 JobExecution을 보면 JobParameters가있다.
////                  그래서 이 부분을 참조할수있는것
////                  ChunkContext 안에 StepCOntext안에 StepExecution 안에 JobExecution안에 JobParameters가 마찬가지로 존재함
////                  그래서 두개의 클래스에서 참조할수 있는데 차이점이 존재한다.
//                        JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
//                        jobParameters.getString("name");
//                        jobParameters.getLong("seq");
//                        jobParameters.getDate("date");
//                        jobParameters.getDouble("age");
////                      보이다시피 위와 다르게 아래는 Map형태로 반환됨
////                      둘다 조회할수 있지만 Map의경우 딱 고정이 되어있음.
////                      위에 jobParameters에서는 변경이 가능하지만 아래는 변경하기 위한 용도가 아님
//                        Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();
//
//                        System.out.println("step 1 executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("step 2 executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
////    Job을 실행할 때 함께 포함되어 사용되는 파라미터를 가진 도메인 객체
////    하나의 Job에 존재 할 수 있는 여러개의 Instance를 구분하기 위한 용도
////    JobParameters와 JobInstance는 1:1관계
//
////    생성 및 바인딩
////    1. java -jar LongBatch.jar(requestDate=20210101) - 어플리케이션 실행시 주입
////    2. JobParameterBuilder, DefaultJobParametersConverter - 코드로 생성
////    3. @value, @JobScope, @StepScope 선언 필수 - SpEL이용
////    JobParameters Wrapper 키밸류 형태로 (LinkedHashMap<String, JobParameters>) -> 파라미터 값 저장 -> 파라미터 타입 구분 (String, Date, Long, Double)
//}
