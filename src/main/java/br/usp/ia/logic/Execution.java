package br.usp.ia.logic;

import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Execution {

    @Autowired
    private Random random;
}
