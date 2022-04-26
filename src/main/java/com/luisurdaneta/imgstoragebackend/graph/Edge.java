package com.luisurdaneta.imgstoragebackend.graph;

public class Edge {
    private String targetLabel;
    private long weight;

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

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}
