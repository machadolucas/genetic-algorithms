package br.usp.ia.entity;

import br.usp.ia.logic.fitness.FitnessFunction;

public class Individual {

    private final byte[] chromosome;

    private Double fitness;

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
        this.fitness = null;
    }

    public byte[] getChromosome() {
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
        final StringBuilder chromosome = new StringBuilder();
        for (int i = 0; i < fitnessFunction.getChromosomeLength(); i++) {
            chromosome.append(getGene(i));
        }
        return chromosome.toString();
    }

    /**
     * @return a representacao do valor X no cromossomo como valor decimal
     */
    public double getXDoubleRepresentation(final FitnessFunction fitnessFunction) {
        // Obtem o valor inteiro a partir da representacao binaria
        final int variableIntegerValue = Integer.parseInt(toString(fitnessFunction).substring( //
                0, fitnessFunction.getXLength()), 2);

        // Utiliza a funcao segundo Linden (2012) para converter um inteiro em decimal
        return fitnessFunction.getLowerLimit() + variableIntegerValue * //
                (fitnessFunction.getUpperLimit() - fitnessFunction.getLowerLimit()) / //
                (Math.pow(2, fitnessFunction.getXLength()) - 1);
    }

    /**
     * @return a representacao do valor Y no cromossomo como valor decimal
     */
    public double getYDoubleRepresentation(final FitnessFunction fitnessFunction) {
        // Obtem o valor inteiro a partir da representacao binaria de Y
        final int variableIntegerValue = Integer.parseInt(toString(fitnessFunction).substring( //
                fitnessFunction.getXLength(), fitnessFunction.getXLength() + fitnessFunction.getYLength()), 2);

        // Utiliza a funcao segundo Linden (2012) para converter um inteiro em decimal
        return fitnessFunction.getLowerLimit() + variableIntegerValue * //
                (fitnessFunction.getUpperLimit() - fitnessFunction.getLowerLimit()) / //
                (Math.pow(2, fitnessFunction.getYLength()) - 1);
    }

    /**
     * Usa a funcao inversa do getXDoubleRepresentation. obtida matematicamente
     * <p>
     * inteiroPositivo = (double - limInf) * (2^qtdeBits - 1) / (limSup - limInf)
     * converte inteiroPositivo em binario
     *
     * @param x
     * @param y
     * @param fitnessFunction
     */
    public void setChromosomeFromDoubleValues(final double x, final double y, final FitnessFunction fitnessFunction) {
        final int intX = Math.toIntExact(Math.round(( //
                x - fitnessFunction.getLowerLimit()) * //
                (Math.pow(2, fitnessFunction.getXLength()) - 1) / //
                (fitnessFunction.getUpperLimit() - fitnessFunction.getLowerLimit())));
        final int intY = Math.toIntExact(Math.round(( //
                y - fitnessFunction.getLowerLimit()) * //
                (Math.pow(2, fitnessFunction.getYLength()) - 1) / //
                (fitnessFunction.getUpperLimit() - fitnessFunction.getLowerLimit())));
        final String binaryString = Integer.toBinaryString(intX) + Integer.toBinaryString(intY);

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '0') {
                setGene(i, (byte) 0);
            } else {
                setGene(i, (byte) 1);
            }
        }

    }

    /**
     * Calcula a distancia entre dois individuos (Pitagoras)
     *
     * @param other
     * @param fitnessFunction
     * @return
     */
    public double distanceTo(final Individual other, final FitnessFunction fitnessFunction) {
        return Math.sqrt(Math.pow((other.getYDoubleRepresentation(fitnessFunction) - this.getYDoubleRepresentation
                (fitnessFunction)), 2) + Math.pow((other.getXDoubleRepresentation(fitnessFunction) - this
                .getXDoubleRepresentation(fitnessFunction)), 2));
    }

}
