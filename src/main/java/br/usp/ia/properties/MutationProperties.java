package br.usp.ia.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "algorithm.params.mutation")
public class MutationProperties {

    @Value("${energy:1}")
    private double energy;
    private String strategy;
}

