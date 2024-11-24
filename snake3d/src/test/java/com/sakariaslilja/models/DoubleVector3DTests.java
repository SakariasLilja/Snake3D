package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.Math;
import java.util.function.Predicate;

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
        DoubleVector3D vector3d = new DoubleVector3D(2.5, -0.4, -0.9);
        DoubleVector3D result = vector3d.round();
        DoubleVector3D expected = new DoubleVector3D(3, 0, -1);
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

    @Test
    @DisplayName("DoubleVector3D convert to Vector3D")
    void convertToVector3D() {
        DoubleVector3D vector = new DoubleVector3D(0.5, -0.2, -1.9);
        Vector3D converted = vector.toVector3D();
        Vector3D expected = new Vector3D(1, 0, -2);

        assertEquals(expected, converted, "The toVector3D method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D mul")
    void mulDoubleVector() {
        int scalar = 10;
        DoubleVector3D vector = new DoubleVector3D(1.8, -1, 0.5);
        DoubleVector3D expected = new DoubleVector3D(18, -10, 5);

        assertEquals(expected, vector.mul(scalar), "The mul method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D toString")
    void doubleVectorToString() {
        DoubleVector3D vector = new DoubleVector3D(Math.PI, -0, 22.1);
        String expected = "DV(3.142  0.000  22.100)";

        assertEquals(expected, vector.toString(), "The toString method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D add")
    void doubleVectorAdd() {
        DoubleVector3D first = Vector3D.Backward.toDoubleVector3D();
        DoubleVector3D second = Vector3D.Forward.toDoubleVector3D();

        DoubleVector3D expected = new DoubleVector3D(0, 0, 0);

        assertEquals(expected, first.add(second), "The add method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D duplicate")
    void doubleVectorDuplicate() {
        DoubleVector3D vector = new DoubleVector3D(1, 2, 2);
        DoubleVector3D duplicate = vector.duplicate();

        assertEquals(vector, duplicate, "The duplicate method should produce a copy");
        assertNotEquals(true, vector == duplicate, "The duplicate should create a new object reference");
    }
    
    @Test
    @DisplayName("DoubleVector3D negate")
    void doubleVectorNegate() {
        DoubleVector3D vector = new DoubleVector3D(1, 2, 3);
        DoubleVector3D negated = vector.neg();
        DoubleVector3D expected = new DoubleVector3D(-1, -2, -3);

        assertEquals(expected, negated, "The negate method should work as expected");
    }
    
    @Test
    @DisplayName("DoubleVector3D exists")
    void doubleVectorExists() {
        DoubleVector3D vector = new DoubleVector3D(1, 1, -1);
        Predicate<Double> isNegative = (d) -> d.doubleValue() < 0;
        Predicate<Double> isZero = (d) -> d.doubleValue() == 0;

        assertEquals(true, vector.exists(isNegative), "The exists method should work");
        assertNotEquals(true, vector.exists(isZero), "The exists method should not give false results");
    }
    
}
