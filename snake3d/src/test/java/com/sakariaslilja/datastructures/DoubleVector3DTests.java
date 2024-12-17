package com.sakariaslilja.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.Math;
import java.util.function.Predicate;

public class DoubleVector3DTests {

    @Test
    @DisplayName("DoubleVector3D equals")
    public void doubleVector3DEquals() {
        DoubleVector3D first = new DoubleVector3D(1, 2, 3);
        DoubleVector3D second = new DoubleVector3D(5, -3, 8);
        DoubleVector3D third = new DoubleVector3D(1, 2, 3);
        assertEquals(first, first, "The equals method should return true if they are the same object");
        assertEquals(first, third, "The equals method should return true if they have the same values");
        assertNotEquals(first, second, "The equals method should return false if the values are not the same");
    }

    @Test
    @DisplayName("DoubleVector3D round")
    public void round() {
        DoubleVector3D vector3d = new DoubleVector3D(2.5, -0.4, -0.9);
        vector3d.round();
        DoubleVector3D expected = new DoubleVector3D(3, 0, -1);
        assertEquals(expected, vector3d, "The round method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D convert to Vector3D")
    public void convertToVector3D() {
        DoubleVector3D vector = new DoubleVector3D(0.5, -0.2, -1.9);
        Vector3D converted = vector.toVector3D();
        Vector3D expected = new Vector3D(1, 0, -2);

        assertEquals(expected, converted, "The toVector3D method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D mul")
    public void mulDoubleVector() {
        int scalar = 10;
        DoubleVector3D vector = new DoubleVector3D(1.8, -1, 0.5);
        vector.mul(scalar);
        DoubleVector3D expected = new DoubleVector3D(18, -10, 5);

        assertEquals(expected, vector, "The mul method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D toString")
    public void doubleVectorToString() {
        DoubleVector3D vector = new DoubleVector3D(Math.PI, -0, 22.1);
        String expected = "DV(3.142  0.000  22.100)";

        assertEquals(expected, vector.toString(), "The toString method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D add")
    public void doubleVectorAdd() {
        DoubleVector3D first = Heading.BACKWARD.vec.toDoubleVector3D();
        DoubleVector3D second = Heading.FORWARD.vec.toDoubleVector3D();
        first.add(second);

        DoubleVector3D expected = new DoubleVector3D(0, 0, 0);

        assertEquals(expected, first, "The add method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D duplicate")
    public void doubleVectorDuplicate() {
        DoubleVector3D vector = new DoubleVector3D(1, 2, 2);
        DoubleVector3D duplicate = vector.duplicate();

        assertEquals(vector, duplicate, "The duplicate method should produce a copy");
        assertNotEquals(true, vector == duplicate, "The duplicate should create a new object reference");
    }
    
    @Test
    @DisplayName("DoubleVector3D negate")
    public void doubleVectorNegate() {
        DoubleVector3D vector = new DoubleVector3D(1, 2, 3);
        vector.neg();
        DoubleVector3D expected = new DoubleVector3D(-1, -2, -3);

        assertEquals(expected, vector, "The negate method should work as expected");
    }
    
    @Test
    @DisplayName("DoubleVector3D exists")
    public void doubleVectorExists() {
        DoubleVector3D vector = new DoubleVector3D(1, 1, -1);
        Predicate<Double> isNegative = (d) -> d.doubleValue() < 0;
        Predicate<Double> isZero = (d) -> d.doubleValue() == 0;

        assertEquals(true, vector.exists(isNegative), "The exists method should work");
        assertNotEquals(true, vector.exists(isZero), "The exists method should not give false results");
    }

    @Test
    @DisplayName("DoubleVector3D magnitude")
    public void magnitude() {
        DoubleVector3D vector = new DoubleVector3D(3, 4, 5);
        double expected = Math.sqrt(50);
        assertEquals(expected, vector.magnitude(), "The magnitude should be as expected");
    }

    @Test
    @DisplayName("DoubleVector3D normalize")
    public void normalize() {
        DoubleVector3D vector = new DoubleVector3D(1, 2, 3);
        vector.normalized();
        assertEquals(1.0, vector.magnitude(), "The normalize method should work as expected");
    }

    @Test
    @DisplayName("DoubleVector3D forAll")
    public void forAll() {
        DoubleVector3D vector = new DoubleVector3D(5, -5, 10);
        Predicate<Double> fiveDivides = d -> d % 5 == 0;
        Predicate<Double> positive = d -> d > 0;
        assertTrue(vector.forAll(fiveDivides), "The forAll method should work as expected");
        assertFalse(vector.forAll(positive), "The forAll method should not return incorrect result");
    }

    @Test
    @DisplayName("DoubleVector3D floor")
    public void floorConvert() {
        DoubleVector3D vector1 = new DoubleVector3D(1, 0, -3);
        DoubleVector3D vector2 = new DoubleVector3D(1.9, -2.2, -3.77);

        Vector3D expected1 = new Vector3D(1, 0, -3);
        Vector3D expected2 = new Vector3D(1, -2, -3);

        assertEquals(expected1, vector1.flooredVector3D(), "The values should not change");
        assertEquals(expected2, vector2.flooredVector3D(), "The values should be floored correctly");
    }
    
}
