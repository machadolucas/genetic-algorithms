package br.usp.ia.create;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class TestsCreator {


    public void execute() {

        //Mapa de nomes de arquivos e seus conteudos
        final Map<String, String> testInstances = new HashMap<>();

        //-------------------------------------------------------------------

        //Selecoes a serem usadas
        final Template.SelectionStrategy[] selectionStrategies = {Template.SelectionStrategy.roulette};

        //Elitismos
        final boolean[] elitisms = {true, false};

        //Crossovers a serem usados
        final Template.CrossoverStrategy[] crossoverStrategies = {Template.CrossoverStrategy.one_point, Template
                .CrossoverStrategy.two_point, Template.CrossoverStrategy.uniform};
        //Probabilidades de crossover
        final String[] crossoverEnergies = {"0.8", "0.4"};

        //Funcoes a serem otimizadas
        final Template.FitnessStrategy[] fitnessStrategies = {Template.FitnessStrategy.bump, Template.FitnessStrategy
                .gold, Template.FitnessStrategy.rastrigin};

        //Mutacoes a serem usadas
        final Template.MutationStrategy[] mutationStrategies = {Template.MutationStrategy.simple, Template
                .MutationStrategy.uniform};
        //Probabilidades de mutacao
        final String[] mutationEnergies = {"0.01", "0.1"};

        //Tamanhos de populacao
        final int[] populationSizes = {40, 800};

        //Intervalo de log na tela (para execucao em batch quanto menos logar, melhor)
        final int generationsToLogOnScreenInterval = 9999999;

        //Numero limite de geracoes (criterio de parada)
        final int[] maxNumbersOfGenerations = {150, 1500};

        //Criterio de parada
        final Template.StopStrategy[] stopStrategies = {Template.StopStrategy.convergence, Template.StopStrategy
                .number_of_generations};

        //Criterio de troca de populacao
        final Template.PopulationChangeStrategy[] populationChangeStrategies = {Template.PopulationChangeStrategy
                .complete_replacement, Template.PopulationChangeStrategy.mu_plus_lambda};

        //-------------------------------------------------------------------

        //Para cada funcao a ser otimizada
        for (final Template.FitnessStrategy function : fitnessStrategies) {

            //Para cada tipo de selecao
            for (final Template.SelectionStrategy selection : selectionStrategies) {

                //Se tem ou nao elitismo
                for (final boolean elitism : elitisms) {

                    //Para cada tipo de crossover
                    for (final Template.CrossoverStrategy crossover : crossoverStrategies) {

                        //Para cada probabilidade de crossover
                        for (final String crossEnergy : crossoverEnergies) {

                            //Para cada tipo de mutacao
                            for (final Template.MutationStrategy mutation : mutationStrategies) {

                                //Para cada probabilidade de mutacao
                                for (final String mutEnergy : mutationEnergies) {

                                    //Para cada tamanho de populacao
                                    for (final int popSize : populationSizes) {

                                        //Para cada numero limite de geracoes
                                        for (final int maxGen : maxNumbersOfGenerations) {

                                            //Para cada tipo de criterio de parada
                                            for (final Template.StopStrategy stop : stopStrategies) {

                                                //Para cada tipo de criterio de troca de populacao
                                                for (final Template.PopulationChangeStrategy popChange :
                                                        populationChangeStrategies) {

                                                    //Cria nome do arquivo de teste
                                                    final StringBuilder fileName = new StringBuilder("T-");
                                                    fileName.append(function).append("-");
                                                    fileName.append(elitism).append("-");
                                                    fileName.append(crossover).append(crossEnergy).append("-");
                                                    fileName.append(mutation).append(mutEnergy).append("-");
                                                    fileName.append(popSize).append("-");
                                                    fileName.append(maxGen).append("-");
                                                    fileName.append(stop).append("-");
                                                    fileName.append(popChange);

                                                    //Obtem conteudo do arquivo de teste
                                                    final String fileContent = Template.getTestFileContent(selection,
                                                            elitism, crossover, crossEnergy, function, mutation,
                                                            mutEnergy, popSize, generationsToLogOnScreenInterval,
                                                            maxGen, stop, popChange);

                                                    testInstances.put(fileName.toString(), fileContent);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //-------------------------------------------------------------------

        //Grava em arquivos cada uma das instancias de teste
        try {
            Files.createDirectory(Paths.get("tests/"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        testInstances.forEach((fileName, content) -> {
            try {
                Files.write(Paths.get("tests/" + fileName + ".yml"), content.getBytes());
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });


    }
}
