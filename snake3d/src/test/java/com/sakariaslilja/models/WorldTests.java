package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.Constants;

public class WorldTests {

    @Test
    @DisplayName("World with width and height parameters")
    void setDimensions() {
        int width = 5;
        int height = 7;
        World normalWorld = new World(width, height);
        int expectedWidth = Constants.UNIT * width;
        int expectedHeight = Constants.UNIT * height;

        assertEquals(expectedWidth, normalWorld.getWidth(), "The getWidth method should return correct value");
        assertEquals(expectedHeight, normalWorld.getHeight(), "The getHeight method should return correct value");

        World smallWorld = new World(0, 0);
        World largeWorld = new World(2 * Constants.MAX_WORLD_SIZE, 2 * Constants.MAX_WORLD_SIZE);

        assertEquals(Constants.MIN_WORLD_SIZE * Constants.UNIT, smallWorld.getHeight(), "The world should not be smaller than allowed");
        assertEquals(Constants.MAX_WORLD_SIZE * Constants.UNIT, largeWorld.getWidth(), "The world should not be larger than allowed");
    }

    @Test
    @DisplayName("World with size parameter")
    void setSize() {
        int size = 3;
        World normalWorld = new World(size);
        int expectedHeight = Constants.UNIT * size;
        int expectedWidth = expectedHeight;
        
        assertEquals(expectedHeight, normalWorld.getHeight(), "The height should be set properly");
        assertEquals(expectedWidth, normalWorld.getWidth(), "The width should be set properly");

        World smallWorld = new World(0);
        World largeWorld = new World(2* Constants.MAX_WORLD_SIZE);

        assertEquals(Constants.MIN_WORLD_SIZE * Constants.UNIT, smallWorld.getHeight(), "The world should not be too small");
        assertEquals(Constants.MAX_WORLD_SIZE * Constants.UNIT, largeWorld.getHeight(), "The world should not be too large");
    }
    
}
