package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

@Component
public class RastriginFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        //Obtem os valores decimais a partir da representacao binaria
        final double x = individual.getXDoubleRepresentation(this);
        final double y = individual.getXDoubleRepresentation(this);

        //Calcula a funcao
        final double zx = Math.pow(x, 2.0) - 10.0 * Math.cos(2 * Math.PI * x);
        final double zy = Math.pow(y, 2.0) - 10.0 * Math.cos(2 * Math.PI * y);

        return zx + zy; //TODO: Retornar -(zx + zy)? Pois eh maximizacao
    }

    @Override
    public double getLowerLimit() {
        return -5;
    }

    @Override
    public double getUpperLimit() {
        return 5;
    }

}
