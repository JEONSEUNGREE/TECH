package com.example.SpringBatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

//@Component bean 등록 혹은 그냥 객체로 사용해도됨
public class CustomTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String stepName = contribution.getStepExecution().getStepName();

        String jobName = chunkContext.getStepContext().getJobName();

        System.out.println("step2 was executed");
        return RepeatStatus.FINISHED;
    }
}
