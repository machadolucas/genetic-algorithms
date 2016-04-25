package br.usp.ia.logging.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import br.usp.ia.logging.Logging;
import br.usp.ia.logic.fitness.FitnessFunction;

@Component
@Slf4j(topic = "resultsFile")
public class CVSFileLogging implements Logging {

    @Override
    public void fitnessProgress(final int generation, final int generationScreenSkip, final double populationTotal,
            final double populationAvg, final double max, final double min, FitnessFunction fitnessFunction) {
        log.info("{},{},{},{},{}", generation, //
                String.format("%." + fitnessFunction.getDecimalPrecision() + "f", populationTotal), //
                String.format("%." + fitnessFunction.getDecimalPrecision() + "f", populationAvg), //
                String.format("%." + fitnessFunction.getDecimalPrecision() + "f", max), //
                String.format("%." + fitnessFunction.getDecimalPrecision() + "f", min));
    }

    @Override
    public void print(final String string) {
        log.info(string);
    }
}
