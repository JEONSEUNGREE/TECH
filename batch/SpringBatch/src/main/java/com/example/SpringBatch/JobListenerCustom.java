package com.example.SpringBatch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListenerCustom implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
//        listener @value 어노테이션에 설정해줌(JobScpe선언필수)
        jobExecution.getExecutionContext().putString("name", "user1");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
