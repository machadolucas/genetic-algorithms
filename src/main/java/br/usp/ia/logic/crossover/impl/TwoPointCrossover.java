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
public class TwoPointCrossover implements Crossover {

    @Autowired
    Random random;

    @Override
    public List<String> doCrossover(final String father, final String mother) {
        // Seleciona a posicao de corte aleatoriamente
        // O primeiro corte ocorre entre 0 e o tamanho total do cromossomo
        final int firstCutPosition = this.random.getUniformGenerator().nextInt(father.length());
        // O segundo ocorre entre firstCutPosition e o tamanho total do cromossomo
        final int secondCutPosition = this.random.getUniformGenerator().nextInt(father.length() - firstCutPosition) +
                firstCutPosition;

        // Lista de criancas que serao retornadas
        final List<String> children = new LinkedList<>();

        // Inicializa filhos
        final char[] son1 = new char[father.length()];
        final char[] son2 = new char[father.length()];

        // Preenche filhos ate a primeira posicao de corte
        for (int i = 0; i < firstCutPosition; i++) {
            son1[i] = father.charAt(i);
            son2[i] = mother.charAt(i);
        }
        // Preenche filhos da primeira ate a segunda posicao de corte
        for (int i = firstCutPosition; i < secondCutPosition; i++) {
            son1[i] = mother.charAt(i);
            son2[i] = father.charAt(i);
        }
        // Preenche filhos depois da segunda posicao de corte
        for (int i = secondCutPosition; i < father.length(); i++) {
            son1[i] = father.charAt(i);
            son2[i] = mother.charAt(i);
        }

        children.add(new String(son1));
        children.add(new String(son2));

        return children;
    }
}
