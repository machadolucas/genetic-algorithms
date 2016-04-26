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

import static br.usp.ia.properties.ExecutionProperties.StopStrategy.CONVERGENCE;

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

        //Imprime no arquivo e na tela os parametros de inicializacao do algoritmo
        this.logging.print(this.fitnessProperties.toString());
        this.logging.print(this.crossoverProperties.toString());
        this.logging.print(this.mutationProperties.toString());
        this.logging.print(this.selectionProperties.toString());
        this.logging.print(this.executionProperties.toString());

        final long startTime = System.currentTimeMillis();
        Population population = initializeRandomPopulation(this.executionProperties.getPopulationSize(), this
                .fitnessFunction);

        int generationCount = 0;

        //variavel pra guardar o ultimo "melhor individuo"
        Individual lastBest = population.getBest(this.fitnessFunction);
        //variavel para contar o numero de geracoes de individuos "repetidos"
        int convergenceCount = 0;
        //variavel para parar o algoritmo em caso de convergencia
        int convergenceNumber = 20;

        boolean keepGoing = true;
        while (keepGoing) {
            generationCount++;
            this.logging.fitnessProgress( //
                    generationCount, this.executionProperties.getGenerationsToLogOnScreenInterval(), //
                    population, this.fitnessFunction);

            population = evolveGeneration(population);

            //Criterio de parada do algoritmo:
            switch (this.executionProperties.getStopStrategy()) {
                case NUMBER_OF_GENERATIONS:
                    //Se for pelo numero de geracoes
                    if (generationCount >= this.executionProperties.getMaxNumberOfGenerations()) {
                        keepGoing = false;
                    }
                    break;
                case CONVERGENCE:
                default:
                    //Se for por convergencia da populacao
                    //TODO Controlar a parada do while quando nao ocorre melhora significativa na solucao durante um dado nimero de geracoes

                    if (population.getBest(this.fitnessFunction) == lastBest) {
                        convergenceCount++;
                    }
                    if (convergenceCount == convergenceNumber) {
                        keepGoing = false;
                    }
                    break;

            }
        }

        final long endTime = System.currentTimeMillis();

        this.logging.print(""); // Imprime o melhor individuo encontrado
        this.logging.print("Melhor individuo da ultima geracao:");
        final Individual best = population.getBest(this.fitnessFunction);
        this.logging.print(best.toString(this.fitnessFunction));
        this.logging.print("X:" + best.getXDoubleRepresentation(this.fitnessFunction) + ",Y:" + best
                .getYDoubleRepresentation(this.fitnessFunction));

        this.logging.print(""); // Imprime o tempo total de execucao
        this.logging.print("Tempo total de execucao (ms):");
        this.logging.print(String.valueOf(endTime - startTime));

    }

    /**
     * Evolui uma populacao para a proxima geracao
     *
     * @param currentPopulation a populacao da geracao atual que sera evoluida
     * @return a proxima geracao da populacao
     */
    private Population evolveGeneration(final Population currentPopulation) {

        final Population newGeneration = new Population(new LinkedList<>());

        int individualsToSelect = currentPopulation.size();

        //Se tiver elitismo, mantem o melhor individuo da geracao anterior na nova
        if (this.selectionProperties.isElitism()) {
            newGeneration.getIndividuals().add(currentPopulation.getBest(this.fitnessFunction));
            individualsToSelect--;
        }

        //Vai gerar novos individuos ate encher a populacao da nova geracao
        while (individualsToSelect > 0) {

            //Seleciona os individuos que gerarao a proxima geracao
            final List<Individual> selectedParents = //
                    this.selection.selectInPopulation(currentPopulation, 2, this.fitnessFunction);

            //Calcula probabilidade de crossover
            if (this.random.getUniformGenerator().nextDouble() <= this.crossoverProperties.getEnergy()) {

                //Aplica operador de crossover entre pares de selectedParents, criando children
                final List<Individual> children = this.crossover.doCrossover( //
                        selectedParents.get(0), selectedParents.get(1));

                //Escolhe aleatoriamente um dos filhos para adicionar na nova geracao
                final int choosenChildren = this.random.getUniformGenerator().nextInt(2);
                newGeneration.getIndividuals().add(children.get(choosenChildren));

            } else {

                //Se nao atingiu a probabilidade de crossover, faz reproducao (clone)
                //Escolhe aleatoriamente um dos filhos para adicionar na nova geracao
                final int choosenChildren = this.random.getUniformGenerator().nextInt(2);
                newGeneration.getIndividuals().add(selectedParents.get(choosenChildren));
            }

            individualsToSelect--;
        }

        //Aplica operador de mutacao em toda a nova geracao
        newGeneration.getIndividuals().forEach( //
                individual -> this.mutation.mutate(individual, this.mutationProperties.getEnergy()));

        //Faz a troca da populacao de acordo com o criterio
        switch (this.executionProperties.getPopulationChangeStrategy()) {
            case MU_PLUS_LAMBDA:
                //Faz a troca pela estrategia Mu+Lambda, selecionando os melhores individuos entre todos da
                // populacao atual e da nova
                final Population parentsAndChildren = new Population(new LinkedList<>());
                parentsAndChildren.getIndividuals().addAll(currentPopulation.getIndividuals());
                parentsAndChildren.getIndividuals().addAll(newGeneration.getIndividuals());

                final List<Individual> selected = this.selection.selectInPopulation(parentsAndChildren,
                        currentPopulation.size(), this.fitnessFunction);

                return new Population(selected);
            case COMPLETE_REPLACEMENT:
            default:
                //Faz a troca por substituicao completa da geracao atual pela nova
                return newGeneration;
        }

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
