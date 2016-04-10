package br.usp.ia.logic;

import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GAAlgorithm {

    public final static int chromosomeLength = 128;

    @Autowired
    private Random random;
}
