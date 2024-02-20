package com.consumer.model;

import lombok.Data;

@Data
public class Tracer {

    private String spanId;
    private String traceId;
    private Double duration;
    private String applicationName;
    private String methodName;
}
