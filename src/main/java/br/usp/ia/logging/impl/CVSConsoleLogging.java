package br.usp.ia.logging.impl;

import br.usp.ia.logging.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "resultsConsole")
public class CVSConsoleLogging implements Logging {

    @Override
    public void fitnessProgress(final int generation, final int generationScreenSkip, final double populationTotal,
                                final double populationAvg, final double max, final double min) {
        if (generation <= 25 || generation % generationScreenSkip == 0) {
            log.info("Geracao:{},\tTotal:{},\tAvg:{},\tMax:{},\tMin:{}", //
                    generation, populationTotal, populationAvg, max, min);
        }
    }

    @Override
    public void print(final String string) {
        log.info(string);
    }

}
