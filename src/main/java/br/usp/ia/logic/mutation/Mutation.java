package br.usp.ia.logic.mutation;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;

public interface Mutation {

    Individual mutate(Individual individual, double probability, FitnessFunction fitnessFunction);

}
