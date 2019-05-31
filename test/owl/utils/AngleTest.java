package owl.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AngleTest {
    @Test
    void if_no_arguments_in_constructor_should_measure_0_degrees() {
        Angle angle = new Angle();

        assertEquals(0, angle.getDegrees());
    }

    @Test
    void if_no_arguments_in_constructor_should_measure_0_radians() {
        Angle angle = new Angle();

        assertEquals(0, angle.getRadians());
    }

    @Test
    void should_measure_correctly_in_degrees() {
        Angle angle = new Angle(180);

        assertEquals(180, angle.getDegrees());
    }

    @Test
    void should_measure_correctly_in_radians() {
        Angle angle = new Angle(180);

        assertEquals(1, angle.getRadians());
    }
}