package br.usp.ia.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "algorithm.params.population")
public class PopulationProperties {

    @Value("${size:10000}")
    private int size;
    @Value("${elitism:true}")
    private boolean elitism;
    @Value("${perpetuating:true}")
    private boolean perpetuating;

}
