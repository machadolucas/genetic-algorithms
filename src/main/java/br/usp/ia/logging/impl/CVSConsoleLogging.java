package br.usp.ia.logging.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import br.usp.ia.logging.Logging;
import br.usp.ia.logic.fitness.FitnessFunction;

@Component
@Slf4j(topic = "resultsConsole")
public class CVSConsoleLogging implements Logging {

    @Override
    public void fitnessProgress(final int generation, final int generationScreenSkip, final double populationTotal,
            final double populationAvg, final double max, final double min, FitnessFunction fitnessFunction) {
        String inverse = "";
        if (fitnessFunction.isMinimization()) {
            inverse = "-";
        }
        if (generation <= 25 || generation % generationScreenSkip == 0) {
            log.info("Geracao:{}\tTotal:{}\tAvg:{}\tMax:{}\tMin:{}", //
                    generation, //
                    String.format(inverse + "%." + fitnessFunction.getDecimalPrecision() + "f", populationTotal), //
                    String.format(inverse + "%." + fitnessFunction.getDecimalPrecision() + "f", populationAvg), //
                    String.format(inverse + "%." + fitnessFunction.getDecimalPrecision() + "f", max), //
                    String.format(inverse + "%." + fitnessFunction.getDecimalPrecision() + "f", min));
        }
    }

    @Override
    public void print(final String string) {
        log.info(string);
    }

}
