package br.usp.ia.logic.selection.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.Population;
import br.usp.ia.logic.fitness.FitnessFunction;
import br.usp.ia.logic.selection.Selection;
import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class RouletteSelection implements Selection {

    @Autowired
    private Random random;

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
     * @param population       Lista com os individuos da populacao
     * @param selectionsAmount Quantidade de selecoes a serem retornadas da populacao
     * @param fitnessFunction  Funcao fitness utilizada para avaliar os individuos
     * @return Uma lista com os individuos selecionados da população
     */
    @Override
    public List<Individual> selectInPopulation(final Population population, final int selectionsAmount, final
    FitnessFunction fitnessFunction) {

        final double maxFitness;
        // Obtem o valor maximo de fitness da populacao
        maxFitness = population.getMaxFitness(fitnessFunction);

        // Cada posicao desse array guarda quantas vezes cada individuo foi selecionado
        final int[] selections = new int[population.size()];

        // Algoritmo de aceitacao estocastica para selecionar individuos
        int index = 0;
        for (int i = 0; i < selectionsAmount; i++) {
            //Para cada um dos individuos que precisam ser selecionados, calcula quem sera o escolhido
            boolean accepted = false;
            while (!accepted) {
                //Ate escolher alguem, calcula aleatoriamente pelo fitness/maxFitness
                index = this.random.getUniformGenerator().nextInt(population.size());
                final double fitness;
                    // Valor de fitness do individuo
                    fitness = population.getIndividuals().get(index).getFitness(fitnessFunction);
                // Mudei de < para >= pois problema é minimizar o fitness. Isso inverte a probabilidade
                if (this.random.getUniformGenerator().nextDouble() >=
                        fitness / maxFitness) {
                    accepted = true;
                }
            }
            // Quando accepted vira true, incrementa a contagem com individuo selecionado
            selections[index]++;
        }

        // Inicializa lista da populacao selecionada
        final List<Individual> resultingPopulation = new LinkedList<>();

        // Preenche lista da populacao selecionada com individuos selecionados
        for (int i = 0; i < selections.length; i++) {
            for (int selectionsCount = 0; selectionsCount < selections[i]; selectionsCount++) {
                resultingPopulation.add(population.getIndividuals().get(i));
            }
        }

        return resultingPopulation;
    }
}
