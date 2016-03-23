package br.usp.ia.logic.selection.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.usp.ia.logic.selection.Selection;
import br.usp.ia.util.Random;

/**
 * Created by lmachado on 3/23/16.
 */
@Component
public class RouletteSelection implements Selection {

    @Autowired
    Random random;

    /**
     * Implementa a seleção de roleta pelo algoritmo O(1) de aceitacao estocastica
     * <p>
     * Adam Lipowski, Dorota Lipowska, Roulette-wheel selection via stochastic acceptance, Physica A: Statistical
     * Mechanics and its Applications, Volume 391, Issue 6, 15 March 2012, Pages 2193-2196, ISSN 0378-4371,
     * http://dx.doi.org/10.1016/j.physa.2011.12.004.
     * (http://www.sciencedirect.com/science/article/pii/S0378437111009010)
     * <p>
     * http://arxiv.org/pdf/1109.3627.pdf
     *
     * @param population
     *            Lista com a populacao
     * @param fitnesses
     *            Array com os valores de fitness da populacao, na mesma ordem que population
     * @param selectionsAmount
     *            Quantidade de selecoes a serem retornadas da populacao
     * @return Uma lista com os elementos da população
     */
    @Override
    public List<Double[]> selectInPopulation(final List<Double[]> population, final Double[] fitnesses,
            final int selectionsAmount) {

        // Obtem o valor maximo de fitness da populacao
        Double maxFitness = 0d;
        for (final Double fitness : fitnesses) {
            if (maxFitness < fitness) {
                maxFitness = fitness;
            }
        }

        // Cada posicao desse array guarda quantas vezes cada individuo foi selecionado
        final int[] selections = new int[fitnesses.length];

        // algoritmo de aceitacao estocastica para selecionar inidividuos
        int index = 0;
        for (int i = 0; i < selectionsAmount; i++) {
            boolean accepted = false;
            while (!accepted) {
                index = this.random.getUniformGenerator().nextInt(fitnesses.length);
                if (this.random.getUniformGenerator().nextDouble() < fitnesses[index] / maxFitness) {
                    accepted = true;
                }
            }
            // Quando accepted vira true, incrementa a contagem com individuo selecionado
            selections[index]++;
        }

        // Inicializa lista da populacao selecionada
        final List<Double[]> resultingPopulation = new LinkedList<>();

        // Preenche lista da populacao selecionada com individuos
        for (final int person : selections) {
            for (int selectionCount = 0; selectionCount < selections[person]; selectionCount++) {
                resultingPopulation.add(population.get(selections[person]));
            }
        }

        return resultingPopulation;
    }
}
