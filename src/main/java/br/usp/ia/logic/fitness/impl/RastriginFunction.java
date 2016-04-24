package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.Population;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

/**
 * String para gerar o grafico no wolfram alpha:
 * plot -((x^2-10*cos(2*pi*x)+10)+(y^2-10*cos(2*pi*y)+10)), x=-5..5, y=-5..5
 */
@Component
public class RastriginFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        //Obtem os valores decimais a partir da representacao binaria
        final double x = individual.getXDoubleRepresentation(this);
        final double y = individual.getYDoubleRepresentation(this);

        //Calcula z na funcao Rastrigin a partir de x e y
        final double zx = Math.pow(x, 2) - 10 * Math.cos(2 * Math.PI * x) + 10;
        final double zy = Math.pow(y, 2) - 10 * Math.cos(2 * Math.PI * y) + 10;

        final double z = zx + zy;

        return -z;
    }

    @Override
    //Limite inferior do intervalo para maximizacao
    public double getLowerLimit() {
        return -5;
    }

    @Override
    //Limite superior do intervalo para maximizacao
    public double getUpperLimit() {
        return 5;
    }

    @Override
    public Individual getTheBestIndividual(final Population population) {
        //Obtem na populacao, o individuo com valor maximo
        return population.getIndividuals().stream().max((o1, o2) -> //
                Double.compare(o1.getFitness(this), o2.getFitness(this))).get();
    }

}
