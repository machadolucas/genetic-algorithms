package br.usp.ia.logic.mutation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import br.usp.ia.logic.mutation.Mutation;
import br.usp.ia.util.Random;

@Component
public class SimpleMutation implements Mutation {

    @Autowired
    private Random random;

    @Override
    public Individual mutate(final Individual individual, final double probability,
            final FitnessFunction fitnessFunction) {

        final int changingGene = this.random.getUniformGenerator().nextInt(individual.getChromosomeLength());

        // Calcula a probabilidade de mudanca para o gene atual
        if (this.random.getUniformGenerator().nextDouble() <= probability) {

            // Calcula aleatoriamente o novo valor para o gene
            final int gene = this.random.getUniformGenerator()
                    .nextInt(fitnessFunction.getTestInstance().getDimension() + 1);
            individual.setGene(changingGene, gene);
        }

        return individual;
    }
}
