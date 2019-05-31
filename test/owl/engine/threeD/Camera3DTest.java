package owl.engine.threeD;

import org.junit.jupiter.api.Test;
import owl.utils.Angle;
import owl.utils.General;

import static org.junit.jupiter.api.Assertions.*;

class Camera3DTest {
    private static final double COMP_PREC = 0.00001;

    @Test
    void should_face_front() {
        Camera3D camera = new Camera3D(
                "front",
                500,
                500,
                new Point3D(30, 20, 10),
                new Angle(90),
                new Angle(0),
                new Angle(0),
                new Angle(0)
        );

        Point3D position = camera.getPosition();
        Point3D vPOrigin = camera.getViewPlaneOrigin();
        double distance = camera.getD();

        camera.writeStatus();

        assertEquals(distance, vPOrigin.getZ() - position.getZ());
        assertTrue(General.compareDoublesWithPrecision(position.getY(), vPOrigin.getY(), COMP_PREC));
        assertTrue(General.compareDoublesWithPrecision(position.getX(), vPOrigin.getX(), COMP_PREC));
    }

    @Test
    void should_face_back() {
        Camera3D camera = new Camera3D(
                "back",
                500,
                500,
                new Point3D(30.0, 20.0, 10.0),
                new Angle(90),
                new Angle(0),
                new Angle(180),
                new Angle(0)
        );

        Point3D position = camera.getPosition();
        Point3D vPOrigin = camera.getViewPlaneOrigin();
        double distance = camera.getD();

        camera.writeStatus();

        assertEquals(-distance, vPOrigin.getZ() - position.getZ());
        assertTrue(General.compareDoublesWithPrecision(position.getY(), vPOrigin.getY(), COMP_PREC));
        assertTrue(General.compareDoublesWithPrecision(position.getX(), vPOrigin.getX(), COMP_PREC));
    }

    @Test
    void should_face_up() {
        Camera3D camera = new Camera3D(
                "up",
                500,
                500,
                new Point3D(30.0, 20.0, 10.0),
                new Angle(90),
                new Angle(90),
                new Angle(0),
                new Angle(0)
        );

        Point3D position = camera.getPosition();
        Point3D vPOrigin = camera.getViewPlaneOrigin();
        double distance = camera.getD();

        camera.writeStatus();

        assertEquals(distance, vPOrigin.getY() - position.getY());
        assertTrue(General.compareDoublesWithPrecision(position.getZ(), vPOrigin.getZ(), COMP_PREC));
        assertTrue(General.compareDoublesWithPrecision(position.getX(), vPOrigin.getX(), COMP_PREC));
    }

    @Test
    void should_face_down() {
        Camera3D camera = new Camera3D(
                "down",
                500,
                500,
                new Point3D(30.0, 20.0, 10.0),
                new Angle(90),
                new Angle(-90),
                new Angle(0),
                new Angle(0)
        );

        Point3D position = camera.getPosition();
        Point3D vPOrigin = camera.getViewPlaneOrigin();
        double distance = camera.getD();

        camera.writeStatus();

        assertEquals(-distance, vPOrigin.getY() - position.getY());
        assertTrue(General.compareDoublesWithPrecision(position.getZ(), vPOrigin.getZ(), COMP_PREC));
        assertTrue(General.compareDoublesWithPrecision(position.getX(), vPOrigin.getX(), COMP_PREC));
    }

    @Test
    void should_face_right() {
        Camera3D camera = new Camera3D(
                "right",
                500,
                500,
                new Point3D(30.0, 20.0, 10.0),
                new Angle(90),
                new Angle(0),
                new Angle(90),
                new Angle(0)
        );

        Point3D position = camera.getPosition();
        Point3D vPOrigin = camera.getViewPlaneOrigin();
        double distance = camera.getD();

        camera.writeStatus();

        assertEquals(distance, vPOrigin.getX() - position.getX());
        assertTrue(General.compareDoublesWithPrecision(position.getZ(), vPOrigin.getZ(), COMP_PREC));
        assertTrue(General.compareDoublesWithPrecision(position.getY(), vPOrigin.getY(), COMP_PREC));
    }

    @Test
    void should_face_left() {
        Camera3D camera = new Camera3D(
                "left",
                500,
                500,
                new Point3D(30.0, 20.0, 10.0),
                new Angle(90),
                new Angle(0),
                new Angle(-90),
                new Angle(0)
        );

        Point3D position = camera.getPosition();
        Point3D vPOrigin = camera.getViewPlaneOrigin();
        double distance = camera.getD();

        camera.writeStatus();

        assertEquals(-distance, vPOrigin.getX() - position.getX());
        assertTrue(General.compareDoublesWithPrecision(position.getZ(), vPOrigin.getZ(), COMP_PREC));
        assertTrue(General.compareDoublesWithPrecision(position.getY(), vPOrigin.getY(), COMP_PREC));
    }
}