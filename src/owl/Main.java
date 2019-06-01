package owl;

import owl.engine.threeD.Camera3D;
import owl.engine.threeD.Point3D;
import owl.engine.threeD.Scene3D;
import owl.utils.Angle;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Scene3D stage = new Scene3D();

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

//        Camera3D camera1 = new Camera3D(
//                "uno",
//                500,
//                500,
//                new Point3D(30, 20, 300),
//                new Angle(90),
//                new Angle(-5),
//                new Angle(0),
//                new Angle(0)
//        );
//
//        camera1.writeStatus();
//        stage.addCamera(camera1);

        Camera3D camera2 = new Camera3D(
                "dos",
                500,
                500,
                new Point3D(30, 20, 500),
                new Angle(90),
                new Angle(0),
                new Angle(0),
                new Angle(0)
        );

        System.out.println();
        camera2.writeStatus();
        stage.addCamera(camera2);

        stage.start();
    }
}
