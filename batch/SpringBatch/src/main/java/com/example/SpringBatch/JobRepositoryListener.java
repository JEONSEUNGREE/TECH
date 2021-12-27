//package com.example.SpringBatch;
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JobRepositoryListener implements JobExecutionListener {
//
//    @Autowired
//    private JobRepository jobRepository;
//
//    @Override
//    public void beforeJob(JobExecution jobExecution) {
//
//    }
//
//    @Override
//    public void afterJob(JobExecution jobExecution) {
//        String jobName = jobExecution.getJobInstance().getJobName();
////      BATCH_JOB_EXECUTION_PARAMS의 JOB_EXECUTION_ID
////      BATCH_JOB_EXECUTION_PARAMS의 KEY_NAME, STRING_VAL
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("requestDate", "20210102").toJobParameters();
//
//        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
//        if (lastJobExecution != null) {
//            for (StepExecution execution : lastJobExecution.getStepExecutions()) {
//                BatchStatus status = execution.getStatus();
//                ExitStatus exitStatus = execution.getExitStatus();
//                System.out.println("existStatus = " + exitStatus);
//                String stepName = execution.getStepName();
//                System.out.println("stepName = " + stepName);
//            }
//        }
//
//
//    }
//}
