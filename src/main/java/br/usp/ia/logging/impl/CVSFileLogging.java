package br.usp.ia.logging.impl;

import br.usp.ia.logging.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "resultsFile")
public class CVSFileLogging implements Logging {

    private int counter = 0;

    @Override
    public void fitnessProgress(final int populationSize, final double populationAvg, final double max, final double min) {
        log.info("{}, {}, {}, {}, {}", this.counter, populationSize, populationAvg, max, min);
        this.counter++;
    }
}
