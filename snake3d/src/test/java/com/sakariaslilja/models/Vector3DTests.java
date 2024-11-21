package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Vector3DTests {

    @Test
    @DisplayName("Vector3D toString")
    void returnsCorrectString() {
        Vector3D vector3d = new Vector3D(0, 0, 0);
        String expected = "Vector3D(0, 0, 0)";
        assertEquals(expected, vector3d.toString(), "Vector3D(0, 0, 0) should be returned when toString is called");
    }

    @Test
    @DisplayName("Vector3D getX")
    void getX() {
        Vector3D vector3d = new Vector3D(0, 0, 0);
        int expected = 0;
        assertEquals(expected, vector3d.getX(), "getX() should return correct value");
    }

    @Test
    @DisplayName("Vector3D getY")
    void getY() {
        Vector3D vector3d = new Vector3D(0, 5, 0);
        int expected = 5;
        assertEquals(expected, vector3d.getY(), "getY() should return correct value");
    }

    @Test
    @DisplayName("Vector3D getZ")
    void getZ() {
        Vector3D vector3d = new Vector3D(0, 0, -3);
        int expected = -3;
        assertEquals(expected, vector3d.getZ(), "getZ() should return correct value");
    }

    @Test
    @DisplayName("Vector3D add")
    void addVector3D() {
        Vector3D first = Vector3D.Right;
        Vector3D second = Vector3D.Down;
        Vector3D result = first.add(second);
        Vector3D expected = new Vector3D(1, 1, 0);
        assertEquals(expected, result, "The add method should work as expected");
    }

    @Test
    @DisplayName("Vector3D negate")
    void negateVector3D() {
        Vector3D vector3d = Vector3D.Backward;
        Vector3D expected = Vector3D.Forward;
        assertEquals(expected, vector3d.neg(), "The neg method should work as expected");
    }

    @Test
    @DisplayName("Vector3D scalar multiply")
    void scalarMul() {
        int scalar = 5;
        Vector3D vector3d = Vector3D.Right;
        Vector3D expected = new Vector3D(scalar, 0, 0);
        assertEquals(expected, vector3d.mul(scalar), "The mul method should work as expected");
    }

    @Test
    @DisplayName("Vector3D equals")
    void vectorEquals() {
        Vector3D backward = Vector3D.Backward;
        Vector3D forward = Vector3D.Forward;
        Vector3D custom = new Vector3D(0, 0, 1);
        assertEquals(forward, forward, "The equals method should return true if equal");
        assertNotEquals(backward, forward, "The equals method should return false when not equal");
        assertEquals(forward, custom, "The equals method should return true if the values are the same");
    }

    @Test
    @DisplayName("Vector3D forAll")
    void vectorForAll() {
        Vector3D vector3d = new Vector3D(2, 0, 4);

        assertNotEquals(true, vector3d.forAll((c) -> c < 0), "The forAll method should return false");
        assertEquals(true, vector3d.forAll((c) -> c >= 0), "The forAll method should return true");
    }

    @Test
    @DisplayName("Vector3D conversion to DoubleVector3D")
    void vectorConvert() {
        Vector3D vector3d = new Vector3D(1, 2, 3);
        DoubleVector3D converted = vector3d.toDoubleVector3D();
        DoubleVector3D expected = new DoubleVector3D(1, 2, 3);

        assertEquals(expected, converted, "The toDoubleVector3D should work as expected");
    }
    
}
