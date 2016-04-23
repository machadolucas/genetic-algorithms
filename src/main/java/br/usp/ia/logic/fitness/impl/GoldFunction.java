package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.Population;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

@Component
public class GoldFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        //Obtem os valores decimais a partir da representacao binaria
        final double x = individual.getXDoubleRepresentation(this);
        final double y = individual.getYDoubleRepresentation(this);

        //Calcula z na funcao Gold a partir de x e y
        final double a = 1 + Math.pow((x + y + 1), 2) * //
                (19 - 14 * x + 3 * Math.pow(x, 2) - 14 * y + 6 * x * y + 3 * Math.pow(y, 2));
        final double b = 30 + Math.pow((2 * x - 3 * y), 2) * //
                (18 - 32 * x + 12 * Math.pow(x, 2) + 48 * y - 36 * x * y + 27 * Math.pow(y, 2));
        final double z = a * b;
        return -z;
    }

    @Override
    //Limite inferior do intervalo para minimizacao
    public double getLowerLimit() {
        return -2;
    }

    @Override
    //Limite superior do intervalo para minimizacao
    public double getUpperLimit() {
        return -2;
    }

    @Override
    public Individual getTheBestIndividual(final Population population) {
        //Obtem na populacao, o individuo com valor minimo (funcao de minimizacao)
        return population.getIndividuals().stream().min((o1, o2) -> //
                Double.compare(o1.getFitness(this), o2.getFitness(this))).get();
    }

}
