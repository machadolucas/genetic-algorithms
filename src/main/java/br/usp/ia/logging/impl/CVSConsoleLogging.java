package br.usp.ia.logging.impl;

import br.usp.ia.logging.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by richardcsantana on 15/03/16.
 */
@Component
@Slf4j(topic = "resultsConsole")
public class CVSConsoleLogging implements Logging {

    @Value("${results.generationsInterval:100}")
    private int generations;

    private int counter;
    private int totalCounter;

    @Override
    public void fitnessProgress(final int populationSize, final double populationAvg, final double max, final double min) {
        if (this.counter == this.generations) {
            log.info("{}, {}, {}, {}, {}", this.totalCounter, populationSize, populationAvg, max, min);
            this.counter = 0;
        } else {
            this.counter++;
            this.totalCounter++;
        }
    }

}
