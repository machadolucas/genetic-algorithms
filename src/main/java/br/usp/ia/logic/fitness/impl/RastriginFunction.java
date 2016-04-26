package br.usp.ia.logic.fitness.impl;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;

/**
 * String para gerar o grafico no wolfram alpha:
 * plot -((x^2-10*cos(2*pi*x)+10)+(y^2-10*cos(2*pi*y)+10)), x=-5..5, y=-5..5
 */
@Component
public class RastriginFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        // Obtem os valores decimais a partir da representacao binaria
        final double x = individual.getXDoubleRepresentation(this);
        final double y = individual.getYDoubleRepresentation(this);

        // Calcula z na funcao Rastrigin a partir de x e y
        final double zx = Math.pow(x, 2) - 10 * Math.cos(2 * Math.PI * x) + 10;
        final double zy = Math.pow(y, 2) - 10 * Math.cos(2 * Math.PI * y) + 10;

        final double z = zx + zy;

        return -z;
    }

    @Override
    // Limite inferior do intervalo para maximizacao
    public double getLowerLimit() {
        return -5;
    }

    @Override
    // Limite superior do intervalo para maximizacao
    public double getUpperLimit() {
        return 5;
    }

    private static int decimalPrecision = 0;

    @Override
    // Casa decimal de maxima precisao, de acordo com a representacao
    // Calculado segundo Linden (2012)
    public int getDecimalPrecision() {
        if (decimalPrecision == 0) { // So calcula se nao foi calculado antes (cache)
            final double precisionValue = (getUpperLimit() - getLowerLimit()) / (Math.pow(2, getXLength()) - 1);
            DecimalFormat df = new DecimalFormat("0");
            df.setMaximumFractionDigits(340);
            String plainDouble = df.format(precisionValue);
            int precision = 1;
            for (int i = 2; i < plainDouble.length(); i++) {
                if (plainDouble.charAt(i) == '0') {
                    precision++;
                } else {
                    break;
                }
            }
            decimalPrecision = precision;

        }
        return decimalPrecision;
    }

    @Override
    public boolean isMinimization() {
        return false;
    }

}
