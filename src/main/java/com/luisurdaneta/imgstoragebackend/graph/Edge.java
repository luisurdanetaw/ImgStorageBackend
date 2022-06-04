package com.luisurdaneta.imgstoragebackend.graph;

public class Edge {
    private String targetLabel;
    private float weight;

    public Edge() {
    }

    public Edge(String targetLabel, long weight) {
        this.targetLabel = targetLabel;
        this.weight = weight;
    }

    public String getTargetLabel() {
        return targetLabel;
    }

    public void setTargetLabel(String targetLabel) {
        this.targetLabel = targetLabel;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
