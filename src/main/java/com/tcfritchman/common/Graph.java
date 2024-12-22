package com.tcfritchman.common;

import java.util.HashSet;
import java.util.Set;

public class Graph<V> {

    private final Set<Vertex<V>> vertices;

    public Graph() {
        vertices = new HashSet<>();
    }

    public Vertex<V> addVertex(V data) {
        Vertex<V> vertex = new Vertex<>(data);
        vertices.add(vertex);
        return vertex;
    }

    public void addEdge(Vertex<V> v1, Vertex<V> v2, int weight) {
        Edge<V> edge = new Edge<>(v1, v2, weight);
        v1.addEdge(edge);
        v2.addEdge(edge);
    }
}
