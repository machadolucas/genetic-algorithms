package br.usp.ia.logic.fitness;

import br.usp.ia.entity.Individual;

public interface FitnessFunction {

    double calculate(Individual individual);

}
