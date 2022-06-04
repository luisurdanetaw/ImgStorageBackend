package com.luisurdaneta.imgstoragebackend.profile;

import com.luisurdaneta.imgstoragebackend.graph.*;

import java.util.List;

public class WageDifferenceGraph {
    private Graph graph;
    public WageDifferenceGraph(){
        graph = new Graph();
    }

    public void addEmployee(Employee employee){
        graph.addVertex(employee.getUsername());

        for(Vertex vertex : graph.getAdjacencyList()){
            if(!vertex.getLabel().equals(employee.getUsername())){
                graph.addEdge(employee.getUsername(), vertex.getLabel(), employee.getSalary() - vertex.getSalary());
            }
        }
    }

}
