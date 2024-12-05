package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RotatableTupleTests {

    @Test
    @DisplayName("RotatableTuple rotateX")
    public void rotateX() {
        RotatableTuple tuple = new RotatableTuple(new Int(1), new Int(2), new Int(3));
        RotatableTuple expected = new RotatableTuple(new Int(1), new Int(357), new Int(2));
        tuple.rotateX(true);
        assertEquals(expected.getX().value(), tuple.getX().value(), "The rotateX method should work as expected");
        assertEquals(expected.getY().value(), tuple.getY().value(), "The rotateX method should work as expected");
        assertEquals(expected.getZ().value(), tuple.getZ().value(), "The rotateX method should work as expected");
    }

    @Test
    @DisplayName("RotatableTuple rotateY")
    public void rotateY() {
        RotatableTuple tuple = new RotatableTuple(new Int(1), new Int(2), new Int(3));
        RotatableTuple expected = new RotatableTuple(new Int(3), new Int(2), new Int(359));
        tuple.rotateY(true);
        assertEquals(expected.getX().value(), tuple.getX().value(), "The rotateY method should work as expected");
        assertEquals(expected.getY().value(), tuple.getY().value(), "The rotateY method should work as expected");
        assertEquals(expected.getZ().value(), tuple.getZ().value(), "The rotateY method should work as expected");
    }

    @Test
    @DisplayName("RotatableTuple rotateZ")
    public void rotateZ() {
        RotatableTuple tuple = new RotatableTuple(new Int(1), new Int(2), new Int(3));
        RotatableTuple expected = new RotatableTuple(new Int(358), new Int(1), new Int(3));
        tuple.rotateZ(true);
        assertEquals(expected.getX().value(), tuple.getX().value(), "The rotateZ method should work as expected");
        assertEquals(expected.getY().value(), tuple.getY().value(), "The rotateZ method should work as expected");
        assertEquals(expected.getZ().value(), tuple.getZ().value(), "The rotateZ method should work as expected");
    }

    @Test
    @DisplayName("RotatableTuple toString")
    public void rotTupleToString() {
        RotatableTuple tuple = new RotatableTuple(new Int(1), new Int(2), new Int(3));
        String expected = "RT(1, 2, 3)";
        assertEquals(expected, tuple.toString(), "The toString override should work as expected");
    }
    
}
