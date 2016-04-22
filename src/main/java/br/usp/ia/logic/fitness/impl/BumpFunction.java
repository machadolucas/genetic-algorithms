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
        final double y = individual.getYDoubleRepresentation(this);

        final double temp0 = Math.pow(Math.cos(x), 4) + Math.pow(Math.cos(y), 4);
        final double temp1 = 2 * Math.pow(Math.cos(x), 2) * Math.pow(Math.cos(y), 2);
        final double temp2 = Math.sqrt(Math.pow(x, 2) + 2 * Math.pow(y, 2));
        final double z = Math.abs((temp0 - temp1) / temp2);
        final double result = -z;

        return result;
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
