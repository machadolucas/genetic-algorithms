package br.usp.ia.logging;

import br.usp.ia.logic.fitness.FitnessFunction;

public interface Logging {
    void fitnessProgress(int generation, int generationScreenSkip, double populationTotal, double populationAvg,
            double max, double min, FitnessFunction fitnessFunction);

    void print(String string);
}
