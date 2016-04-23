package br.usp.ia.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "algorithm.params.execution")
public class ExecutionProperties {

    @Value("${populationSize:10000}")
    private int populationSize;
    @Value("${elitism:true}")
    private boolean elitism;
    @Value("${perpetuating:true}")
    private boolean perpetuating;
    @Value("${populationSize:100}")
    private int generationsToLogOnScreenInterval;

}
