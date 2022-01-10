package com.example.SpringBatch.batch.classifier;

import com.example.SpringBatch.batch.domain.ApiRequestVo;
import com.example.SpringBatch.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class ProcessorClassifier<C, T> implements Classifier<C, T> {

    private Map<String, ItemProcessor<ProductVO, ApiRequestVo>> processorMap = new HashMap<>();


    @Override
    public T classify(C classifiable) {

        return (T) processorMap.get(((ProductVO) classifiable).getType());

    }

    public void setProcessorMap(Map<String, ItemProcessor<ProductVO, ApiRequestVo>> processorMap) {
        this.processorMap = processorMap;
    }
}


