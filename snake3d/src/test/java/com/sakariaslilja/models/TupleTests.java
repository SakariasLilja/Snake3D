package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TupleTests implements IHeading {

    @Test
    @DisplayName("Tuple values test")
    public void tupleValues() {
        DoubleVector3D value1 = new DoubleVector3D(6,1,2);
        DoubleVector3D value2 = new DoubleVector3D(7, 2, 221);

        Tuple tuple = new Tuple(value1, value2);

        assertEquals(value1, tuple.value1, "First value should be equal");
        assertEquals(value2, tuple.value2, "Second value should be equal");
    }

    @Test
    @DisplayName("Tuple equivalency test")
    public void tupleEqual() {
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
    public void tupleUpdate() {
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
    public void forAll() {
        DoubleVector3D value1 = new DoubleVector3D(6, 4, 1);
        DoubleVector3D value2 = new DoubleVector3D(6, 2, 55);

        Tuple tuple = new Tuple(value1, value2);

        assertEquals(true, tuple.forAll((v) -> v.getX() > 0), "forAll should return true");
        assertEquals(false, tuple.forAll((v) -> v.getZ() == 0), "forAll should return false");
    }

    @Test
    @DisplayName("ForEach function")
    public void forEach() {
        DoubleVector3D value1 = RIGHT.toDoubleVector3D();
        DoubleVector3D value2 = LEFT.toDoubleVector3D();
        int scalar = 2;

        Tuple tuple = new Tuple(value1, value2);
        
        Tuple expected = new Tuple(value1.mul(scalar), value2.mul(scalar));

        assertNotEquals(expected, tuple, "Should not be equal before forEach is run");

        tuple.forEach((v) -> v.mul(scalar));

        assertEquals(expected, tuple, "forEach should work as expected");
    }

    @Test
    @DisplayName("Tuple toString")
    public void tupleToString() {
        DoubleVector3D value1 = RIGHT.toDoubleVector3D();
        DoubleVector3D value2 = DOWN.toDoubleVector3D();

        Tuple tuple = new Tuple(value1, value2);

        String expected = "[DV(1.000  0.000  0.000) <-> DV(0.000  1.000  0.000)]";

        assertEquals(expected, tuple.toString(), "The toString method should work as expected");
    }

    @Test
    @DisplayName("Tuple contains")
    public void tupleContains() {
        DoubleVector3D value1 = RIGHT.toDoubleVector3D();
        DoubleVector3D value2 = UP.toDoubleVector3D();

        Tuple tuple = new Tuple(value1, value2);
        DoubleVector3D check = new DoubleVector3D(1, 0, 0);

        assertEquals(true, tuple.contains(check), "The contains method should return true when Tuple contains Vector3D");
        assertNotEquals(true, tuple.contains(check.mul(-1)), "The contains method should return false when Tuple doesn't contain Vector3D");
    }

    @Test
    @DisplayName("Tuple duplicate")
    public void tupleDuplicate() {
        DoubleVector3D value1 = new DoubleVector3D(4, 2, 1);
        DoubleVector3D value2 = new DoubleVector3D(1, 2, 2);

        Tuple tuple = new Tuple(value1, value2);
        Tuple duplicate = tuple.duplicate();

        assertEquals(tuple, duplicate, "The duplicate method should produce a clone");
        assertNotEquals(true, tuple == duplicate, "The duplicate should create a new object reference");

        assertEquals(value1, duplicate.value1, "The first value should be the same in both instances");
        assertEquals(value2, duplicate.value2, "The second value should be the same in both instances");

        assertNotEquals(true, value1 == duplicate.value1, "The references of the values should not be the same");
        assertNotEquals(true, value2 == duplicate.value2, "The references of the values should not be the same");
    }

    @Test
    @DisplayName("Tuple exists")
    public void tupleExists() {
        DoubleVector3D value1 = new DoubleVector3D(1, 2, 2);
        DoubleVector3D value2 = new DoubleVector3D(-1, 1, 1);

        Tuple tuple = new Tuple(value1, value2);

        Predicate<Double> isNegative = (d) -> d.doubleValue() < 0;
        Predicate<Double> isZero = (d) -> d.doubleValue() == 0;

        Predicate<DoubleVector3D> hasNegative = (v) -> v.exists(isNegative);
        Predicate<DoubleVector3D> hasZero = (v) -> v.exists(isZero);

        assertEquals(true, tuple.exists(hasNegative), "The exists method should work");
        assertNotEquals(true, tuple.exists(hasZero), "The exists method should not give incorrect results");
    }
    
}
