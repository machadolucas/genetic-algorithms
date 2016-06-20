package br.usp.ia.logic.crossover.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.crossover.Crossover;
import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class OnePointCrossover implements Crossover {

    @Autowired
    private Random random;

    @Override
    public List<Individual> doCrossover(final Individual father, final Individual mother) {
        // Seleciona a posicao de corte aleatoriamente
        final int cutPosition = this.random.getUniformGenerator().nextInt(father.getChromosomeLength());

        //Inicializa cromossomo dos filhos
        final Integer[] son1 = new Integer[father.getChromosomeLength()];
        final Integer[] son2 = new Integer[father.getChromosomeLength()];

        //Preenche filhos ate a posicao de corte
        for (int i = 0; i < cutPosition; i++) {
            son1[i] = father.getGene(i);
            son2[i] = mother.getGene(i);
        }
        //Preenche filhos depois da posicao de corte
        for (int i = cutPosition; i < father.getChromosomeLength(); i++) {
            son1[i] = mother.getGene(i);
            son2[i] = father.getGene(i);
        }

        // Lista de criancas que serao retornadas
        final List<Individual> children = new LinkedList<>();
        children.add(new Individual(son1));
        children.add(new Individual(son2));

        return children;
    }
}
