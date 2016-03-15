package br.usp.ia.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by richardcsantana on 15/03/16.
 */
@Data
@Component
@ConfigurationProperties(prefix = "algorithm.params.mutation")
public class Mutation {

    private double energy;
    private String strategy;
}
}
