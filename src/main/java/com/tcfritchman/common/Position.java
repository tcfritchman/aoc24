package com.tcfritchman.common;

import java.util.Objects;

public record Position(int row, int col) {

    @Override
    public int row() {
        return row;
    }

    @Override
    public int col() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    public Position add(Position other) {
        return new Position(this.row + other.row, this.col + other.col);
    }

    public Position subtract(Position other) {
        return new Position(this.row - other.row, this.col - other.col);
    }
}
