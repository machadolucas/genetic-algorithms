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

        final int nodesAmount = getTestInstance().getDimension();
        final int trucksAmount = getTestInstance().getTrucks();

        final Map<Integer, List<Integer>> objectRepresentation = individual.getObjectRepresentation(this);
        final List<List<Double>> coords = getTestInstance().getCoords();
        final List<Double> depotCoords = coords.get(0);

        int totalDistances = 0;
        for (final Map.Entry<Integer, List<Integer>> entry : objectRepresentation.entrySet()) {
            final List<Integer> path = entry.getValue();
            int pathDistance = 0;
            if (path.size() > 0) {
                final int outOfDepotDistance = distanceBetween(depotCoords, coords.get(path.get(0)));
                pathDistance += outOfDepotDistance;
                for (int i = 0; i < path.size() - 1; i++) {
                    pathDistance += distanceBetween(coords.get(path.get(i)), coords.get(path.get(i + 1)));
                }
                final int backToDepotDistance = distanceBetween(coords.get(path.get(path.size() - 1)), depotCoords);
                pathDistance += backToDepotDistance;
            }

            totalDistances += pathDistance;
        }

        return totalDistances;
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
