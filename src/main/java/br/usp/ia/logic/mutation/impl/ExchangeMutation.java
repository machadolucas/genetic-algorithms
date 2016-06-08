package br.usp.ia.logic.mutation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import br.usp.ia.logic.mutation.Mutation;
import br.usp.ia.util.Random;

@Component
public class ExchangeMutation implements Mutation {

    @Autowired
    private Random random;

    @Override
    public Individual mutate(final Individual individual, final double probability,
            final FitnessFunction fitnessFunction) {

        // Calcula a probabilidade de mudanca para o gene atual
        if (this.random.getUniformGenerator().nextDouble() <= probability) {

            // Calcula aleatoriamente de qual gene vai mudar
            final int originalGeneIndex = this.random.getUniformGenerator().nextInt(individual.getChromosomeLength());

            // Calcula aleatoriamente para qual gene vai mudar
            final int changingGeneIndex = this.random.getUniformGenerator().nextInt(individual.getChromosomeLength());
            final Integer tmpGene = individual.getGene(changingGeneIndex);

            // Troca os genes
            individual.setGene(changingGeneIndex, individual.getGene(originalGeneIndex));
            individual.setGene(originalGeneIndex, tmpGene);
        }

        return individual;
    }
}
