package br.usp.ia.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "algorithm.params.fitness")
public class FitnessProperties {

    private FitnessStrategy strategy;

    @Override
    public String toString() {
        return "{\"FitnessProperties\":{" +
                "\"strategy\":\"" + this.strategy +
                "\"}}";
    }

    @AllArgsConstructor
    public enum FitnessStrategy {
        BUMP("bumpFunction"), GOLD("goldFunction"), RASTRIGIN("rastriginFunction");

        @Getter
        @Setter
        private String name;
    }

}

