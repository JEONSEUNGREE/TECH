//package com.example.SpringBatch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
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
//@Configuration
//@RequiredArgsConstructor
//public class JobInstance {
////  이번에는 수동실행
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
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
////    Job이 실행될 때 생성되는 Job의 논리적 실행 단위 개게로서 고유하게 식별 가능한 작업 실행을 나타냄
////    예) 하루에 한번씩 배치 Job이 실행된다면 매일 실행되는 각각의 Job을 JobInstance로 표현한다.
////    JobInstance 생성 및 실행
////    즉 Job_NAME(Job)과 JOB_KEY(Job_Parameters 해시값) 가 동일한 데이터는 중복해서 저장할 수 없음
////    처음 시작시 Job + JobParameter 일 경우 새로운 JobInstance 생성 ( JobLauncher의 인자는 Job과
////    JobParameter이다.)
////    이전과 동일한 Job + JobParameter 으로 실행 할 경우 이미 존재하는 JobInstance 리턴
////    내부적으로 JobName + Jobkey( JobParameter의 해시 값)를 가지고 JobInstance 객체를 얻음
////    Job과는 1:M 관계
////    과정
////    JobLauncher -> run(job, jobParameters) -> JobRepository -> DB(잡이 실행된적이 있는지 job과 jobParameters를 비교하여 기존잡
////    비교 여부) -> 존재하는경우 -> JobInstance를 새롭게 생성하지 않고 기존 JobInstance 리턴
////    -> 존재하지 않는경우 새로 JobInstance 생성
//
////    예를 들면
////    Job ( 일별정산 ) 이 존재한다.
////    JobInstanceA ( 일별 정산, 2021.01.01) , JobInstanceB ( 일별정산, 2021.01.02)가
////    존재한다고 치자 그러면
////    BATCH_JOB_INSTANCE 테이블에서
////    JobInstance Name은 같지만 JobParameter가 다르므로 JobKey가 다를 것이다.
////    만약 JobParameters가 같다면 해시한 키값이 같기 때문에 인스턴스가 실행될 수 가 없다.
////    즉 한번더 실행되지 않고 이전값을 리턴한다는 뜻이다.
//
////    실제 위 코드를 실행한 후 DB를 확인해보자
////    Instance가 생성되었고 key(JobParameters)인 해쉬값도 확인할수있다.
////    JobInstance에서 수동으로 설정한 BATCH_JOB_EXECUTION_PARAMS 테이블에서 타입으로 설정한 타입, 키, 파라미터를 확인할수있다.)
////    그리고 한번 더 실행을 하면 JobInstanceAlreadyCompleteException: A job instance already exists and is complete for parameters={name=user1}.  If you want to run this job again, change the parameters.
////    같은 에러가 발생한다. 이미 존재하기떄문에(해시값이 동일한 키가 존재하므로) 예외발생
////    다시금 실행하려면 파라미터 값 혹은 key_Name을 다르게 바꿔야지 실행이가능하다.
//
//
//}
