package owl.utils;

public class Angle {
    private double degree;

    public Angle() {
        degree = 0.0;
    }

    public Angle(double degree) {
        this.degree = degree;
    }

    public double getDegrees() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public double getRadians() {
        return degree / 180;
    }
}
