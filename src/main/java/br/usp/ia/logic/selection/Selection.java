package br.usp.ia.logic.selection;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.Population;
import br.usp.ia.logic.fitness.FitnessFunction;

import java.util.List;

public interface Selection {

    List<Individual> selectInPopulation(Population population, int selectionsNumber, FitnessFunction fitnessFunction);

}
