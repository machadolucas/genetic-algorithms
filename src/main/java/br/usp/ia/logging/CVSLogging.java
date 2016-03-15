package br.usp.ia.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by richardcsantana on 15/03/16.
 */
@Component
@Slf4j(topic = "results")
public class CVSLogging {

    public void fitnessProgress(int populationSize, double populationAvg, double max,double min){
        log.info("{}, {}, {}, {}",populationSize,populationAvg,max,min);
    }
}
