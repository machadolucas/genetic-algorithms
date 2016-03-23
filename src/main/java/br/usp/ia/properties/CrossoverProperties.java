package br.usp.ia.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by richardcsantana on 15/03/16.
 */
@Data
@Component
@ConfigurationProperties(prefix = "algorithm.params.crossover")
public class CrossoverProperties {

    @Value("${energy:50}")
    private double energy;
    private String strategy;
}