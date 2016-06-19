package br.usp.ia.entity;

import br.usp.ia.logic.fitness.FitnessFunction;

import java.util.*;

public class Individual {

    private final Integer[] chromosome;

    private Integer fitness;

    /**
     * Construtor que cria um individuo com um cromossomo
     *
     * @param chromosome
     */
    public Individual(final Integer[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * @param index indice
     * @return o valor do gene no indice especificado
     */
    public Integer getGene(final int index) {
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

    public Integer[] getChromosome() {
        return this.chromosome;
    }

    /**
     * @param fitnessFunction uma funcao fitness
     * @return o valor do fitness do individuo
     */
    public int getFitness(final FitnessFunction fitnessFunction) {
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
     * @return a representacao do cromossomo como texto (array)
     */
    public String toString(final FitnessFunction fitnessFunction) {
        return Arrays.toString(this.chromosome);
    }

    public Map<Integer, List<Integer>> getObjectRepresentation(final FitnessFunction fitnessFunction) {
        final Map<Integer, List<Integer>> objectRepresentation = new HashMap<>();

        final int trucksAmount = fitnessFunction.getTestInstance().getTrucks();
        //Inicializa as listas de caminhos por caminhoes
        for (int i = 0; i < trucksAmount; i++) {
            final List<Integer> path = new LinkedList<>();
            objectRepresentation.put(i, new LinkedList<>(path));
        }

        //Distribui sequencialmente os genes entre os caminhoes
        final int geneIndex = 0;
        for (final int geneValue : this.chromosome) {
            objectRepresentation.get(geneIndex % trucksAmount).add(geneValue);
        }

        return objectRepresentation;
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
