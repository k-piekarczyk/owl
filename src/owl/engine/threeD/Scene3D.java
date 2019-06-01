package owl.engine.threeD;

import owl.engine.common.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Scene3D implements Scene {

    private List<Point3D> vertices = new ArrayList<>();
    private List<Camera3D> cameras = new ArrayList<>();

    public void addVertex(Point3D vertex) {
        vertices.add(vertex);
    }

    public void addVertices(List<Point3D> vertices) {
        this.vertices.addAll(vertices);
    }

    public void addCamera(Camera3D camera) {
        camera.setScene(this);
        cameras.add(camera);
    }

    List<Point3D> getVertices() {
        return vertices;
    }

    public void start() {
        for (Camera3D camera : cameras) {
            camera.start();
        }
    }
}
