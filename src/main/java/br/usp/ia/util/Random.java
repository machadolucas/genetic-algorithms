package br.usp.ia.util;

import it.unimi.dsi.util.XorShift1024StarRandom;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Random {

    /**
     * Esse gerador eh bem mais rapido que o nativo do Java.
     * <p>
     * Veja mais em http://xorshift.di.unimi.it/
     */
    XorShift1024StarRandom uniformGenerator = new XorShift1024StarRandom();

}
