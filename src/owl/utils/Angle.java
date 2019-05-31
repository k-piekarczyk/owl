package owl.utils;

public class Angle {
    private double degree;

    public Angle() {
        degree = 0.0;
    }

    public Angle(double degree) {
        this.degree = degree;
        normalize();
    }

    private void normalize() {
        if (degree >= 360) {
            double x = Math.floor(degree / 360.0);
            degree = degree - 360.0 * x;
        } else if (degree < 0) {
            double abs = Math.abs(degree);
            double x = Math.floor(abs / 360.0);

            degree = 360 - abs * (1 - x);
        }
    }

    public double getDegrees() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
        normalize();
    }

    public double getRadians() {
        return degree / 180;
    }
}
