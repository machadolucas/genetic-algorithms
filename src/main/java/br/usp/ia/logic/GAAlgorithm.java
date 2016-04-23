package br.usp.ia.logic;

import br.usp.ia.entity.Individual;
import br.usp.ia.entity.Population;
import br.usp.ia.logging.impl.CVSLogging;
import br.usp.ia.logic.crossover.Crossover;
import br.usp.ia.logic.fitness.FitnessFunction;
import br.usp.ia.logic.mutation.Mutation;
import br.usp.ia.logic.selection.Selection;
import br.usp.ia.properties.*;
import br.usp.ia.util.Random;
import br.usp.ia.util.StrategySolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
public class GAAlgorithm {

    //====== Realiza logging da execucao
    @Autowired
    CVSLogging logging;

    //====== Criador de dados aleatorios
    @Autowired
    private Random random;

    //====== Propriedades da instancia de execucao
    @Autowired
    private ExecutionProperties executionProperties;
    @Autowired
    private CrossoverProperties crossoverProperties;
    @Autowired
    private MutationProperties mutationProperties;
    @Autowired
    private SelectionProperties selectionProperties;
    @Autowired
    private FitnessProperties fitnessProperties;

    //====== Resolvedor de algoritmos a partir das propriedades de execucao
    @Autowired
    private StrategySolver strategySolver;

    //====== Algoritmos a serem usados
    private FitnessFunction fitnessFunction;
    private Crossover crossover;
    private Mutation mutation;
    private Selection selection;

    //Inicializa quais algoritmos deve usar baseado nas propriedades
    @PostConstruct
    private void initializeAlgorithms() {
        this.fitnessFunction = this.strategySolver.getFitnessFunction(this.fitnessProperties);
        this.crossover = this.strategySolver.getCrossover(this.crossoverProperties);
        this.mutation = this.strategySolver.getMutation(this.mutationProperties);
        this.selection = this.strategySolver.getSelection(this.selectionProperties);
    }

    /**
     * Metodo que cria uma populacao inicial aleatoria, e gerencia a evolucao das geracoes, controlando o criterio de
     * parada.
     */
    public void progressAlgorithm() {

        Population population = initializeRandomPopulation(this.executionProperties.getPopulationSize(), this
                .fitnessFunction);

        int generationCount = 0;
        //TODO Controlar a parada do while pelo numero de geracoes ou por proximidade da resposta final
        while (population.getBest(this.fitnessFunction).getFitness(this.fitnessFunction) < 100 //
                && generationCount < 1000) {
            generationCount++;
            this.logging.fitnessProgress(population, this.fitnessFunction);
            population = evolveGeneration(population);
        }

    }

    /**
     * Evolui uma populacao para a proxima geracao
     *
     * @param currentPopulation a populacao da geracao atual que sera evoluida
     * @return a proxima geracao da populacao
     */
    private Population evolveGeneration(final Population currentPopulation) {

        final Population newGeneration = new Population(new LinkedList<>());

        //Se tiver elitismo, mantem o melhor individuo da geracao anterior na nova
        if (this.executionProperties.isElitism()) {
            newGeneration.getIndividuals().add(currentPopulation.getBest(this.fitnessFunction));
        }

        while (newGeneration.size() < currentPopulation.size()) {
            //Seleciona os pais para gerar novos cromossomos
            final List<Individual> selectedParents = //
                    this.selection.selectInPopulation(currentPopulation, 2, this.fitnessFunction);

            //Aplica operador de crossover
            final List<Individual> children = //
                    this.crossover.doCrossover(selectedParents.get(0), selectedParents.get(1));

            newGeneration.getIndividuals().add(children.get(0));
        }

        //Aplica operador de mutacao a toda a nova geracao
        newGeneration.getIndividuals().forEach( //
                individual -> this.mutation.mutate(individual, this.mutationProperties.getEnergy()));

        return newGeneration;
    }


    private Population initializeRandomPopulation(final int size, final FitnessFunction fitnessFunction) {
        final List<Individual> individuals = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            final Individual individual = this.random.nextBinaryIndividual(fitnessFunction);
            individuals.add(individual);
        }
        return new Population(individuals);
    }
}
