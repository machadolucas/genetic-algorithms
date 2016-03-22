package br.usp.ia.util;

import lombok.Data;

import org.springframework.stereotype.Component;

import it.unimi.dsi.util.XorShift1024StarRandom;

/**
 * Created by lmachado on 3/22/16.
 */
@Data
@Component
public class Random {

    /**
     * Esse gerador eh bem mais rapido que o nativo do Java.
     * 
     * Veja mais em http://xorshift.di.unimi.it/
     */
    XorShift1024StarRandom uniformGenerator = new XorShift1024StarRandom();

}
