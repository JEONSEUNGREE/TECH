package com.example.SpringBatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.job.JobParametersExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class API {

//    allowStratIfComplete
//    allowStratIfComplete(true) step에 주게되면 이미 성공한 스텝이어도 뒤에 스텝이 실패하면
//    성공해도 스킵하지않고 jobexectuion 마다 다시실행한다.
//    step에 startlimit을 설정하면 해당 수만큼 stpe을 실행하고 더 실행하지않음

//    JobStep job이 step을 갖는 것 (모듈화 시키는 것)
//    ParentsJob(Job)

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job ParentJob() {
        return this.jobBuilderFactory.get("parentJob")
                .start(jobStep(null))
                .next(step2())
                .build();
    }


    @Bean
    public Step jobStep(JobLauncher jobLauncher) {
        return stepBuilderFactory.get("jobStep")
                .job(childJob())
                .launcher(jobLauncher)
                .parametersExtractor(jobParametersExtractor())
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        stepExecution.getExecutionContext().putString("name", "user1");

                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .build();
    }
//  parentsJob -> childJob
    private DefaultJobParametersExtractor jobParametersExtractor() {
        DefaultJobParametersExtractor defaultJobParametersExtractor = new DefaultJobParametersExtractor();
//        beforeStep을 통해서 name파라미터 키를 참조해서 user1 value를 가져옴
        defaultJobParametersExtractor.setKeys(new String[]{"name"});
        return defaultJobParametersExtractor;

    }

    private Job childJob() {
        return jobBuilderFactory.get("childJob")
                .start(step1())
                .build();
    }

    private Step step1() {
        return null;
    }

    private Step step2() {
        return null;
    }

//    Job이 Step3개를 생성하고 하나라도 실패하면 Job이 실패하게됨
//    하지만 최종적으로 수행이 되게하려면 FlowJob을 실행해야한다.
//    또한 Step을 순서에따라서 실행되게하는 것 말고 step1 완료시 step3을 실행하는등의 앞의 변화에 따라서 뒤에
//    다른 변화를 주는 것을 FlowJob으로 구성할수있다.

    //    즉 SimpleJob은 순차적흐름
//    FlowJob은 조건적 흐름 (step의 성공여부에따른)
    @Bean
    public Job flowJob() {
        return jobBuilderFactory.get("batchJob")
                .start(step1())
                .on("COMPLETED").to(step2())
//                성공시 step2실행
                .from(step1())
                .on("FAILED").to(step2())
//                만일 step1이 실패하게되면 step2로 이동
                .end()
                .build();
//
////        일반 SimpleJob과 다르게 위에 step1을 실패시켜도 job이 성공하게됨
//          이미 조건을 설정해놨기때문
        
//        start() / next() api
//        start() 메서드 인자에 step이오면 simpleJobBuilder로 반환되고
//        start() 메서드 인자에 Flow인자가 오면 FlowJobBuilder로 반환됨
//        simpleJobbuilder에도 on메서드가 있어서 flow로 전환가능
//        next는 flow, job, decider로 총 3개가 올수있다.
    }
    

}
