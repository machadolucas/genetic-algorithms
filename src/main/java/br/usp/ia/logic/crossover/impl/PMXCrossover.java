package br.usp.ia.logic.crossover.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.crossover.Crossover;
import br.usp.ia.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PMXCrossover implements Crossover {

    @Autowired
    private Random random;

    @Override
    public List<Individual> doCrossover(final Individual father, final Individual mother) {

        //Inicializa as criancas como listas de inteiros que sao mais faceis de manipular
        final List<Integer> offspring1 = new ArrayList<>(Arrays.asList(father.getChromosome()));
        final List<Integer> offspring2 = new ArrayList<>(Arrays.asList(mother.getChromosome()));

        //Calculas os dois pontos de corte do algortimo PMX
        final int point1 = this.random.getUniformGenerator().nextInt(father.getChromosomeLength());
        final int point2 = this.random.getUniformGenerator().nextInt(father.getChromosomeLength());

        //Calcula o comprimento da distancia entre os pontos de corte
        int length = point2 - point1;
        if (length < 0) {
            //Se for menor que zero, tamanho eh o cromossomo
            length += father.getChromosomeLength();
        }

        //Cria os mapas para mapear os genes entre os pais e escolher nos filhos
        final Map<Integer, Integer> mapping1 = new HashMap<>(length * 2);
        final Map<Integer, Integer> mapping2 = new HashMap<>(length * 2);
        //Completa os genes que forem possiveis
        for (int i = 0; i < length; i++) {
            final int index = (i + point1) % father.getChromosomeLength();
            final Integer item1 = offspring1.get(index);
            final Integer item2 = offspring2.get(index);
            offspring1.set(index, item2);
            offspring2.set(index, item1);
            mapping1.put(item1, item2);
            mapping2.put(item2, item1);
        }

        //Verifica para offspring1 elementos duplicados fora da secao mapeada, e completa gene a gene com o mapping2
        for (int i = 0; i < offspring1.size(); i++) {
            if (!isInsideMappedRegion(i, point1, point2)) {
                Integer mapped = offspring1.get(i);
                while (mapping2.containsKey(mapped)) {
                    mapped = mapping2.get(mapped);
                }
                offspring1.set(i, mapped);
            }
        }
        //Verifica para offspring2 elementos duplicados fora da secao mapeada, e completa gene a gene com o mapping1
        for (int i = 0; i < offspring2.size(); i++) {
            if (!isInsideMappedRegion(i, point1, point2)) {
                Integer mapped = offspring2.get(i);
                while (mapping1.containsKey(mapped)) {
                    mapped = mapping1.get(mapped);
                }
                offspring2.set(i, mapped);
            }
        }

        //Converte as listas de volta para cromossomos
        final Integer[] son1 = offspring1.stream().toArray(Integer[]::new);
        final Integer[] son2 = offspring2.stream().toArray(Integer[]::new);

        // Lista de criancas que serao retornadas
        final List<Individual> children = new LinkedList<>();
        children.add(new Individual(son1));
        children.add(new Individual(son2));

        return children;
    }

    /**
     * Verifica se o posicao esta dentro da regiao mapeada do crossover
     *
     * @param position   a posicao
     * @param startPoint primeiro ponto de corte
     * @param endPoint   segundo ponto de corte
     */
    private boolean isInsideMappedRegion(final int position, final int startPoint, final int endPoint) {
        final boolean enclosed = (position < endPoint && position >= startPoint);
        final boolean wrapAround = (startPoint > endPoint && (position >= startPoint || position < endPoint));
        return enclosed || wrapAround;
    }
}
