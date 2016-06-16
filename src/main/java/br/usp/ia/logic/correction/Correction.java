package br.usp.ia.logic.correction;

import java.util.List;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;

public interface Correction {

    void correct(Individual individual, FitnessFunction fitnessFunction, List<Integer> missingList,
            List<Integer> errorList);

}
