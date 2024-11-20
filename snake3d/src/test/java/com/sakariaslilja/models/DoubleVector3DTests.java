package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.Math;

public class DoubleVector3DTests {

    @Test
    @DisplayName("DoubleVector3D getX")
    void getX() {
        double x = 7.0;
        DoubleVector3D vector3d = new DoubleVector3D(x, 0, 0);
        assertEquals(x, vector3d.getX(), "The getX method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D getY")
    void getY() {
        double y = -3.5;
        DoubleVector3D vector3d = new DoubleVector3D(0, y, 0);
        assertEquals(y, vector3d.getY(), "The getY method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D getZ")
    void getZ() {
        double z = 0.004;
        DoubleVector3D vector3d = new DoubleVector3D(0, 0, z);
        assertEquals(z, vector3d.getZ(), "The getZ method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D equals")
    void doubleVector3DEquals() {
        DoubleVector3D first = new DoubleVector3D(1, 2, 3);
        DoubleVector3D second = new DoubleVector3D(5, -3, 8);
        DoubleVector3D third = new DoubleVector3D(1, 2, 3);
        assertEquals(first, first, "The equals method should return true if they are the same object");
        assertEquals(first, third, "The equals method should return true if they have the same values");
        assertNotEquals(first, second, "The equals method should return false if the values are not the same");
    }

    @Test
    @DisplayName("DoubleVector3D round")
    void round() {
        DoubleVector3D vector3d = new DoubleVector3D(1.9, -0.4, -0.9);
        DoubleVector3D result = vector3d.round();
        DoubleVector3D expected = new DoubleVector3D(2, 0, -1);
        assertEquals(expected, result, "The round method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D rotateX")
    void rotateX() {
        double angle = Math.PI * 0.5;
        DoubleVector3D vector3d = new DoubleVector3D(1, 2, 3);
        DoubleVector3D result = vector3d.rotateX(angle).round();
        DoubleVector3D expected = new DoubleVector3D(1, -3, 2);
        assertEquals(expected, result, "The rotateX method should give correct result");
    }

    @Test
    @DisplayName("DoubleVector3D rotateY")
    void rotateY() {
        double angle = Math.PI * 0.5;
        DoubleVector3D vector3d = new DoubleVector3D(1, 2, 3);
        DoubleVector3D result = vector3d.rotateY(angle).round();
        DoubleVector3D expected = new DoubleVector3D(3, 2, -1);
        assertEquals(expected, result, "The rotateY method should give correct result");
    }

    @Test
    @DisplayName("DoubleVector3D rotateZ")
    void rotateZ() {
        double angle = Math.PI * 0.5;
        DoubleVector3D vector3d = new DoubleVector3D(1, 2, 3);
        DoubleVector3D result = vector3d.rotateZ(angle).round();
        DoubleVector3D expected = new DoubleVector3D(-2, 1, 3);
        assertEquals(expected, result, "The rotateZ method should give correct result");
    }
    
}
