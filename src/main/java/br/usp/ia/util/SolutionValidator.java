package br.usp.ia.util;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import lombok.Getter;

import java.util.LinkedHashSet;

public class SolutionValidator {

    @Getter
    private LinkedHashSet<Integer> missingSet = new LinkedHashSet<>();

    @Getter
    private LinkedHashSet<Integer> errorSet = new LinkedHashSet<>();

    public SolutionValidator(final Individual individual, final FitnessFunction fitnessFunction) {

        //TODO validar se o cromossomo eh valido. Adicionar genes faltantes e errantes
    }

    public boolean isValid() {
        return this.missingSet.size() == 0 && this.errorSet.size() == 0;
    }


}
