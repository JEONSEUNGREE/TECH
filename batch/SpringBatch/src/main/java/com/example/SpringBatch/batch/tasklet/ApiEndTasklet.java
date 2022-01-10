package com.example.SpringBatch.batch.tasklet;

import com.example.SpringBatch.batch.chunk.processor.ApiItemProcessor1;
import com.example.SpringBatch.batch.chunk.processor.ApiItemProcessor2;
import com.example.SpringBatch.batch.chunk.processor.ApiItemProcessor3;
import com.example.SpringBatch.batch.classifier.ProcessorClassifier;
import com.example.SpringBatch.batch.domain.ApiRequestVo;
import com.example.SpringBatch.batch.domain.ProductVO;
import com.example.SpringBatch.batch.job.api.QueryGenerator;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApiEndTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println(" >> ApiService1 is started");

        return RepeatStatus.FINISHED;
    }

//    private DataSource dataSource;
//
//    private void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public Map<String, ExecutionContext> partition(int gridSize) {
//
//        ProductVO[] productList = QueryGenerator.getProductList(dataSource);
//
//        HashMap<String, ExecutionContext> result = new HashMap<>();
//
//        int number = 0;
//
//        for (int i = 0; i < productList.length; i++) {
//            ExecutionContext value = new ExecutionContext();
//
//            result.put("partition" + number, value);
//            value.put("partition" + number, value);
//
//            number++;
//        }
//
//        return result;
//    }


}
