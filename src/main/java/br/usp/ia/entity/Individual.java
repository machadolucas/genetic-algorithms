package br.usp.ia.entity;

import br.usp.ia.logic.fitness.FitnessFunction;

public class Individual {

    public final static int chromosomeLength = 64;

    private final byte[] chromosome;

    private double fitness;

    /**
     * Construtor que cria um individuo com um cromossomo
     *
     * @param chromosome
     */
    public Individual(final byte[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * @param index indice
     * @return o valor do gene no indice especificado
     */
    public byte getGene(final int index) {
        return this.chromosome[index];
    }

    /**
     * Coloca o valor na posicao especificada do indice
     *
     * @param index indice
     * @param value valor a ser colocado
     */
    public void setGene(final int index, final byte value) {
        this.chromosome[index] = value;
        this.fitness = 0;
    }

    /**
     * @param fitnessFunction uma funcao fitness
     * @return o valor do fitness do individuo
     */
    public double getFitness(final FitnessFunction fitnessFunction) {
        if (this.fitness == 0) {
            this.fitness = fitnessFunction.calculate(this);
        }
        return this.fitness;
    }

    /**
     * @return a representacao binaria do cromossomo
     */
    @Override
    public String toString() {
        final StringBuilder chromosome = new StringBuilder();
        for (int i = 0; i < chromosomeLength; i++) {
            chromosome.append(getGene(i));
        }
        return chromosome.toString();
    }

}
