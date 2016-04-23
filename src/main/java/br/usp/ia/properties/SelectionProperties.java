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
@ConfigurationProperties(prefix = "algorithm.params.selection")
public class SelectionProperties {

    @Value("${energy:1}")
    private double energy;
    private SelectionStrategy strategy;

    @AllArgsConstructor
    public enum SelectionStrategy {
        ROULETTE("rouletteSelection");

        @Getter
        @Setter
        private String name;
    }
}

