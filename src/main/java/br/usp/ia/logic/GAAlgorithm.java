package br.usp.ia.logic;

import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GAAlgorithm {

    @Autowired
    private Random random;

    //TODO juntar aqui a logica toda de chamar operadores de crossover, selecao, mutacao, etc
}
