package br.usp.ia.logging;

public interface Logging {
    void fitnessProgress(int generation, int generationScreenSkip, double populationTotal, double populationAvg,
                         double max, double min);
}
