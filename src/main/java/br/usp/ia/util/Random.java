package br.usp.ia.util;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import it.unimi.dsi.util.XorShift1024StarRandom;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

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

        final LinkedHashSet<Integer> generated = new LinkedHashSet<>();
        // Como estamos adicionando num Set, ele vai gerar todos os numeros sem repeticao ate preencher tudo
        while (generated.size() < nodesAmount + trucksAmount - depotsAmount) {

            final Integer next = this.uniformGenerator.nextInt(nodesAmount + trucksAmount) + 1;

            //Se for zero ou um deposito, continua e gera outro
            if (next.equals(0) || fitnessFunction.getTestInstance().getDepotSections().contains(next)) {
                continue;
            }
            generated.add(next);
        }

        int i = 0;
        for (final Integer number : generated) {
            chromosome[i] = number;
            i++;
        }

        return new Individual(chromosome);
    }

}
