package br.usp.ia.logic.fitness.impl;

import br.usp.ia.entity.Individual;
import br.usp.ia.logic.fitness.FitnessFunction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RouteFunction extends FitnessFunction {

    @Override
    public int calculate(final Individual individual) {

        final Map<Integer, List<Integer>> objectRepresentation = individual.getObjectRepresentation(this);
        final List<List<Double>> coords = getTestInstance().getCoords();
        final List<Double> depotCoords = coords.get(0);
        final List<Integer> demands = getTestInstance().getDemands();

        int totalDistances = 0;
        int overCapacities = 0;

        // Para cada rota de caminhoes (Lista de nodes para o id do caminhao)
        for (final Map.Entry<Integer, List<Integer>> entry : objectRepresentation.entrySet()) {
            final List<Integer> path = entry.getValue();
            int pathDistance = 0;
            int pathDemand = 0;

            // Se o caminho tiver nodes
            if (path.size() > 0) {

                // Calcula e soma distancia entre o depot e o primeiro node
                final int outOfDepotDistance = distanceBetween(depotCoords, coords.get(path.get(0) - 1));
                pathDistance += outOfDepotDistance;

                // Calcula e soma distancias entre os nodes
                for (int i = 0; i < path.size() - 1; i++) {
                    final Integer currentNode = path.get(i);
                    final Integer nextNode = path.get(i + 1);
                    pathDistance += distanceBetween(coords.get(currentNode - 1), coords.get(nextNode - 1));
                    pathDemand += demands.get(currentNode - 1);
                }

                // Calcula e soma distancia entre o ultimo node e o depot de volta
                final int backToDepotDistance = distanceBetween(coords.get(path.get(path.size() - 1) - 1), depotCoords);
                pathDistance += backToDepotDistance;
                pathDemand += demands.get(path.get(path.size() - 1) - 1);

                //Contador de overcapacity
                if (pathDemand > getTestInstance().getCapacity()) {
                    overCapacities++;
                }
            }

            totalDistances += pathDistance;
        }

        // Peso para cada overcapacity
        final int overCapacityWeight = 10000000;

        // Fitness eh a soma das distancias, mais o peso de overcapacity
        return totalDistances + (overCapacityWeight * overCapacities);
    }

    /**
     * Calcula a distancia euclidiana entre dois nodes
     *
     * @param oneNode
     * @param anotherNode
     * @return
     */
    Integer distanceBetween(final List<Double> oneNode, final List<Double> anotherNode) {
        Integer distance = null;

        switch (getTestInstance().getEdgeWeightType()) {
            case "EUC_2D": {
                final double xD = oneNode.get(0) - anotherNode.get(0);
                final double yD = oneNode.get(1) - anotherNode.get(1);
                distance = (int) Math.sqrt(xD * xD + yD * yD);
                break;
            }
            case "EUC_3D": {
                final double xD = oneNode.get(0) - anotherNode.get(0);
                final double yD = oneNode.get(1) - anotherNode.get(1);
                final double zD = oneNode.get(2) - anotherNode.get(2);
                distance = (int) Math.sqrt(xD * xD + yD * yD + zD * zD);
                break;
            }
            default: {
                System.out.println("Not supported edge weight type: " + getTestInstance().getEdgeWeightType());
            }
        }

        return distance;
    }
}
