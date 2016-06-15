package br.usp.ia.logic.correction.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.correction.Correction;
import br.usp.ia.logic.fitness.FitnessFunction;
import br.usp.ia.util.Random;

@Component
public class GeneRepair implements Correction {

    @Autowired
    private Random random;

    @Override
    public Individual correct(final Individual individual, final FitnessFunction fitnessFunction,
            final List<Integer> missingList, final List<Integer> errorList) {

        //Gera um template aleatoria para ser usado na correcao
        final Individual template = this.random.nextRandomIndividual(fitnessFunction);

        //Cria e popula uma lista de genes a usados no reparo
        final List<Integer> repairList = new LinkedList<>();
        for (final Integer i : template.getChromosome()) {
            if (missingList.contains(i)) {
                missingList.remove(i);
                repairList.add(i);
            }
        }

        //Faz o reparo no cromossomo
        int repairCount = 0;
        for (int i = 0; i < individual.getChromosomeLength(); i++) {
            final Integer value = individual.getGene(i);
            if (errorList.contains(value)) {
                errorList.remove(value);
                individual.setGene(i, repairList.get(repairCount));
                repairCount++;
            }
            if (repairCount == repairList.size()) {
                break;
            }
        }

        return individual;
    }
}
