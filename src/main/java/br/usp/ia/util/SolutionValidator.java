package br.usp.ia.util;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SolutionValidator {

    private static Integer depotsAmount;
    private static Integer nodesAmount;
    @Getter
    private List<Integer> missingList = new ArrayList<>();
    @Getter
    private List<Integer> errorList = new ArrayList<>();

    public SolutionValidator(final Individual individual, final FitnessFunction fitnessFunction) {

        if (depotsAmount == null) {
            depotsAmount = fitnessFunction.getTestInstance().getDepotSections().size();
        }
        if (nodesAmount == null) {
            nodesAmount = fitnessFunction.getTestInstance().getDimension() - depotsAmount;
        }

        // Cria uma lista de 2 ate nodesAmount + depotsAmount, pulando o node deposito (1)
        final Set<Integer> allPossible = new TreeSet<>();
        for (int amount = 2; amount <= nodesAmount + depotsAmount; amount++) {
            allPossible.add(amount);
        }

        final Set<Integer> existent = new TreeSet<>();
        for (int i = 0; i < individual.getChromosomeLength(); i++) {
            if (!existent.contains(individual.getGene(i))) {
                // Adiciona na lista de encontrados e remove dos possiveis
                // No final o allPossible vai reter os que estao faltantes
                existent.add(individual.getGene(i));
                allPossible.remove(individual.getGene(i));
            } else {
                //Se ja existe na lista existent, eh duplicado e vai pra errorList
                this.errorList.add(individual.getGene(i));
            }
        }


        // Adiciona a lista de faltantes
        this.missingList.addAll(allPossible);

    }

    public boolean isValid() {
        return this.missingList.size() == 0 && this.errorList.size() == 0;
    }


}
