package com.example.SpringBatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@Configuration
@RequiredArgsConstructor
public class TaskletStep {
    /*
    Tasklet Vs Chunk 기반 비교
    스프링 배치에서 Step의 실행 단위는 크게 2가지
    chunk기반 : 하나의 큰 덩어리를 n개씩 나눠서 실행한다는 의미로 대량 처리하는 경우 효과적
    안에 TransactionTmplate에서 transaction처리함 Rollback등
    Task기반 : ItemReader와 ItemWriter와 같은 청크기반 작업보다 단일 작업 기반으로 처리되는 것이 효율 적인경우
    주로 Tasklet 구현체를 만들어 사용 대량 처리의 경우 chunk기반에 비해 복잡한 구현요구 됨

     */

    private StepBuilderFactory stepBuilderFactory;
    private JobBuilderFactory jobBuilderFactory;
//    청크기반
    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("chunkStep")
                .<String, String>chunk(10)
                .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4", "item5")))
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String item) throws Exception {
                        return item.toUpperCase(Locale.ROOT);
                    }
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        items.forEach(item -> System.out.println(item));
                    }
                })
                .build();


        /*
        Tasklet 타입 클래스 특징
        1. 익명 혹은 구현 클래스를 만들어서 사용
        2. 주로 단일 태스크 수행하기 위함
        3. RepeatStatus Tasklet의 반복 여부 상태 값
        4. 흐름에 봤듯이 step의 구현 기반에 따라 다른데 Tasklet의 경우 TaskletStepBuildr가 반환되어 관련 API를 설정할 수 있다.
        5. Step에는 오 직하나의 Tasklet만 구현가능하고 두개이상인경우 마지막 객체만실행됨
         */
    }
}
