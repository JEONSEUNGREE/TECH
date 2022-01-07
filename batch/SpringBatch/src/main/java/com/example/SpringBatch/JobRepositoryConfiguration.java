//package com.example.SpringBatch;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecutionListener;
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
//@RequiredArgsConstructor
//@Configuration
//public class JobRepositoryConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final JobExecutionListener jobRepositoryListener;
//
//    @Bean
//    public Job helloJob() {
//        return jobBuilderFactory.get("helloJob")
//                .start(helloStep1())
//                .next(helloStep2())
//                .listener(jobRepositoryListener)
//                .build();
//    }
//
//    @Bean
//    public Step helloStep1() {
//        return stepBuilderFactory.get("helloStep1")
////                스템에서는 tasklet을 기본적으로 무한 반복시킨다.
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//                        System.out.println("=======================");
//                        System.out.println("  >> Hello Spring Batch 1!");
//                        System.out.println("=======================");
//
////                        기본한번 실행되고 끝
//                        return RepeatStatus.FINISHED;
//                    }
//
//                })
//                .build();
//    }
//
//    @Bean
//    public Step helloStep2() {
//        return stepBuilderFactory.get("helloStep2")
////                스템에서는 tasklet을 기본적으로 무한 반복시킨다.
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//                        System.out.println("=======================");
//                        System.out.println("  >> Hello Spring Batch 2!");
//                        System.out.println("=======================");
//
////                        기본한번 실행되고 끝
//                        return RepeatStatus.FINISHED;
//                    }
//
//                })
//                .build();
//    }
//
//}
