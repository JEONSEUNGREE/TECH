package com.example.SpringBatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class Chunk {

//    하나의 덩어리들을 잘라서 실행
//    ImteReader와 ImteProcessor는 Chunk 내 개별 아이템을 처리함
//    ItemWriter는 Chunk 크기만큼 아이템을 일괄 처리한다.
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job ParentJob() {
        return this.jobBuilderFactory.get("parentJob")
                .start(step1())
                .next(step2())
                .build();
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(5)
                .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4")))
                .processor(new ItemProcessor<String, String>() {
//                    <Input, OutPut>chunk(chunk size)
                    @Override
                    public String process(String item) throws Exception {
                        Thread.sleep(300);
                        System.out.println("item : " + item);
                        return "my" + item;
                    }
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        Thread.sleep(300);
                        System.out.println("item : " + items);
                    }
                })
                .build();
    }
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
