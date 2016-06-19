package br.usp.ia.util;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import it.unimi.dsi.util.XorShift1024StarRandom;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public Individual nextRandomIndividual(final FitnessFunction fitnessFunction) {
        final int depotsAmount = fitnessFunction.getTestInstance().getDepotSections().size();
        final int nodesAmount = fitnessFunction.getTestInstance().getDimension() - depotsAmount;

        // Cria uma lista de 2 ate nodesAmount + depotsAmount, pulando o node deposito (1)
        final List<Integer> generated = new ArrayList<>();
        for (int amount = 2; amount <= nodesAmount + depotsAmount; amount++) {
            generated.add(amount);
        }

        // Embaralha aleatoriamente os nodes
        Collections.shuffle(generated, this.uniformGenerator);

        // Cria o cromossomo com os numeros gerados
        final Integer[] chromosome = generated.stream().toArray(Integer[]::new);

        return new Individual(chromosome);
    }

}
