package br.usp.ia.logic.crossover;

import java.util.List;

/**
 * Created by lmachado on 3/22/16.
 *
 * Dicas de ideias para algoritmos: https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)
 */
public interface Crossover {

    List<Double[]> doCrossover(Double[] father, Double[] mother);

}
