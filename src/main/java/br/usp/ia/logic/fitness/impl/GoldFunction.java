package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.Population;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * String para gerar o grafico no wolfram alpha:
 * plot (1+(x+y+1)^2*(19-14*x+3*x^2-14*y+6*x*y+3*y^2))*(30+(2*x-3*y)^2*(18-32*x+12*x+12*x^2+48*y-36*x*y+27*y^2)),
 * x=-2..2, y=-2..2
 */
@Component
public class GoldFunction implements FitnessFunction {

    @Override
    public double calculate(final Individual individual) {
        //Obtem os valores decimais a partir da representacao binaria
        final double x = individual.getXDoubleRepresentation(this);
        final double y = individual.getYDoubleRepresentation(this);

        //Calcula z na funcao Gold a partir de x e y
        final double a = 1 + Math.pow((x + y + 1), 2) * //
                (19 - 14 * x + 3 * Math.pow(x, 2) - 14 * y + 6 * x * y + 3 * Math.pow(y, 2));
        final double b = 30 + Math.pow((2 * x - 3 * y), 2) * //
                (18 - 32 * x + 12 * Math.pow(x, 2) + 48 * y - 36 * x * y + 27 * Math.pow(y, 2));
        final double z = a * b;

        return z;
    }

    @Override
    //Limite inferior do intervalo para minimizacao
    public double getLowerLimit() {
        return -2;
    }

    @Override
    //Limite superior do intervalo para minimizacao
    public double getUpperLimit() {
        return 2;
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
                if (plainDouble.charAt(i) == '0'){
                    precision++;
                } else {
                    break;
                }
            }
            decimalPrecision = precision;

        }
        return decimalPrecision;
    }

}
