package com.consumer.config;


import com.consumer.model.Tracer;
import com.consumer.service.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomTraceExporter implements SpanExporter {

    @Value("${spring.application.name}")
    private String applicationName;

    private Producer producer;


    @Autowired
    public CustomTraceExporter(Producer producer) {
        this.producer = producer;
    }

    @Override
    public CompletableResultCode export(Collection<SpanData> spans) {
        System.out.println("data size :"+spans.size());
        for (SpanData span : spans) {
            Tracer tracer = new Tracer();
            tracer.setTraceId(span.getSpanContext().getTraceId());
            tracer.setSpanId(span.getSpanContext().getSpanId());
            tracer.setApplicationName(applicationName);
            tracer.setMethodName(span.getName());
            long elapsedTime = span.getEndEpochNanos() - span.getStartEpochNanos();
            tracer.setDuration((double) elapsedTime / 1_000_000_000);
            try {
                producer.sendTrace(tracer);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return CompletableResultCode.ofSuccess();
    }

    @Override
    public CompletableResultCode flush() {
        return CompletableResultCode.ofSuccess();
    }

    @Override
    public CompletableResultCode shutdown() {
        return CompletableResultCode.ofSuccess();
    }
}

