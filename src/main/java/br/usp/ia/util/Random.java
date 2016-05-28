package br.usp.ia.util;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import it.unimi.dsi.util.XorShift1024StarRandom;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class Random {

    /**
     * Esse gerador eh bem mais rapido que o nativo do Java.
     * <p>
     * Veja mais em http://xorshift.di.unimi.it/
     */
    XorShift1024StarRandom uniformGenerator = new XorShift1024StarRandom();

    /**
     * @return um novo individuo com um cromossomo aleatorio
     */
    public Individual nextBinaryIndividual(final FitnessFunction fitnessFunction) {
        final int depotsAmount = fitnessFunction.getTestInstance().getDepotSections().size();
        final int nodesAmount = fitnessFunction.getTestInstance().getDimension() - depotsAmount;
        final int trucksAmount = fitnessFunction.getTestInstance().getTrucks();

        final Integer[] chromosome = new Integer[nodesAmount + trucksAmount - depotsAmount];

        // Cria uma lista de 2 ate nodesAmount + depotsAmount, pulando o node deposito (1)
        final List<Integer> generated = new ArrayList<>();
        for (int amount = 2; amount <= nodesAmount + depotsAmount; amount++) {
            generated.add(amount);
        }

        // Embaralha aleatoriamente os nodes
        Collections.shuffle(generated, this.uniformGenerator);

        // Aleatoriamente gera pontos de corte para inserir os marcadores de trucks
        final Set<Integer> cutPoints = new HashSet<>();
        while (cutPoints.size() < trucksAmount - 1) {
            final Integer nextCut = this.uniformGenerator.nextInt(chromosome.length);
            cutPoints.add(nextCut);
        }

        // Cria o cromossomo com os numeros gerados, intercalando com os cortes para marcadores de trucks
        int i = 0;
        for (int chromosomeIndex = 0; chromosomeIndex < chromosome.length; chromosomeIndex++) {
            if (cutPoints.contains(chromosomeIndex)) {
                chromosome[chromosomeIndex] = 0;
            } else {
                chromosome[chromosomeIndex] = generated.get(i);
                i++;
            }
        }

        return new Individual(chromosome);
    }

}
