package br.usp.ia.logging.impl;

import br.usp.ia.logging.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "resultsFile")
public class CVSFileLogging implements Logging {

    @Override
    public void fitnessProgress(final int generation, final int generationScreenSkip, final Integer populationTotal,
                                final double populationAvg, final Integer max, final Integer min) {
        log.info("{}\t{}\t{}\t{}\t{}", generation, //
                String.format("%d", populationTotal), //
                String.format("%.3f", populationAvg), //
                String.format("%d", max), //
                String.format("%d", min));
    }

    @Override
    public void print(final String string) {
        log.info(string);
    }
}
