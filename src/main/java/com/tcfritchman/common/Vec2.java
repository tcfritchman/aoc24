package com.tcfritchman.common;

public record Vec2(int x, int y) {

    public Vec2 add(Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public Vec2 subtract(Vec2 other) {
        return new Vec2(this.x - other.x, this.y - other.y);
    }

    public Vec2 rotateClockwise() {
        return new Vec2(-y, x);
    }

    public Vec2 rotateCounterClockwise() {
        return new Vec2(y, -x);
    }

    @Override
    public String toString() {
        return "Vec2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
