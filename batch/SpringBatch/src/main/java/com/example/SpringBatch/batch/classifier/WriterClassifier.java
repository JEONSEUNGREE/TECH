package com.example.SpringBatch.batch.classifier;

import com.example.SpringBatch.batch.domain.ApiRequestVo;
import com.example.SpringBatch.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class WriterClassifier<C, T> implements Classifier<C,T> {

    private Map<String, ItemWriter<ApiRequestVo>> writerMap = new HashMap<>();

    @Override
    public T classify(C classifiable) {

        return (T) writerMap.get(((ProductVO) classifiable).getType());

    }

    public void setWriterMap(Map<String, ItemWriter<ApiRequestVo>> writerMap) {
        this.writerMap = writerMap;
    }
}
