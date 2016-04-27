package br.usp.ia.util;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.Population;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class KMeansConvergence {

    @Autowired
    Random random;

    /**
     * Calcula se ha convergencia na populacao por k-means
     *
     * @param fitnessFunction
     * @return true se houver convergencia
     */
    public boolean hasConvergence(final Population population, final FitnessFunction fitnessFunction) {

        // Tenta definir qua a quantidade de clusters deve ser 10% do tamanho da populacao
        int k = Math.toIntExact(Math.round(population.size() * 0.1));
        if (k < 5) { // para garantir que haja no minimo 5 grupos
            k = 5;
        } else if (k > 15) { // ou que haja no maximo 15 grupos
            k = 15;
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
            clusters.forEach((cent, individuals) -> {
                individuals.clear();
            });

            // Guarda os ultimos centroides antes de serem alterados, para calcular se houve dieferenca depois
            final List<Individual> lastCentroids = centroids.stream().map(individual -> new Individual(individual
                    .getChromosome())).collect(Collectors.toList());

            // Coloca os individuos no cluster mais proximo
            for (final Individual individual : population.getIndividuals()) {

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
                    c.setChromosomeFromDoubleValues(newX, newY, fitnessFunction);
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

        // Define a distancia maxima entre os centroides para considerar convergencia como 10% do espaco de busca em
        // uma dimensao. Com D dimensoes, isso equivale a 0.1^D da area total de busca (1). No caso, 1% da area.
        final double maxConvergenceDistance = fitnessFunction.getUpperLimit() - fitnessFunction.getLowerLimit() * 0.1;

        // Calcula as distancias entre todos os centroides. Se uma delas for maior que maxConvergenceDistance, nao ha
        // convergencia da populacao
        boolean isAllTogether = true;
        for (final Individual centroid : centroids) {
            for (final Individual other : centroids) {
                if (!centroid.equals(other) && //
                        centroid.distanceTo(other, fitnessFunction) > maxConvergenceDistance) {
                    isAllTogether = false;
                }
            }
        }

        return isAllTogether;
    }
}
