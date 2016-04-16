package br.usp.ia.entity;

import br.usp.ia.logic.GAAlgorithm;
import br.usp.ia.logic.fitness.FitnessFunction;

import java.math.BigInteger;

public class Individual {

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
        for (int i = 0; i < GAAlgorithm.chromosomeLength; i++) {
            chromosome.append(getGene(i));
        }
        return chromosome.toString();
    }

    /**
     * @return a representacao do valor X no cromossomo como valor decimal
     */
    public double getXDoubleRepresentation() {
        return Double.longBitsToDouble(new BigInteger(toString().substring(0, 64), 2).longValue());
    }

    /**
     * @return a representacao do valor Y no cromossomo como valor decimal
     */
    public double getYDoubleRepresentation() {
        return Double.longBitsToDouble(new BigInteger(toString().substring(64), 2).longValue());
    }

    /**
     * Coloca a representacao do cromossomo como valor decimal no formato binario
     */
    public void setWithDoubleRepresentation(final double x, final double y) {
        String xBinaryRepresentation = Long.toBinaryString(Double.doubleToRawLongBits(x));
        if (x >= 0) {
            //Se for numero positivo, o primeiro digito binario eh omitido por ser zero.
            //Aqui adicionamos pra representacao sempre ter comprimento de 64 caracteres.
            xBinaryRepresentation = "0" + xBinaryRepresentation;
        }
        for (int i = 0; i < xBinaryRepresentation.length(); i++) {
            if (xBinaryRepresentation.charAt(i) == '0') {
                setGene(i, (byte) 0);
            } else {
                setGene(i, (byte) 1);
            }
        }

        String yBinaryRepresentation = Long.toBinaryString(Double.doubleToRawLongBits(y));
        if (y >= 0) {
            //Se for numero positivo, o primeiro digito binario eh omitido por ser zero.
            //Aqui adicionamos pra representacao sempre ter comprimento de 64 caracteres.
            yBinaryRepresentation = "0" + yBinaryRepresentation;
        }
        for (int i = 64; i < yBinaryRepresentation.length(); i++) {
            if (yBinaryRepresentation.charAt(i - 64) == '0') {
                setGene(i, (byte) 0);
            } else {
                setGene(i, (byte) 1);
            }
        }
    }

}
