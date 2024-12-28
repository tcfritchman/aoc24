package com.tcfritchman.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Graph<K, V> {

    private final Map<K,Vertex<V>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public Vertex<V> addVertex(K key, V data) {
        Vertex<V> vertex = new Vertex<>(data);
        vertices.put(key, vertex);
        return vertex;
    }

    public Vertex<V> getVertex(K key) {
        return vertices.get(key);
    }

    public boolean containsKey(K key) {
        return vertices.containsKey(key);
    }

    public Collection<Vertex<V>> vertices() {
        return vertices.values();
    }

    public void addEdge(Vertex<V> v1, Vertex<V> v2, int weight) {
        Edge<V> edge = new Edge<>(v1, v2, weight);
        v1.addEdge(edge);
        v2.addEdge(edge);
    }

    public void addDirectedEdge(Vertex<V> from, Vertex<V> to) {
        Edge<V> edge = new Edge<>(from, to, 0);
        from.addEdge(edge);
    }
}
