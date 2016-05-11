package br.usp.ia.logic.mutation.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.mutation.Mutation;
import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniformMutation implements Mutation {

    @Autowired
    private Random random;

    @Override
    public Individual mutate(final Individual individual, final double probability) {

        //Itera sobre todos os genes do cromossomo
        for (int i = 0; i < individual.getChromosomeLength(); i++) {

            //Calcula a probabilidade de mudanca para o gene atual
            if (this.random.getUniformGenerator().nextDouble() <= probability) {

                //Calcula aleatoriamente o novo valor para o gene
                final int gene = this.random.getUniformGenerator().nextInt(2);
                individual.setGene(i, gene);
            }
        }

        return individual;
    }
}
