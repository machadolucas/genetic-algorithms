package br.usp.ia.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import lombok.Getter;

public class SolutionValidator {

    @Getter
    private List<Integer> missingList = new ArrayList<>();

    @Getter
    private List<Integer> errorList = new ArrayList<>();

    private static Integer depotsAmount;
    private static Integer nodesAmount;
    private static Integer trucksAmount;

    public SolutionValidator(final Individual individual, final FitnessFunction fitnessFunction) {

        if (depotsAmount == null) {
            depotsAmount = fitnessFunction.getTestInstance().getDepotSections().size();
        }
        if (nodesAmount == null) {
            nodesAmount = fitnessFunction.getTestInstance().getDimension() - depotsAmount;
        }
        if (trucksAmount == null) {
            trucksAmount = fitnessFunction.getTestInstance().getTrucks();
        }

        // Cria uma lista de 2 ate nodesAmount + depotsAmount, pulando o node deposito (1)
        final Set<Integer> allPossible = new TreeSet<>();
        for (int amount = 2; amount <= nodesAmount + depotsAmount; amount++) {
            allPossible.add(amount);
        }

        final Set<Integer> existent = new TreeSet<>();
        int trucksCounted = 0;
        for (int i = 0; i < individual.getChromosomeLength(); i++) {
            if (individual.getGene(i) == 0) {
                // Conta a quantidade de caminhoes do gene
                trucksCounted++;
            } else {
                if (!existent.contains(individual.getGene(i))) {
                    // Adiciona na lista de encontrados e remove dos possiveis
                    // No final o allPossible vai reter os que estao faltantes
                    existent.add(individual.getGene(i));
                    allPossible.remove(individual.getGene(i));
                } else {
                    this.errorList.add(individual.getGene(i));
                }
            }
        }
        // Adiciona mais um pois a ultima rota nao tem um zero no final
        trucksCounted++;

        int trucksCount = trucksCounted;

        // Se tiver menos caminhoes que o certo, adiciona caminhoes na lista de faltantes
        while (trucksCount < trucksAmount) {
            this.missingList.add(0);
            trucksCount++;
        }

        // Se tiver mais caminhoes que o certo, adiciona caminhoes na lista de erros
        while (trucksCount > trucksAmount) {
            this.errorList.add(0);
            trucksCount--;
        }

        // Adiciona a lista de faltantes
        this.missingList.addAll(allPossible);

    }

    public boolean isValid() {
        return this.missingList.size() == 0 && this.errorList.size() == 0;
    }


}
