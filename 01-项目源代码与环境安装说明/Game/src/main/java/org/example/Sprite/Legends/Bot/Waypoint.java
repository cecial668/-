package org.example.Sprite.Legends.Bot;
public class Waypoint {
    double x;
    double y;

    public Waypoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + "  " + y;
    }
}