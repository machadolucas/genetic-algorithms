package br.usp.ia.logic.crossover.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.usp.ia.logic.crossover.Crossover;
import br.usp.ia.util.Random;

/**
 * Created by lmachado on 3/22/16.
 */
@Component
public class UniformCrossover implements Crossover {

    @Autowired
    Random random;

    @Override
    public List<Double[]> doCrossover(final Double[] father, final Double[] mother) {

        // Lista de criancas que serao retornadas
        final List<Double[]> children = new LinkedList<>();

        // Inicializa filhos
        final Double[] son1 = new Double[father.length];
        final Double[] son2 = new Double[father.length];

        // Preenche filhos
        for (int i = 0; i < father.length; i++) {
            // Seleciona para cada gene, de qual dos pais sera herdado com prob de 50%
            boolean firstSonfromFather = random.getUniformGenerator().nextBoolean();
            if (firstSonfromFather) {
                son1[1] = father[i];
                son2[i] = mother[i];
            } else {
                son1[i] = mother[i];
                son2[1] = father[i];
            }
        }

        children.add(son1);
        children.add(son2);

        return children;
    }
}
