package owl;

import owl.engine.threeD.Point3D;
import owl.engine.threeD.Scene3D;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Scene3D stage = new Scene3D(500, 500);

        List<Point3D> vertices = List.of(
                new Point3D(-30, 30, 0),
                new Point3D(-30, -30, 0),
                new Point3D(30, -30, 0),
                new Point3D(30, 30, 0),

                new Point3D(-30, 30, 30),
                new Point3D(-30, -30, 30),
                new Point3D(30, -30, 30),
                new Point3D(30, 30, 30)
        );

        stage.addVertices(vertices);
        stage.startRender();
    }
}
