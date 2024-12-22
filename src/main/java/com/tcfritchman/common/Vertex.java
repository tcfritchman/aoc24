package com.tcfritchman.common;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Vertex<K> {
    private final Set<Edge<K>> edges;
    private final K data;

    public Vertex(K data) {
        this.data = data;
        this.edges = new HashSet<>();
    }

    public Set<Vertex<K>> getNeighbors() {
        return edges.stream()
                .map(edge -> {
                    Pair<Vertex<K>> vertexPair = edge.getVertexPair();
                    if (vertexPair.left().equals(this)) {
                        return vertexPair.right();
                    } else {
                        return vertexPair.left();
                    }
                })
                .collect(Collectors.toSet());
    }

    public K getData() {
        return data;
    }

    public boolean isAdjacent(Vertex<K> other) {
        return getNeighbors().contains(other);
    }

    protected void addEdge(Edge<K> edge) {
        edges.add(edge);
    }
}
