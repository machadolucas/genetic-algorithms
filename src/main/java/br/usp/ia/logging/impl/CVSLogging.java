package br.usp.ia.logging.impl;

import br.usp.ia.entity.Population;
import br.usp.ia.logging.Logging;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CVSLogging {

    private final List<Logging> loggingList;

    @Autowired
    public CVSLogging(final List<Logging> loggingList) {
        this.loggingList = loggingList;
    }

    public void fitnessProgress(final int generation, final int generationScreenSkip, final Population population,
                                final FitnessFunction fitnessFunction) {

        this.loggingList.forEach(logging -> logging.fitnessProgress(//
                generation, //
                generationScreenSkip, //
                population.getTotalFitness(fitnessFunction), //
                population.getAverageFitness(fitnessFunction), //
                population.getMaxFitness(fitnessFunction), //
                population.getMinFitness(fitnessFunction) //
        ));
    }

    public void print(final String string) {
        this.loggingList.forEach(logging -> logging.print(string));
    }
}
