package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TupleTests {

    @Test
    @DisplayName("Tuple values test")
    void tupleValues() {
        DoubleVector3D value1 = new DoubleVector3D(6,1,2);
        DoubleVector3D value2 = new DoubleVector3D(7, 2, 221);

        Tuple tuple = new Tuple(value1, value2);

        assertEquals(value1, tuple.value1, "First value should be equal");
        assertEquals(value2, tuple.value2, "Second value should be equal");
    }

    @Test
    @DisplayName("Tuple equivalency test")
    void tupleEqual() {
        DoubleVector3D value1 = new DoubleVector3D(126, 16, -27);
        DoubleVector3D value2 = new DoubleVector3D(8, -3, -818);
        DoubleVector3D value2copy = new DoubleVector3D(8, -3, -818);

        Tuple tuple1 = new Tuple(value1, value2);
        Tuple tuple2 = new Tuple(value1, value1);
        Tuple tuple3 = new Tuple(value1, value2copy);

        assertEquals(tuple1, tuple1, "Same reference should be equal");
        assertNotEquals(tuple1, tuple2, "Different values should not be equal");
        assertEquals(tuple1, tuple3, "Equal values should be equal");
    }

    @Test
    @DisplayName("Updating values should work")
    void tupleUpdate() {
        DoubleVector3D value1 = new DoubleVector3D(28, 3, 182);
        DoubleVector3D value2 = new DoubleVector3D(18, 7327, -1);

        Tuple tuple = new Tuple(value1, value2);
        Tuple expected = new Tuple(value2, value1);

        tuple.value1 = value2;
        tuple.value2 = value1;

        assertEquals(expected, tuple, "Updating values should work");
    }

    @Test
    @DisplayName("ForAll function")
    void forAll() {
        DoubleVector3D value1 = new DoubleVector3D(6, 4, 1);
        DoubleVector3D value2 = new DoubleVector3D(6, 2, 55);

        Tuple tuple = new Tuple(value1, value2);

        assertEquals(true, tuple.forAll((v) -> v.getX() > 0), "forAll should return true");
        assertEquals(false, tuple.forAll((v) -> v.getZ() == 0), "forAll should return false");
    }

    @Test
    @DisplayName("ForEach function")
    void forEach() {
        DoubleVector3D value1 = Vector3D.Right.toDoubleVector3D();
        DoubleVector3D value2 = Vector3D.Left.toDoubleVector3D();
        int scalar = 2;

        Tuple tuple = new Tuple(value1, value2);
        
        Tuple expected = new Tuple(value1.mul(scalar), value2.mul(scalar));

        assertNotEquals(expected, tuple, "Should not be equal before forEach is run");

        tuple.forEach((v) -> v.mul(scalar));

        assertEquals(expected, tuple, "forEach should work as expected");
    }

    @Test
    @DisplayName("Tuple toString")
    void tupleToString() {
        DoubleVector3D value1 = Vector3D.Right.toDoubleVector3D();
        DoubleVector3D value2 = Vector3D.Down.toDoubleVector3D();

        Tuple tuple = new Tuple(value1, value2);

        String expected = "[DV(1.000  0.000  0.000) <-> DV(0.000  1.000  0.000)]";

        assertEquals(expected, tuple.toString(), "The toString method should work as expected");
    }

    @Test
    @DisplayName("Tuple contains")
    void tupleContains() {
        DoubleVector3D value1 = Vector3D.Right.toDoubleVector3D();
        DoubleVector3D value2 = Vector3D.Up.toDoubleVector3D();

        Tuple tuple = new Tuple(value1, value2);
        DoubleVector3D check = new DoubleVector3D(1, 0, 0);

        assertEquals(true, tuple.contains(check), "The contains method should return true when Tuple contains Vector3D");
        assertNotEquals(true, tuple.contains(check.mul(-1)), "The contains method should return false when Tuple doesn't contain Vector3D");
    }
    
}
