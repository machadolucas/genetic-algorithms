package br.usp.ia.logic.crossover.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.crossover.Crossover;
import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TwoPointCrossover implements Crossover {

    @Autowired
    private Random random;

    @Override
    public List<Individual> doCrossover(final Individual father, final Individual mother) {
        // Seleciona a posicao de corte aleatoriamente
        // O primeiro corte ocorre entre 0 e o tamanho total do cromossomo
        final int firstCutPosition = this.random.getUniformGenerator().nextInt(Individual.chromosomeLength);
        // O segundo ocorre entre firstCutPosition e o tamanho total do cromossomo
        final int secondCutPosition = this.random.getUniformGenerator().nextInt(Individual.chromosomeLength -
                firstCutPosition) + firstCutPosition;

        //Inicializa cromossomo dos filhos
        final byte[] son1 = new byte[Individual.chromosomeLength];
        final byte[] son2 = new byte[Individual.chromosomeLength];

        // Preenche filhos ate a primeira posicao de corte
        for (int i = 0; i < firstCutPosition; i++) {
            son1[i] = father.getGene(i);
            son2[i] = mother.getGene(i);
        }
        // Preenche filhos da primeira ate a segunda posicao de corte
        for (int i = firstCutPosition; i < secondCutPosition; i++) {
            son1[i] = mother.getGene(i);
            son2[i] = father.getGene(i);
        }
        // Preenche filhos depois da segunda posicao de corte
        for (int i = secondCutPosition; i < Individual.chromosomeLength; i++) {
            son1[i] = father.getGene(i);
            son2[i] = mother.getGene(i);
        }

        // Lista de criancas que serao retornadas
        final List<Individual> children = new LinkedList<>();
        children.add(new Individual(son1));
        children.add(new Individual(son2));

        return children;
    }
}
