package com.tcfritchman.common;

import java.util.HashMap;
import java.util.Map;

public class Graph<K> {

    private final Map<K,Vertex<K>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public Vertex<K> addVertex(K data) {
        Vertex<K> vertex = new Vertex<>(data);
        vertices.put(data, vertex);
        return vertex;
    }

    public boolean containsKey(K data) {
        return vertices.containsKey(data);
    }

    public void addEdge(Vertex<K> v1, Vertex<K> v2, int weight) {
        Edge<K> edge = new Edge<>(v1, v2, weight);
        v1.addEdge(edge);
        v2.addEdge(edge);
    }
}
