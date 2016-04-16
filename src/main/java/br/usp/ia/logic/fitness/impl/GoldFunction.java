package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

@Component
public class GoldFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        //Obtem os valores decimais a partir da representacao binaria
        final double x = individual.getXDoubleRepresentation(this);
        final double y = individual.getXDoubleRepresentation(this);

        final double a = 1 + Math.pow((x + y + 1), 2) * //
                (19 - 14 * x + 3 * Math.pow(x, 2) - 14 * y + 6 * x * y + 3 * Math.pow(y, 2));
        final double b = 30 + Math.pow((2 * x - 3 * y), 2) * //
                (18 - 32 * x + 12 * Math.pow(x, 2) + 48 * y - 36 * x * y + 27 * Math.pow(y, 2));
        final double z = a * b;
        return -z;
    }

    @Override
    public double getLowerLimit() {
        return -2;
    }

    @Override
    public double getUpperLimit() {
        return -2;
    }

}
