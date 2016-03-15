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
@ConfigurationProperties(prefix = "algorithm.params.population")
public class Population {

    @Value("${size:10000}")
    private int size;
    @Value("${elitism:true}")
    private boolean elitism;
    @Value("${perpetuating:true}")
    private boolean perpetuating;

}
