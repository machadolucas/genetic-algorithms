package br.usp.ia.logging.impl;

import br.usp.ia.logging.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by richardcsantana on 15/03/16.
 */
@Component
public class CVSLogging {

    private final List<Logging> loggingList;

    @Autowired
    public CVSLogging(final List<Logging> loggingList) {
        this.loggingList = loggingList;
    }

    public void fitnessProgress(final int populationSize, final double populationAvg, final double max, final double min) {
        for (final Logging logging : this.loggingList) {
            logging.fitnessProgress(populationSize, populationAvg, max, min);
        }
    }
}
