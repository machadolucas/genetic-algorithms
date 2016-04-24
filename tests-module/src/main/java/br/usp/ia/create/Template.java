package br.usp.ia.create;

public class Template {

    private static final String SELECTION_STRATEGY = "<selectionStrategy>";
    private static final String ELITISM = "<elitism>";
    private static final String CROSSOVER_STRATEGY = "<crossoverStrategy>";
    private static final String CROSSOVER_ENERGY = "<crossoverEnergy>";
    private static final String FITNESS_STRATEGY = "<fitnessStrategy>";
    private static final String MUTATION_STRATEGY = "<mutationStrategy>";
    private static final String MUTATION_ENERGY = "<mutationEnergy>";
    private static final String POPULATION_SIZE = "<populationSize>";
    private static final String LOGGING_GENERATIONS_INTERVAL = "<loggingGenerationsInterval>";
    private static final String MAX_NUMBER_OF_GENERATIONS = "<maxNumberOfGenerations>";
    private static final String STOP_STRATEGY = "<stopStrategy>";
    private static final String POPULATION_CHANGE_STRATEGY = "<populationChangeStrategy>";

    private final static String template = "algorithm:\n" +
            "  params:\n" +
            "    selection:\n" +
            "      strategy: " + SELECTION_STRATEGY + "\n" +
            "      elitism: " + ELITISM + "\n" +
            "    crossover:\n" +
            "      strategy: " + CROSSOVER_STRATEGY + "\n" +
            "      energy: " + CROSSOVER_ENERGY + "\n" +
            "    fitness:\n" +
            "      strategy: " + FITNESS_STRATEGY + "\n" +
            "    mutation:\n" +
            "      strategy: " + MUTATION_STRATEGY + "\n" +
            "      energy: " + MUTATION_ENERGY + "\n" +
            "    execution:\n" +
            "      population-size: " + POPULATION_SIZE + "\n" +
            "      generations-to-log-on-screen-interval: " + LOGGING_GENERATIONS_INTERVAL + "\n" +
            "      max-number-of-generations: " + MAX_NUMBER_OF_GENERATIONS + "\n" +
            "      stop-strategy: " + STOP_STRATEGY + "\n" +
            "      population-change-strategy: " + POPULATION_CHANGE_STRATEGY + "\n";

    public static String getTestFileContent( //
                                             final SelectionStrategy selectionStrategy, final Boolean elitism, //
                                             final CrossoverStrategy crossoverStrategy, final String crossoverEnergy, //
                                             final FitnessStrategy fitnessStrategy, //
                                             final MutationStrategy mutationStrategy, final String mutationEnergy, //
                                             final int populationSize, final int generationsToLogOnScreenInterval, //
                                             final int maxNumberOfGenerations, //
                                             final StopStrategy stopStrategy, //
                                             final PopulationChangeStrategy populationChangeStrategy //
    ) {
        return template //
                .replaceFirst(SELECTION_STRATEGY, selectionStrategy.toString()) //
                .replaceFirst(ELITISM, elitism.toString()) //
                .replaceFirst(CROSSOVER_STRATEGY, crossoverStrategy.toString()) //
                .replaceFirst(CROSSOVER_ENERGY, crossoverEnergy) //
                .replaceFirst(FITNESS_STRATEGY, fitnessStrategy.toString()) //
                .replaceFirst(MUTATION_STRATEGY, mutationStrategy.toString()) //
                .replaceFirst(MUTATION_ENERGY, mutationEnergy) //
                .replaceFirst(POPULATION_SIZE, Integer.toString(populationSize)) //
                .replaceFirst(LOGGING_GENERATIONS_INTERVAL, Integer.toString(generationsToLogOnScreenInterval)) //
                .replaceFirst(MAX_NUMBER_OF_GENERATIONS, Integer.toString(maxNumberOfGenerations)) //
                .replaceFirst(STOP_STRATEGY, stopStrategy.toString()) //
                .replaceFirst(POPULATION_CHANGE_STRATEGY, populationChangeStrategy.toString()) //
                ;
    }


    enum SelectionStrategy {
        roulette
    }

    enum CrossoverStrategy {
        one_point, two_point, uniform
    }

    enum FitnessStrategy {
        bump, gold, rastrigin
    }

    enum MutationStrategy {
        simple, uniform
    }

    enum StopStrategy {
        number_of_generations, convergence
    }

    enum PopulationChangeStrategy {
        complete_replacement, mu_plus_lambda
    }
}
