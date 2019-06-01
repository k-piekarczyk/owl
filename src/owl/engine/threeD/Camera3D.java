package owl.engine.threeD;

import owl.engine.common.Camera;
import owl.utils.Angle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class Camera3D extends JPanel implements Camera, KeyListener {
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

    private static int frameRate = 60;
    private static float interval = 1000.0f / frameRate;

    public Camera3D(String name, int vw, int vh, Point3D position, Angle FOV, Angle pitch, Angle yaw, Angle roll) {
        setSize(vw, vh);
        setPreferredSize(new Dimension(vw, vh));
        setFocusable(true);

        this.position = position;
        this.vw = vw;
        this.vh = vh;
        this.FOV = FOV;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
        this.name = name;

        updateSettings();
        addKeyListener(this);
    }

    private void updateSettings() {
        calculateDistance();
        calculateViewPlaneOrigin();
    }

    public void setScene(Scene3D scene) {
        this.scene = scene;
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

    private Point3D translatePointToCamera(Point3D point) {
        Point3D p = new Point3D(point);
        p.substract(position);

        double x0 = p.getX();
        double y0 = p.getY();
        double z0 = p.getZ();

        double r = Math.sqrt(x0 * x0 + y0 * y0 + z0 * z0);
        double pitch0 = Math.asin(y0 / r);
        double yaw0;
        if (z0 == 0.0) yaw0 = 0.0;
        else yaw0 = Math.atan(x0 / z0);

        double pitch1 = pitch0 - pitch.getRadians();
        double yaw1 = yaw0 - yaw.getRadians();

        double x1 = r * Math.cos(pitch1) * Math.sin(yaw1);
        double y1 = r * Math.sin(pitch1);
        double z1 = r * Math.cos(pitch1) * Math.cos(yaw1);

        p.setTo(x1, y1, z1);
        p.add(position);
        p.substract(viewPlaneOrigin);

        return p;
    }

    private Point2D offsetOrigin(Point2D point) {
        return new Point2D.Double(point.getX() + (vw / 2.0), point.getY() + (vh / 2.0));
    }

    private void drawPoint(Graphics2D g, Point3D point) {
        Point3D translated = translatePointToCamera(point);
        Point2D projected = offsetOrigin(translated.project2D(d));

//        System.out.printf(point.toString() + " -> [x: %.1f y: %.1f]%n", projected.getX(), projected.getY());

        g.drawOval((int) projected.getX(), (int) projected.getY(), 5, 5);

        String pointInfo = point.toString();

        g.drawString(pointInfo, (int) projected.getX() - g.getFontMetrics().stringWidth(pointInfo) / 2, (int) projected.getY() - 3);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.BLACK);
        for (Point3D vertex : scene.getVertices()) {
            drawPoint(g2, vertex);
        }
    }

    private void loop() {
        repaint();
        while (true) {
            float time = System.currentTimeMillis();
            repaint();
            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    public void start() {
        JFrame display = new JFrame();
        display.setTitle(String.format("Camera %s [%d]", name, hashCode()));
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setLocationRelativeTo(null);
        display.setResizable(false);

        display.add(this);
        display.pack();
        display.setVisible(true);

        loop();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        switch (c) {
            case KeyEvent.VK_R:
                position.setZ(position.getZ() - 5.0);
                updateSettings();
                break;
            case KeyEvent.VK_F:
                position.setZ(position.getZ() + 5.0);
                updateSettings();
                break;
            case KeyEvent.VK_A:
                position.setX(position.getX() - 5.0);
                updateSettings();
                break;
            case KeyEvent.VK_D:
                position.setX(position.getX() + 5.0);
                updateSettings();
                break;
            case KeyEvent.VK_W:
                position.setY(position.getY() - 5.0);
                updateSettings();
                break;
            case KeyEvent.VK_S:
                position.setY(position.getY() + 5.0);
                updateSettings();
                break;
            case KeyEvent.VK_UP:
                pitch.setDegree(pitch.getDegrees() - 0.1);
                updateSettings();
                break;
            case KeyEvent.VK_DOWN:
                pitch.setDegree(pitch.getDegrees() + 0.1);
                updateSettings();
                break;
            case KeyEvent.VK_LEFT:
                yaw.setDegree(yaw.getDegrees() - 0.1);
                updateSettings();
                break;
            case KeyEvent.VK_RIGHT:
                yaw.setDegree(yaw.getDegrees() + 0.1);
                updateSettings();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
