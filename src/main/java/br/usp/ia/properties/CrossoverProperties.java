package br.usp.ia.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "algorithm.params.crossover")
public class CrossoverProperties {

    @Value("${energy:0.8}")
    private double energy;
    private CrossoverStrategy strategy;

    @Override
    public String toString() {
        return "{\"CrossoverProperties\":{" +
                "\"energy\":\"" + this.energy +
                "\",\"strategy\":\"" + this.strategy +
                "\"}}";
    }

    @AllArgsConstructor
    public enum CrossoverStrategy {
        ONE_POINT("onePointCrossover"), TWO_POINT("twoPointCrossover"), UNIFORM("uniformCrossover");

        @Getter
        @Setter
        private String name;
    }
}
