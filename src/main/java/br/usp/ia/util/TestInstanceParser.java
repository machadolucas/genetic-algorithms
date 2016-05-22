package br.usp.ia.util;

import br.usp.ia.entity.TestInstance;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TestInstanceParser {

    /**
     * Faz a interpretacao de um arquivo de instancia de teste .vrp especificado e retorna um objeto TestInstance
     * preenchido
     *
     * @param fileName nome do arquivo de teste, por exemplo "tests/A-n33-k5.vrp"
     * @return objeto {@link TestInstance} preenchido
     */
    public TestInstance parse(final String fileName) {
        final TestInstance testInstance = new TestInstance();

        //Carrega as linhas do arquivo .vrp
        List<String> lines = new LinkedList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            lines = stream.collect(Collectors.toList());
        } catch (final IOException e) {
            e.printStackTrace();
        }

        //Faz a leitura do arquivo .vrp linha a linha e transforma num objeto TestInstance
        final String name = lines.get(0).trim().substring(7);
        testInstance.setName(name);

        final String comment = lines.get(1).trim().substring(10);
        testInstance.setComment(comment);

        final String type = lines.get(2).trim().substring(7);
        testInstance.setType(type);

        final Integer dimension = Integer.valueOf(lines.get(3).trim().substring(12));
        testInstance.setDimension(dimension);

        final String edgeWeightType = lines.get(4).trim().substring(19);
        testInstance.setEdgeWeightType(edgeWeightType);

        final Integer trucks = Integer.valueOf(lines.get(0).trim().substring(lines.get(0).indexOf("k") + 1));
        testInstance.setTrucks(trucks);

        final Integer capacity = Integer.valueOf(lines.get(5).trim().substring(11));
        testInstance.setCapacity(capacity);

        int lineIndex = 7;
        //Faz leitura das coordenadas
        final List<List<Double>> coords = new ArrayList<>();
        while (!lines.get(lineIndex).contains("DEMAND_SECTION")) {
            final List<Double> nodeCoords = new LinkedList<>();
            final String[] lineStringCoords = lines.get(lineIndex).trim().split(" ");
            for (int coord = 1; coord < lineStringCoords.length; coord++) {
                nodeCoords.add(Double.valueOf(lineStringCoords[coord]));
            }
            coords.add(nodeCoords);
            lineIndex++;
        }
        testInstance.setCoords(coords);
        lineIndex++;

        //Faz leitura das demandas
        final List<Integer> demands = new ArrayList<>();
        while (!lines.get(lineIndex).contains("DEPOT_SECTION")) {
            final String[] lineStringDemand = lines.get(lineIndex).trim().split(" ");
            demands.add(Integer.valueOf(lineStringDemand[1]));
            lineIndex++;
        }
        testInstance.setDemands(demands);
        lineIndex++;

        //Faz leitura dos DEPOT_SECTIONs
        final List<Integer> depotSections = new ArrayList<>();
        while (!lines.get(lineIndex).contains("-1")) {
            depotSections.add(Integer.valueOf(lines.get(lineIndex).trim()));
            lineIndex++;
        }
        testInstance.setDepotSections(depotSections);

        return testInstance;
    }
}
