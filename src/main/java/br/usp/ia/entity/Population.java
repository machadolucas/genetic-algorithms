package br.usp.ia.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.usp.ia.logic.fitness.FitnessFunction;
import br.usp.ia.util.Random;

@Data
public class Population {

    @Autowired
    Random random;

    private List<Individual> individuals = new LinkedList<>();

    public Population(final List<Individual> individuals) {
        this.individuals = individuals;
    }

    public Individual getBest(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o individuo com valor maximo
        return this.individuals.stream().max((individual1, individual2) -> //
        Double.compare(individual1.getFitness(fitnessFunction), individual2.getFitness(fitnessFunction))).get();
    }

    public double getMaxFitness(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o valor do individuo com maximo fitness
        return this.individuals.stream().max((individual1, individual2) -> //
        Double.compare(individual1.getFitness(fitnessFunction), individual2.getFitness(fitnessFunction))) //
                .get().getFitness(fitnessFunction);
    }

    public double getMinFitness(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o valor do individuo com minimo fitness
        return this.individuals.stream().min((individual1, individual2) -> //
        Double.compare(individual1.getFitness(fitnessFunction), individual2.getFitness(fitnessFunction))) //
                .get().getFitness(fitnessFunction);
    }

    public double getAverageFitness(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o valor da media aritmetica simples do fitness dos individuos
        return this.individuals.stream().mapToDouble(individual -> individual.getFitness(fitnessFunction)) //
                .average().getAsDouble();
    }

    public double getTotalFitness(final FitnessFunction fitnessFunction) {
        // Obtem na populacao, o valor da soma do fitness dos individuos
        return this.individuals.stream().mapToDouble(individual -> individual.getFitness(fitnessFunction)) //
                .sum();
    }

    public int size() {
        return this.individuals.size();
    }

    /**
     * Calcula se ha convergencia na populacao por k-means
     * 
     * @param fitnessFunction
     * @return true se houver convergencia
     */
    public boolean hasConvergence(final FitnessFunction fitnessFunction) {

        // Tenta definir qua a quantidade de clusters deve ser 10% do tamanho da populacao
        int k = Math.toIntExact(Math.round(size() * 0.1));
        if (k < 3) { // para garantir que haja no minimo 3 grupos
            k = 3;
        } else if (k > 10) { // ou que haja no maximo 10
            k = 10;
        }

        // Gera k centroides aleatoriamente.
        final List<Individual> centroids = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            centroids.add(this.random.nextBinaryIndividual(fitnessFunction));
        }

        // Clusters: centroide e lista de pontos
        final Map<Individual, List<Individual>> clusters = new HashMap<>();
        centroids.forEach(c -> clusters.put(c, new LinkedList<>()));

        boolean finish = false;

        // Faz a iteracao do k-means, recalculando o centroide a cada passo
        while (!finish) {
            // Limpa a lista de pontos de cada centroide
            clusters.forEach((cent, points) -> {
                points.clear();
            });

            // Guarda os ultimos centroides antes de serem alterados, para calcular se houve dieferenca depois
            final List<Individual> lastCentroids = centroids.stream()
                    .map(individual -> new Individual(individual.getChromosome())).collect(Collectors.toList());

            // Coloca os individuos no cluster mais proximo
            for (final Individual individual : this.individuals) {

                final Individual closerCluster = centroids.stream().min((c1, c2) -> //
                Double.compare( //
                        c1.distanceTo(individual, fitnessFunction), //
                        c2.distanceTo(individual, fitnessFunction)) //
                ).get();

                clusters.get(closerCluster).add(individual);
            }

            // Calcula novos centroides.
            centroids.forEach(c -> {
                final List<Individual> clusterIndividuals = clusters.get(c);
                final int amountOfIndividuals = clusterIndividuals.size();

                if (amountOfIndividuals > 0) {
                    double sumX = 0;
                    double sumY = 0;
                    for (final Individual individual : clusterIndividuals) {
                        sumX += individual.getXDoubleRepresentation(fitnessFunction);
                        sumY += individual.getYDoubleRepresentation(fitnessFunction);
                    }
                    final double newX = sumX / amountOfIndividuals;
                    final double newY = sumY / amountOfIndividuals;
                    c.setChromosomeFromDoubleValues(newX, newY);
                }
            });

            // Calcula a distancia total entre os centroides novos e os antigos.
            double distance = 0;
            for (int i = 0; i < k; i++) {
                distance += lastCentroids.get(i).distanceTo(centroids.get(i), fitnessFunction);
            }

            // Se for zero, os centroides estao no lugar certo e final.
            if (distance == 0) {
                finish = true;
            }
        }

        // TODO verifica agora se a maioria da populacao esta num mesmo cluster. Se sim, retorna true

        return false;
    }

}
