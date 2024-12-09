package com.tcfritchman.common;

import java.util.Objects;
import java.util.stream.Stream;

public record Pair<T>(T left, T right) {
    @Override
    public T left() {
        return left;
    }

    @Override
    public T right() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?> pair = (Pair<?>) o;
        return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public Stream<T> stream() {
        return Stream.of(left, right);
    }
}
