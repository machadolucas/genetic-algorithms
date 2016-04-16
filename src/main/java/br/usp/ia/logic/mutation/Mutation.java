package br.usp.ia.logic.mutation;

import br.usp.ia.entity.Individual;

public interface Mutation {

    Individual mutate(Individual individual, double probability);

}
