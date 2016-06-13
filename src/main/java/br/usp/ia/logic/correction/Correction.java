package br.usp.ia.logic.correction;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;

import java.util.Set;

public interface Correction {

    Individual correct(Individual individual, FitnessFunction fitnessFunction, Set<Integer> missingSet, Set<Integer>
            errorSet);

}
