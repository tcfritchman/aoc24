package com.tcfritchman.common;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Vertex<V> {
    private final Set<Edge<V>> edges;
    private final V data;

    public Vertex(V data) {
        this.data = data;
        this.edges = new HashSet<>();
    }

    public Set<Vertex<V>> getNeighbors() {
        return edges.stream()
                .map(edge -> {
                    Pair<Vertex<V>> vertexPair = edge.getVertexPair();
                    if (vertexPair.left() == this) {
                        return vertexPair.right();
                    } else {
                        return vertexPair.left();
                    }
                })
                .collect(Collectors.toSet());
    }

    public V getData() {
        return data;
    }

    public boolean isAdjacent(Vertex<V> other) {
        return getNeighbors().contains(other);
    }

    protected void addEdge(Edge<V> edge) {
        edges.add(edge);
    }
}
