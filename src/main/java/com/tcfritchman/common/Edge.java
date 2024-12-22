package com.tcfritchman.common;

public class Edge<K> {

    private final Pair<Vertex<K>> vertexPair;
    private final int weight;

    public Edge(Vertex<K> v1, Vertex<K> v2, int weight) {
        this.vertexPair = new Pair<>(v1, v2);
        this.weight = weight;
    }

    public Pair<Vertex<K>> getVertexPair() {
        return vertexPair;
    }

    public int getWeight() {
        return weight;
    }
}
