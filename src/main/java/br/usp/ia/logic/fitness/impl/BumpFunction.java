package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

@Component
public class BumpFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        //Obtem os valores decimais a partir da representacao binaria
        final double x = individual.getXDoubleRepresentation(this);
        final double y = individual.getXDoubleRepresentation(this);

        //TODO

        return 0;
    }

    @Override
    public double getLowerLimit() {
        return 0;
    }

    @Override
    public double getUpperLimit() {
        return 10;
    }

}
