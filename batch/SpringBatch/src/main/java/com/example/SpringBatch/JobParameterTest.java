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
//import java.util.Date;
//
//@Component
//public class JobParameterTest implements ApplicationRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private org.springframework.batch.core.Job job;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("test1", "tasklet")
//                .toJobParameters();
//
//        jobLauncher.run(job, jobParameters);
//
//    }
//}
