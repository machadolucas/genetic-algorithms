package br.usp.ia.logic.fitness;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.TestInstance;
import lombok.Getter;
import lombok.Setter;

public abstract class FitnessFunction {

    @Getter
    @Setter
    private TestInstance testInstance;

    public abstract int calculate(Individual individual);

}
