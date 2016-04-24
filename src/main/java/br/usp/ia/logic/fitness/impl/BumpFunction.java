package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.Population;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

/**
 * String para gerar o grafico no wolfram alpha:
 * plot abs(((cos(x)^4+cos(y)^4)-(2*(cos(x)^2)*(cos(y)^2)))/sqrt(x^2+2*y^2)), x=0..10, y=0..10
 */
@Component
public class BumpFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        //Obtem os valores decimais a partir da representacao binaria
        final double x = individual.getXDoubleRepresentation(this);
        final double y = individual.getYDoubleRepresentation(this);

        //Calcula z na funcao Bump a partir de x e y
        final double temp0 = Math.pow(Math.cos(x), 4) + Math.pow(Math.cos(y), 4);
        final double temp1 = 2 * Math.pow(Math.cos(x), 2) * Math.pow(Math.cos(y), 2);
        final double temp2 = Math.sqrt(Math.pow(x, 2) + 2 * Math.pow(y, 2));
        final double z = Math.abs((temp0 - temp1) / temp2);

        return z;
    }

    @Override
    //Limite inferior do intervalo para minimizacao
    public double getLowerLimit() {
        return 0;
    }

    @Override
    //Limite superior do intervalo para minimizacao
    public double getUpperLimit() {
        return 10;
    }

    @Override
    public Individual getTheBestIndividual(final Population population) {
        //Obtem na populacao, o individuo com valor maximo
        return population.getIndividuals().stream().max((individual1, individual2) -> //
                Double.compare(individual1.getFitness(this), individual2.getFitness(this))).get();
    }

}
