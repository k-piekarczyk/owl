package owl.engine.e3D;

import owl.engine.e3D.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Stage3D extends JPanel {
    private int width;
    private int height;
    private double cameraDistance = 100;
    private Point2D originOffset;

    private List<Point3D> vertices = new ArrayList<>();

    public Stage3D(int width, int height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);

        this.width = width;
        this.height =height;

        originOffset = new Point2D.Double(width / 2, height / 2);
    }

    public Stage3D(int width, int height, double initCameraDistance) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);

        this.width = width;
        this.height =height;

        originOffset = new Point2D.Double(width / 2, height / 2);
        cameraDistance = initCameraDistance;
    }

    public void addVertex(Point3D vertex) {
        vertices.add(vertex);
    }

    public void addVertices(List<Point3D> vertices) {
        this.vertices.addAll(vertices);
    }

    private void drawPoint(Graphics2D g, Point3D point) {
        Point2D projected = point.project2D(cameraDistance);

        System.out.printf(point.toString() + " -> [x: %.1f y: %.1f]%n", projected.getX(), projected.getY());

        g.drawOval((int) projected.getX(), (int) projected.getY(), 5, 5);

        String pointInfo = point.toString();

        g.drawString(pointInfo, (int) projected.getX() - g.getFontMetrics().stringWidth(pointInfo)/2, (int) projected.getY() - 3);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.BLACK);
        for (Point3D vertex : vertices) {
            drawPoint(g2, vertex);
        }
    }

    public void startRender() {
        JFrame display = new JFrame();
        display.setTitle("Stage3D " + this.hashCode());
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setLocationRelativeTo(null);
        display.setResizable(false);

        display.add(this);
        display.pack();
        display.setVisible(true);

        repaint();
    }
}
