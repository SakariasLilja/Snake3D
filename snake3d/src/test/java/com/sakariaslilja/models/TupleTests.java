package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TupleTests {

    @Test
    @DisplayName("Tuple values test")
    void tupleValues() {
        Vector3D value1 = new Vector3D(6,1,2);
        Vector3D value2 = new Vector3D(7, 2, 221);

        Tuple tuple = new Tuple(value1, value2);

        assertEquals(value1, tuple.value1, "First value should be equal");
        assertEquals(value2, tuple.value2, "Second value should be equal");
    }

    @Test
    @DisplayName("Tuple equivalency test")
    void tupleEqual() {
        Vector3D value1 = new Vector3D(126, 16, -27);
        Vector3D value2 = new Vector3D(8, -3, -818);

        Tuple tuple1 = new Tuple(value1, value2);
        Tuple tuple2 = new Tuple(value1, value1);
        Tuple tuple3 = new Tuple(value1, value2);

        assertEquals(tuple1, tuple1, "Same reference should be equal");
        assertNotEquals(tuple1, tuple2, "Different values should not be equal");
        assertEquals(tuple1, tuple3, "Equal values should be equal");
    }

    @Test
    @DisplayName("Updating values should work")
    void tupleUpdate() {
        Vector3D value1 = new Vector3D(28, 3, 182);
        Vector3D value2 = new Vector3D(18, 7327, -1);

        Tuple tuple = new Tuple(value1, value2);
        Tuple expected = new Tuple(value2, value1);

        tuple.value1 = value2;
        tuple.value2 = value1;

        assertEquals(expected, tuple, "Updating values should work");
    }
    
}
