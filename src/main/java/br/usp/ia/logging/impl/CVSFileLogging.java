package br.usp.ia.logging.impl;

import br.usp.ia.logging.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "resultsFile")
public class CVSFileLogging implements Logging {

    @Override
    public void fitnessProgress(final int generation, final int generationScreenSkip, final double populationTotal,
                                final double populationAvg, final double max, final double min) {
        log.info("{},{},{},{},{}", generation, populationTotal, populationAvg, max, min);
    }

    @Override
    public void print(final String string) {
        log.info(string);
    }
}
