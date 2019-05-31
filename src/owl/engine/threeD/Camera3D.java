package owl.engine.threeD;

import owl.engine.common.Camera;
import owl.utils.Angle;

import javax.swing.*;

public class Camera3D extends JPanel implements Camera {
    private Point3D position;

    private int wv; // view width
    private int wh; // view height

    private Angle FOV;

    private double d; // distance from camera

    private Angle pitch; // x axis rotation
    private Angle yaw; // y axis rotation
    private Angle roll; // z axis rotation

    private Scene3D scene;
    private String name;
}
