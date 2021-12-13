//package com.example.SpringBatch;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JobRunner implements ApplicationRunner {
//
//    @Autowired
////    spring-batch초기화 될때 bean 등록이 되어있어서 의존성 주입 가능
//    private JobLauncher jobLauncher;
//
//    @Autowired
////    JobInstance에서 생성한 잡 주입 (bean생성되어있음)
//    private Job job;
//
////  Job 수동 실행 해보기
////  Runner하고 나서 스프링부트 자동 실행 막기
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder().addString("name1", "user2").toJobParameters();
//        jobLauncher.run(job, jobParameters);
//
//    }
//}
