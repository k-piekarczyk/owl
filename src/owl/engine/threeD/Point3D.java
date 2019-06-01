package owl.engine.threeD;

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

    public Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Point3D(Point3D point) {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }

    /**
     * @param d camera's distance from the view plane
     * @return point projected to on the view plane
     */
    public Point2D project2D(double d) {
        if (z != 0)
            return new Point2D.Double(x * d / z, y * d / z);
        else
            return new Point2D.Double(x, y);
    }

    public void add(Point3D point) {
        x += point.getX();
        y += point.getY();
        z += point.getZ();
    }

    public void add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void substract(Point3D point) {
        x -= point.getX();
        y -= point.getY();
        z -= point.getZ();
    }

    public void substract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= x;
    }

    public void setTo(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setTo(Point3D point) {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }

    @Override
    public String toString() {
        return String.format("[x: %.1f y: %.1f z: %.1f]", x, y, z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
