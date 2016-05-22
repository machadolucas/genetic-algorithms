package br.usp.ia.entity;

import br.usp.ia.logic.fitness.FitnessFunction;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Population {

    private List<Individual> individuals = new LinkedList<>();

    public Population(final List<Individual> individuals) {
        this.individuals = individuals;
    }

    public Individual getBest(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o individuo com valor minimo
        return this.individuals.stream().min((individual1, individual2) -> //
                Integer.compare(individual1.getFitness(fitnessFunction), //
                        individual2.getFitness(fitnessFunction))).get();
    }

    public double getMaxFitness(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o valor do individuo com maximo fitness
        return this.individuals.stream().max((individual1, individual2) -> //
                Integer.compare(individual1.getFitness(fitnessFunction), //
                        individual2.getFitness(fitnessFunction))) //
                .get().getFitness(fitnessFunction);
    }

    public double getMinFitness(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o valor do individuo com minimo fitness
        return this.individuals.stream().min((individual1, individual2) -> //
                Integer.compare(individual1.getFitness(fitnessFunction), //
                        individual2.getFitness(fitnessFunction))) //
                .get().getFitness(fitnessFunction);
    }

    public double getAverageFitness(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o valor da media aritmetica simples do fitness dos individuos
        return this.individuals.stream().mapToInt(individual -> //
                individual.getFitness(fitnessFunction)).average().getAsDouble();
    }

    public double getTotalFitness(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o valor da soma do fitness dos individuos
        return this.individuals.stream().mapToInt(individual -> //
                individual.getFitness(fitnessFunction)).sum();
    }

    public int size() {
        return this.individuals.size();
    }

}
