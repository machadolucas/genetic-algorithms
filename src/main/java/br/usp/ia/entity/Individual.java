package br.usp.ia.entity;

import br.usp.ia.logic.fitness.FitnessFunction;

import java.util.Arrays;

public class Individual {

    private final int[] chromosome;

    private Double fitness;

    /**
     * Construtor que cria um individuo com um cromossomo
     *
     * @param chromosome
     */
    public Individual(final int[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * @param index indice
     * @return o valor do gene no indice especificado
     */
    public int getGene(final int index) {
        return this.chromosome[index];
    }

    /**
     * Coloca o valor na posicao especificada do indice
     *
     * @param index indice
     * @param value valor a ser colocado
     */
    public void setGene(final int index, final int value) {
        this.chromosome[index] = value;
        this.fitness = null;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    /**
     * @param fitnessFunction uma funcao fitness
     * @return o valor do fitness do individuo
     */
    public double getFitness(final FitnessFunction fitnessFunction) {
        if (this.fitness == null) {
            this.fitness = fitnessFunction.calculate(this);
        }
        return this.fitness;
    }

    /**
     * @return comprimento do cromossomo
     */
    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    /**
     * @return a representacao binaria do cromossomo
     */
    public String toString(final FitnessFunction fitnessFunction) {
        return Arrays.toString(this.chromosome);
    }

    /**
     * Calcula a distancia entre dois individuos (Pitagoras)
     *
     * @param other
     * @param fitnessFunction
     * @return
     */
    public double distanceTo(final Individual other, final FitnessFunction fitnessFunction) {
        return 0; // TODO
        // return Math.sqrt(Math.pow((other.getYDoubleRepresentation(fitnessFunction) - this.getYDoubleRepresentation
        // (fitnessFunction)), 2) + Math.pow((other.getXDoubleRepresentation(fitnessFunction) - this
        // .getXDoubleRepresentation(fitnessFunction)), 2));
    }

}
