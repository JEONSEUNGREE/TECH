package com.example.SpringBatch;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JpaCursorItemReader {

//    Step -> ItemStream -> Create EntityManager, CreateQuery, Create ResultStream
//    JpaCursorItemReader -> doRead() -> ResultStream -> next() -> Iterator
//    ItemStream -> Close EntityManager

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job ParentJob() {
        return this.jobBuilderFactory.get("parentJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(3)
                .reader(customItemReader())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public ItemReader<? extends Customer> customItemReader() {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", "a%");

        return new JpaCursorItemReaderBuilder<Customer>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select c from Customer c where firstName like :firstName")
                .parameterValues(parameters)
                .build();
    }

    @Bean
    public ItemWriter<Customer> customerItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println(item.toString());
            }
        };
    }

//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }


}
