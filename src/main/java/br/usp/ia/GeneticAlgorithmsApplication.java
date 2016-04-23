package br.usp.ia;

import br.usp.ia.logic.GAAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GeneticAlgorithmsApplication {

    @Autowired
    GAAlgorithm gaAlgorithm;

    public static void main(final String[] args) {
        SpringApplication.run(GeneticAlgorithmsApplication.class, args);
    }

    @PostConstruct
    public void initialize() {
        this.gaAlgorithm.progressAlgorithm();
    }
}
