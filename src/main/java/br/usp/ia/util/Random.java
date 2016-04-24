package br.usp.ia.util;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import it.unimi.dsi.util.XorShift1024StarRandom;
import lombok.Data;
import org.springframework.stereotype.Component;

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
     * @return um novo individuo com um cromossomo binario aleatorio
     */
    public Individual nextBinaryIndividual(final FitnessFunction fitnessFunction) {
        final byte[] chromosome = new byte[fitnessFunction.getChromosomeLength()];
        for (int i = 0; i < chromosome.length; i++) {
            chromosome[i] = (byte) this.uniformGenerator.nextInt(2);
        }
        return new Individual(chromosome);
    }

}
