package com.luisurdaneta.imgstoragebackend.graph;

import java.util.LinkedList;

public class Vertex {
    private LinkedList<Edge> edgeList;
    private String label;
    private int index;
    private String type;
    private float salary;

    public Vertex() {
    }

    public Vertex(String label, int index){
        this.label = label;
        this.index = index;
    }

    public void addEdge(Edge e){
        edgeList.addLast(e);
    }
    public void removeEdge(String targetLabel){
        edgeList.removeIf(e -> e.getTargetLabel().equals(label));
    }

    @Override
    public String toString(){
        String s = "Vertex: " + getLabel();
        if(edgeList.isEmpty()) return null;
        else return s;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LinkedList<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(LinkedList<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
