package com.sakariaslilja.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuaternionTests {

    @Test
    @DisplayName("Quaternion toString")
    public void quaternionToString() {
        Quaternion q = new Quaternion(-1, 2, 2.7777, Math.PI);
        String expected = "Q(-1.000, 2.000, 2.778, 3.142)";
        assertEquals(expected, q.toString(), "The toString method should work as expected");
    }

    @Test
    @DisplayName("Quaternion equals")
    public void quaterionEquals() {
        Quaternion q1 = new Quaternion(1, 2, 3, 4);
        Quaternion q2 = new Quaternion(1, 2, 3, 4);
        Quaternion q3 = new Quaternion(1, 1, 1, 1);
        assertNotSame(q1, q2, "The object reference should not be the same");
        assertEquals(q1, q2, "They should be equal");
        assertNotEquals(q1, q3, "Non-equal objects should not be the same");
    }

    @Test
    @DisplayName("Quaternion angled constructor")
    public void angledConstructor() {
        DoubleVector3D vector = new DoubleVector3D(-2, 3, -6);
        double angle = 60.0; // Degrees
        Quaternion q = new Quaternion(vector, Math.PI * angle / 180.0);

        double expectedW = 0.5 * Math.sqrt(3);
        double error = 0.000000001;
        
        DoubleVector3D expectedVector = vector.duplicate();
        expectedVector.mul(1.0 / 14.0);
        DoubleVector3D quaternionVector = new DoubleVector3D(q.getX(), q.getY(), q.getZ());

        assertTrue(expectedW - error <= q.getW() && expectedW + error >= q.getW(), "The w-component should be within a range");
        assertEquals(expectedVector.toString(), quaternionVector.toString(), "The vector component should be correct");
    }

    @Test
    @DisplayName("Quaternion conjugate")
    public void conjugate() {
        Quaternion q = new Quaternion(1, 1, 2, -3);
        q.conjugate();

        Quaternion expected = new Quaternion(1, -1, -2, 3);
        assertEquals(expected, q, "The conjugate should work as expected");
    }

}
