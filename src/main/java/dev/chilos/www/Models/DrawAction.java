package dev.chilos.www.Models;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class DrawAction {
    private List<Point> points;
    private String color;

    public DrawAction() {
    }

    public DrawAction(List<Point> points, String color) {
        this.points = points;
        this.color = color;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Color toAwtColor() {
        return color != null || color.isBlank() ? Color.decode(color) : Color.WHITE;
    }
}
