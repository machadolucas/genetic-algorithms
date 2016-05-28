package br.usp.ia.logging;

public interface Logging {
    void fitnessProgress(int generation, int generationScreenSkip, Integer populationTotal, double populationAvg,
                         Integer max, Integer min);

    void print(String string);
}
