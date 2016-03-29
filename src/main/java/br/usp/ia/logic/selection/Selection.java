package br.usp.ia.logic.selection;

import java.util.List;

/**
 * Created by lmachado on 3/22/16.
 *
 * Dicas de ideias para algoritmos: https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)
 */
public interface Selection {

    List<Double[]> selectInPopulation(List<Double[]> population, Double[] fitnesses, int selectionsNumber);

}
