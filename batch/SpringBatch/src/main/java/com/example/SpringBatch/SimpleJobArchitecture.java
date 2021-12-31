package com.example.SpringBatch;

public class SimpleJobArchitecture {

//    흐름도
//    아마 구현이 필요할것으로 예상되는 부분 표시 **
//    사용할 데이터 표시 *
//    JobInstance (Jobparameter*) -- (JobInstance JobExecution (공유되는 데이터 JobExecutionContext*)) ->
//    SimpleJob -- (JobListener에서 JobExecutionListener호출 (beforeJob()**)-->
//    Step -- StepExecution (ExecutionContext*, Tasklet**) -->
//    SimpleJob -- (JobListener에서 afterJob()**) -->
//    JobLaucnher

//    잡런쳐(인스턴스생성) -> 심플잡 -> 스텝 -> 심플잡 -> 잡런쳐
}
