package br.usp.ia.logic.fitness;

import br.usp.ia.entity.Individual;

public interface FitnessFunction {

    double calculate(Individual individual);

    default int getChromosomeLength() {
        return getXLength() + getYLength();
    }

    double getLowerLimit();

    double getUpperLimit();

    default int getXLength() {
        return 10;
    }

    default int getYLength() {
        return 10;
    }

}
