package br.usp.ia.logic.crossover.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.crossover.Crossover;
import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class UniformCrossover implements Crossover {

    @Autowired
    private Random random;

    @Override
    public List<Individual> doCrossover(final Individual father, final Individual mother) {

        //Inicializa cromossomo dos filhos
        final byte[] son1 = new byte[father.getChromosomeLength()];
        final byte[] son2 = new byte[father.getChromosomeLength()];

        // Preenche filhos
        for (int i = 0; i < father.getChromosomeLength(); i++) {
            // Seleciona para cada gene, de qual dos pais sera herdado com prob de 50%
            final boolean firstSonFromFather = this.random.getUniformGenerator().nextBoolean();
            if (firstSonFromFather) {
                son1[i] = father.getGene(i);
                son2[i] = mother.getGene(i);
            } else {
                son1[i] = mother.getGene(i);
                son2[i] = father.getGene(i);
            }
        }

        // Lista de criancas que serao retornadas
        final List<Individual> children = new LinkedList<>();
        children.add(new Individual(son1));
        children.add(new Individual(son2));

        return children;
    }
}
