package owl.engine.threeD;

import owl.engine.common.Camera;
import owl.utils.Angle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Camera3D extends JPanel implements Camera {
    private Point3D position;

    private int vw; // view width
    private int vh; // view height

    private Angle FOV;

    private double d; // distance from camera
    private Point3D viewPlaneOrigin = new Point3D();

    private Angle pitch; // x axis rotation
    private Angle yaw; // y axis rotation
    private Angle roll; // z axis rotation

    private Scene3D scene;
    private String name;

    public Camera3D(String name, int vw, int vh, Point3D position, Angle FOV, Angle pitch, Angle yaw, Angle roll) {
        this.position = position;
        this.vw = vw;
        this.vh = vh;
        this.FOV = FOV;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
        this.name = name;

        updateSettings();
    }

    private void updateSettings() {
        calculateDistance();
        calculateViewPlaneOrigin();
    }

    private void calculateDistance() {
        double alpha = FOV.getRadians() / 2;
        double half = vw / 2;
        d = half * (1.0 / Math.tan(alpha));
    }

    private void calculateViewPlaneOrigin() {
        double x = d * Math.cos(pitch.getRadians()) * Math.sin(yaw.getRadians());
        double y = d * Math.sin(pitch.getRadians());
        double z = d * Math.cos(pitch.getRadians()) * Math.cos(yaw.getRadians());

        viewPlaneOrigin.setTo(position);
        viewPlaneOrigin.add(x, y, z);
    }

    public void writeStatus() {
        System.out.printf("Camera %s status:%n", name);
        System.out.printf("\t- view width: %d%n", vw);
        System.out.printf("\t- view height: %d%n", vh);
        System.out.printf("\t- FOV: %s%n", FOV.toString());
        System.out.printf("\t- distance to view plane: %.2f%n", d);
        System.out.printf("\t- position: %s%n", position.toString());
        System.out.printf("\t- view plane origin: %s%n", viewPlaneOrigin.toString());
    }

    private void drawPoint(Graphics2D g, Point3D point) {
        Point2D projected = point.project2D(d);

        System.out.printf(point.toString() + " -> [x: %.1f y: %.1f]%n", projected.getX(), projected.getY());

        g.drawOval((int) projected.getX(), (int) projected.getY(), 5, 5);

        String pointInfo = point.toString();

        g.drawString(pointInfo, (int) projected.getX() - g.getFontMetrics().stringWidth(pointInfo) / 2, (int) projected.getY() - 3);
    }

    public Point3D getPosition() {
        return position;
    }

    public Point3D getViewPlaneOrigin() {
        return viewPlaneOrigin;
    }

    public double getD() {
        return d;
    }
}
