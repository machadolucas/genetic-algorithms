package br.usp.ia.logic.crossover.impl;

import br.usp.ia.logic.crossover.Crossover;
import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lmachado on 3/22/16.
 */
@Component
public class UniformCrossover implements Crossover {

    @Autowired
    Random random;

    @Override
    public List<String> doCrossover(final String father, final String mother) {

        // Lista de criancas que serao retornadas
        final List<String> children = new LinkedList<>();

        // Inicializa filhos
        final char[] son1 = new char[father.length()];
        final char[] son2 = new char[father.length()];

        // Preenche filhos
        for (int i = 0; i < father.length(); i++) {
            // Seleciona para cada gene, de qual dos pais sera herdado com prob de 50%
            final boolean firstSonfromFather = this.random.getUniformGenerator().nextBoolean();
            if (firstSonfromFather) {
                son1[1] = father.charAt(i);
                son2[i] = mother.charAt(i);
            } else {
                son1[i] = mother.charAt(i);
                son2[1] = father.charAt(i);
            }
        }

        children.add(new String(son1));
        children.add(new String(son2));

        return children;
    }
}
