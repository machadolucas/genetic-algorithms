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
@ConfigurationProperties(prefix = "algorithm.params.mutation")
public class MutationProperties {

    @Value("${energy:0.001}")
    private double energy;
    private MutationStrategy strategy;

    @Override
    public String toString() {
        return "{\"MutationProperties\":{" +
                "\"energy\":\"" + this.energy +
                "\",\"strategy\":\"" + this.strategy +
                "\"}}";
    }

    @AllArgsConstructor
    public enum MutationStrategy {
        SIMPLE("simpleMutation"), UNIFORM("uniformMutation"), EXCHANGE("exchangeMutation");

        @Getter
        @Setter
        private String name;
    }
}

