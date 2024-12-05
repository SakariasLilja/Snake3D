package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntTests {

    @Test
    @DisplayName("Int toString")
    public void intToString() {
        int value = 4;
        Int x = new Int(value);
        assertEquals("" + value, x.toString(), "The toString method should work as expected.");
    }

    @Test
    @DisplayName("Int referencing")
    public void intReference() {
        int value = 6;
        Int x = new Int(value);
        Int reference = x;
        assertSame(x, reference, "Referencing should work as expected");
        x.neg();
        assertSame(x, reference, "Referencing should not be broken when value is updated");
    }
    
}
