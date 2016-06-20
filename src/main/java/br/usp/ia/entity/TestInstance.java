package br.usp.ia.entity;

import lombok.Data;

import java.util.List;

/**
 * Representacao de um arquivo de instancia de teste .vrp como objeto
 */
@Data
public class TestInstance {

    private String name;

    private String comment;

    private String type;

    private Integer dimension;

    private String edgeWeightType;

    private Integer trucks;

    private Integer capacity;

    private List<List<Double>> coords;

    private List<Integer> demands;

    private List<Integer> depotSections;

    @Override
    public String toString() {
        return "{\"TestInstance\":{" +
                "\"name\":\"" + this.name +
                "\",\"comment\":\"" + this.comment +
                "\",\"type\":\"" + this.type +
                "\",\"dimension\":\"" + this.dimension +
                "\",\"edgeWeightType\":\"" + this.edgeWeightType +
                "\",\"capacity\":\"" + this.capacity +
                "\",\"coords\":\"" + this.coords +
                "\",\"demands\":\"" + this.demands +
                "\",\"depotSections\":\"" + this.depotSections +
                "\"}}";
    }
}
