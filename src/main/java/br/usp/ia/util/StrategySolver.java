package br.usp.ia.util;

import br.usp.ia.logic.crossover.Crossover;
import br.usp.ia.logic.mutation.Mutation;
import br.usp.ia.logic.selection.Selection;
import br.usp.ia.properties.CrossoverProperties;
import br.usp.ia.properties.MutationProperties;
import br.usp.ia.properties.SelectionProperties;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategySolver {

    @Autowired
    BeanFactory bf;

    public Crossover getCrossover(final CrossoverProperties crossoverProperties) {
        final CrossoverProperties.CrossoverStrategy strategy = crossoverProperties.getStrategy();
        return this.bf.getBean(strategy.getName(), Crossover.class);
    }

    public Mutation getMutation(final MutationProperties mutationProperties) {
        final MutationProperties.MutationStrategy strategy = mutationProperties.getStrategy();
        return this.bf.getBean(strategy.getName(), Mutation.class);
    }

    public Selection getSelection(final SelectionProperties selectionProperties) {
        final SelectionProperties.SelectionStrategy strategy = selectionProperties.getStrategy();
        return this.bf.getBean(strategy.getName(), Selection.class);
    }


}
