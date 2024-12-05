package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RotatableTupleTests {

    @Test
    @DisplayName("RotatableTuple rotateX")
    public void rotateX() {
        RotatableTuple tuple = new RotatableTuple(1, 2, 3);
        RotatableTuple expected = new RotatableTuple(1, -3, 2);
        tuple.rotateX(true);
        assertEquals(expected.getX(), tuple.getX(), "The rotateX method should work as expected");
        assertEquals(expected.getY(), tuple.getY(), "The rotateX method should work as expected");
        assertEquals(expected.getZ(), tuple.getZ(), "The rotateX method should work as expected");
    }

    @Test
    @DisplayName("RotatableTuple rotateY")
    public void rotateY() {
        RotatableTuple tuple = new RotatableTuple(1, 2, 3);
        RotatableTuple expected = new RotatableTuple(3, 2, -1);
        tuple.rotateY(true);
        assertEquals(expected.getX(), tuple.getX(), "The rotateY method should work as expected");
        assertEquals(expected.getY(), tuple.getY(), "The rotateY method should work as expected");
        assertEquals(expected.getZ(), tuple.getZ(), "The rotateY method should work as expected");
    }

    @Test
    @DisplayName("RotatableTuple rotateZ")
    public void rotateZ() {
        RotatableTuple tuple = new RotatableTuple(1, 2, 3);
        RotatableTuple expected = new RotatableTuple(-2, 1, 3);
        tuple.rotateZ(true);
        assertEquals(expected.getX(), tuple.getX(), "The rotateX method should work as expected");
        assertEquals(expected.getY(), tuple.getY(), "The rotateX method should work as expected");
        assertEquals(expected.getZ(), tuple.getZ(), "The rotateX method should work as expected");
    }
    
}
