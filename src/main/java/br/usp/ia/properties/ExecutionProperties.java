package br.usp.ia.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    @Value("${maxNumberOfGenerations:100000000}")
    private int maxNumberOfGenerations;

    @Value("${populationChangeStrategy:COMPLETE_REPLACEMENT}")
    private PopulationChangeStrategy populationChangeStrategy;

    @Value("${useCorrection:false}")
    private boolean useCorrection;

    @Override
    public String toString() {
        return "{\"ExecutionProperties\":{" +
                "\"populationSize\":\"" + this.populationSize +
                "\",\"generationsToLogOnScreenInterval\":\"" + this.generationsToLogOnScreenInterval +
                "\",\"stopStrategy\":\"" + this.stopStrategy +
                "\",\"maxNumberOfGenerations\":\"" + this.maxNumberOfGenerations +
                "\",\"populationChangeStrategy\":\"" + this.populationChangeStrategy +
                "\"}}";
    }

    @AllArgsConstructor
    public enum StopStrategy {
        NUMBER_OF_GENERATIONS, CONVERGENCE
    }

    @AllArgsConstructor
    public enum PopulationChangeStrategy {
        COMPLETE_REPLACEMENT, MU_PLUS_LAMBDA
    }
}
