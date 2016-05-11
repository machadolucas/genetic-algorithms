package br.usp.ia.logic.fitness.impl;

import org.springframework.stereotype.Component;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;

@Component
public class RouteFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {

            return 0;
    }

}
