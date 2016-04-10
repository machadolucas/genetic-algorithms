package br.usp.ia.logic.selection;

import java.util.List;

/**
 * Created by lmachado on 3/22/16.
 */
public interface Selection {

    List<String> selectInPopulation(List<String> population, Double[] fitnesses, int selectionsNumber);

}
