package owl;

import owl.engine.e3D.Point3D;
import owl.engine.e3D.Stage3D;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Stage3D stage = new Stage3D(500, 500);

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
