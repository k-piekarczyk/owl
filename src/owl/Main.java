package owl;

import owl.engine.threeD.Camera3D;
import owl.engine.threeD.Point3D;
import owl.utils.Angle;

public class Main {

    public static void main(String[] args) {
//        Scene3D stage = new Scene3D(500, 500);

//        List<Point3D> vertices = List.of(
//                new Point3D(-30, 30, 0),
//                new Point3D(-30, -30, 0),
//                new Point3D(30, -30, 0),
//                new Point3D(30, 30, 0),
//
//                new Point3D(-30, 30, 30),
//                new Point3D(-30, -30, 30),
//                new Point3D(30, -30, 30),
//                new Point3D(30, 30, 30)
//        );
//
//        stage.addVertices(vertices);
//        stage.startRender();

        Camera3D camera = new Camera3D(
                "uno",
                500,
                500,
                new Point3D(30, 20, 10),
                new Angle(90),
                new Angle(90),
                new Angle(0),
                new Angle(0)
        );

        camera.writeStatus();
    }
}
