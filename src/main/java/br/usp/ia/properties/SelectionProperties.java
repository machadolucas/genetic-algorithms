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

    @Value("${elitism:true}")
    private boolean elitism;
    private SelectionStrategy strategy;

    @Override
    public String toString() {
        return "{\"SelectionProperties\":{" +
                "\"elitism\":\"" + this.elitism +
                "\",\"strategy\":\"" + this.strategy +
                "\"}}";
    }

    @AllArgsConstructor
    public enum SelectionStrategy {
        ROULETTE("rouletteSelection");

        @Getter
        @Setter
        private String name;
    }
}

