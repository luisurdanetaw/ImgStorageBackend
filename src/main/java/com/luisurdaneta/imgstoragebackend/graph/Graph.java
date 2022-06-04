package com.luisurdaneta.imgstoragebackend.graph;




import org.springframework.data.util.Pair;

import java.util.*;

public class Graph implements GraphADT {

    private ArrayList<Vertex> adjacencyList;
    private HashMap<String, Integer> labelIndexes;
    private int size;

    public Graph() {
    }

    public int getSize() {
        return size;
    }

    public int getLabelIndex(String label){
        if(labelIndexes.get(label) != null)
            return labelIndexes.get(label);
        else return -1;
    }

    //Check
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Vertex v : adjacencyList)
            s.append(v.toString());

        return s.toString();
    }


    /*****************************************************888
     * @param label
     * //Check if vertex exists in adjacency list
     * //Create vertex, store <Label,index> and increment graph size
     *  //Add vertex to adjacency list
     */
    public void addVertex(String label) {
        if(labelIndexes.get(label) != null)
            System.out.println("Vertex is already in graph\n");
        else {
            int index = getSize();
            labelIndexes.put(label, index);
            Vertex vertex = new Vertex(label, index);
            size += 1;

            adjacencyList.add(index, vertex);
        }
    }

    public void removeEdge(String label1, String label2) {
        if((getLabelIndex(label1) == -1) || (getLabelIndex(label2) == -1))
            System.out.println("Invalid edge");
        else {
            adjacencyList.get(getLabelIndex(label1)).removeEdge(label2);
            adjacencyList.get(getLabelIndex(label2)).removeEdge(label1);
        }
    }

    public void removeVertex(String label) {
        if(getLabelIndex(label) == -1)
            System.out.println("Vertex does not exist");
        else{
            LinkedList<Edge> tempEdgeList = adjacencyList.get(getLabelIndex(label)).getEdgeList();
            for(Edge e : tempEdgeList)
                removeEdge(label, e.getTargetLabel());

            adjacencyList.remove(getLabelIndex(label));
            size--;
        }
    }

    /***********************************************
     * WORKING
     * ********************************************/
    public void addEdge(String label1, String label2, long weight) {
        if(getLabelIndex(label1) == -1)
            System.out.println("Invalid edge. " + label1 + " is not a vertex");
        else if(getLabelIndex(label2) == -1)
            System.out.println("Invalid edge. Label 2 is not a vertex");
        else{
            Edge edge1 = new Edge(label2, weight);
            Edge edge2 = new Edge(label1, weight);

            LinkedList<Edge> tempList = adjacencyList.get(getLabelIndex(label1)).getEdgeList();

            if(tempList.contains(edge1)){
                adjacencyList.get(getLabelIndex(label1)).addEdge(edge1);
                adjacencyList.get(getLabelIndex(label2)).addEdge(edge2);
            }
            else System.out.println( "Edge already exists");
        }
    }

    public long shortestDistanceToVertex(String startLabel, String endLabel) {

        PriorityQueue<Pair<String, Long>> PQ = new PriorityQueue<>(
                getSize(),
                Comparator.comparingLong(Pair::getSecond)
        );

        long [] distances = new long[getSize()];

        for(int i = 0; i < getSize(); i++){
            distances[i] = Integer.MAX_VALUE;
        }
        distances[getLabelIndex(startLabel)] = 0;
        PQ.add(Pair.of(startLabel, 0L));

        while(!PQ.isEmpty()){
            Vertex vertex = adjacencyList.get(getLabelIndex(PQ.peek().getFirst()));
            PQ.offer(Pair.of(startLabel, 0L));

            for(Edge e : vertex.getEdgeList()){
                long temp = distances[vertex.getIndex()] + e.getWeight();
                if(temp < distances[getLabelIndex(e.getTargetLabel())]){
                    distances[getLabelIndex(e.getTargetLabel())] = temp;
                    PQ.offer(Pair.of(e.getTargetLabel(), e.getWeight()));
                }
            }
        }
        return distances[getLabelIndex(endLabel)];
    }

    public ArrayList<Vertex> getAdjacencyList() {
        return adjacencyList;
    }
}
