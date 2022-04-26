package com.luisurdaneta.imgstoragebackend.graph;

public interface GraphADT {
        public void addVertex(String label);
        public void removeVertex(String label);
        public void addEdge(String label1, String label2, long weight);
        public void removeEdge(String label1, String label2);
        public long shortestDistanceToVertex(String startLabel, String endLabel);
}
