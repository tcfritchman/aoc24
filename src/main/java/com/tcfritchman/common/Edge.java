package com.tcfritchman.common;

public class Edge<V> {

    private final Pair<Vertex<V>> vertexPair;
    private final int weight;

    public Edge(Vertex<V> v1, Vertex<V> v2, int weight) {
        this.vertexPair = new Pair<>(v1, v2);
        this.weight = weight;
    }

    public Pair<Vertex<V>> getVertexPair() {
        return vertexPair;
    }

    public int getWeight() {
        return weight;
    }
}
