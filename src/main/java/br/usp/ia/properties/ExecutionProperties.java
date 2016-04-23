package br.usp.ia.properties;

import lombok.AllArgsConstructor;
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
    @Value("${generationsToLogOnScreenInterval:100}")
    private int generationsToLogOnScreenInterval;

    @Value("${stopStrategy:NUMBER_OF_GENERATIONS}")
    private StopStrategy stopStrategy;
    @Value("${maxNumberOfGenerations:999999999999}")
    private int maxNumberOfGenerations;

    @Value("${populationChangeStrategy:COMPLETE_REPLACEMENT}")
    private PopulationChangeStrategy populationChangeStrategy;

    @AllArgsConstructor
    public enum StopStrategy {
        NUMBER_OF_GENERATIONS, CONVERGENCE
    }

    @AllArgsConstructor
    public enum PopulationChangeStrategy {
        COMPLETE_REPLACEMENT, MU_PLUS_LAMBDA
    }

}
