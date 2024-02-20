package com.producer.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Trace {

    private String spanId;
    private String traceId;
    private Double duration;
    private String applicationName;
    private String methodName;
}
