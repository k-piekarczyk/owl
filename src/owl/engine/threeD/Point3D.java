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
}
