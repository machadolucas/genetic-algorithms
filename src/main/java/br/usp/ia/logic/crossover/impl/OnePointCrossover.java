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
public class OnePointCrossover implements Crossover {

    @Autowired
    Random random;

    @Override
    public List<String> doCrossover(final String father, final String mother) {
        // Seleciona a posicao de corte aleatoriamente
        final int cutPosition = this.random.getUniformGenerator().nextInt(father.length());

        // Lista de criancas que serao retornadas
        final List<String> children = new LinkedList<>();

        //Inicializa filhos
        final char[] son1 = new char[father.length()];
        final char[] son2 = new char[father.length()];

        //Preenche filhos ate a posicao de corte
        for (int i = 0; i < cutPosition; i++) {
            son1[i] = father.charAt(i);
            son2[i] = mother.charAt(i);
        }
        //Preenche filhos depois da posicao de corte
        for (int i = cutPosition; i < father.length(); i++) {
            son1[i] = mother.charAt(i);
            son2[i] = father.charAt(i);
        }

        children.add(new String(son1));
        children.add(new String(son2));

        return children;
    }
}
