package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

@Component
public class GoldFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        //TODO
        return 0;
    }
}
