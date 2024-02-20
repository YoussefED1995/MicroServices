package com.consumer.config;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.context.Context;
import io.opentelemetry.sdk.trace.data.LinkData;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.sdk.trace.samplers.SamplingDecision;
import io.opentelemetry.sdk.trace.samplers.SamplingResult;

import java.util.List;

public class CustomSampler implements Sampler {

    private final Sampler delegate;

    public CustomSampler(Sampler delegate) {
        this.delegate = delegate;
    }

    @Override
    public SamplingResult shouldSample(
            Context parentContext,
            String traceId,
            String spanName,
            SpanKind spanKind,
            Attributes attributes,
            List<LinkData> parentLinks) {
        if (attributes.toString().contains("traces")) {
            return SamplingResult.create(SamplingDecision.DROP);
        } else {
            return delegate.shouldSample(parentContext, traceId, spanName, spanKind, attributes, parentLinks);
        }
    }

    @Override
    public String getDescription() {
        return delegate.getDescription();
    }
}




