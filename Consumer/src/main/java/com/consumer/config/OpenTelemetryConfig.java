package com.consumer.config;

import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfig {
    @Bean
    public SdkTracerProvider tracerProvider(SpanExporter spanExporter) {
        return SdkTracerProvider.builder()
                .addSpanProcessor(BatchSpanProcessor.builder(spanExporter).build())
                .setSampler(new CustomSampler(Sampler.alwaysOn()))
                .build();
    }

    @Bean
    public OpenTelemetrySdk openTelemetry(SdkTracerProvider tracerProvider) {
        return OpenTelemetrySdk.builder()
                                .setTracerProvider(tracerProvider)
                                .build();
    }

    @Bean
    public SpanExporter spanExporter(CustomTraceExporter customTraceExporter) {
        return customTraceExporter;
    }

}

