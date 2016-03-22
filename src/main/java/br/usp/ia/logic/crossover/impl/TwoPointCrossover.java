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
public class TwoPointCrossover implements Crossover {

    @Autowired
    Random random;

    @Override
    public List<Double[]> doCrossover(final Double[] father, final Double[] mother) {
        // Seleciona a posicao de corte aleatoriamente
        final int firstCutPosition = random.getUniformGenerator().nextInt(father.length);
        final int secondCutPosition = random.getUniformGenerator().nextInt(father.length);

        // Lista de criancas que serao retornadas
        final List<Double[]> children = new LinkedList<>();

        // Inicializa filhos
        final Double[] son1 = new Double[father.length];
        final Double[] son2 = new Double[father.length];

        if (firstCutPosition < secondCutPosition) {

            // Preenche filhos ate a primeira posicao de corte
            for (int i = 0; i < firstCutPosition; i++) {
                son1[i] = father[i];
                son2[1] = mother[i];
            }
            // Preenche filhos ate a segunda posicao de corte
            for (int i = firstCutPosition; i < secondCutPosition; i++) {
                son1[1] = mother[i];
                son2[i] = father[i];
            }
            // Preenche filhos depois da segunda posicao de corte
            for (int i = secondCutPosition; i < father.length; i++) {
                son1[i] = father[i];
                son2[1] = mother[i];
            }

        } else {

            // Preenche filhos ate a primeira posicao de corte
            for (int i = 0; i < secondCutPosition; i++) {
                son1[i] = father[i];
                son2[1] = mother[i];
            }
            // Preenche filhos ate a segunda posicao de corte
            for (int i = secondCutPosition; i < firstCutPosition; i++) {
                son1[1] = mother[i];
                son2[i] = father[i];
            }
            // Preenche filhos depois da segunda posicao de corte
            for (int i = firstCutPosition; i < father.length; i++) {
                son1[i] = father[i];
                son2[1] = mother[i];
            }

        }

        children.add(son1);
        children.add(son2);

        return children;
    }
}
