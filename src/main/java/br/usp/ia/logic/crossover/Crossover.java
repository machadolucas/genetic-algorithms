package br.usp.ia.logic.crossover;

import br.usp.ia.entity.Individual;

import java.util.List;

/**
 * Dicas de ideias para algoritmos: https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)
 */
public interface Crossover {

    List<Individual> doCrossover(Individual father, Individual mother);

}
