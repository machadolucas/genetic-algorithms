package br.usp.ia.logging.impl;

import br.usp.ia.logging.Logging;
import br.usp.ia.properties.ExecutionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "resultsConsole")
public class CVSConsoleLogging implements Logging {

    @Autowired
    ExecutionProperties executionProperties;

    private int counter;

    @Override
    public void fitnessProgress(final double populationTotal, final double populationAvg, final double max, final
    double min) {
        if (this.counter % this.executionProperties.getGenerationsToLogOnScreenInterval() == 0) {
            log.info("Geracao:{},Total:{},Avg:{},Max:{},Min:{}", //
                    this.counter, populationTotal, populationAvg, max, min);
        } else {
            this.counter++;
        }
    }

}
