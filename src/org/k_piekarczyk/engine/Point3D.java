package org.k_piekarczyk.engine;

import java.awt.geom.Point2D;

public class Point3D {
    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point2D project2D() {
        return new Point2D.Double(x, y);
    }
}
