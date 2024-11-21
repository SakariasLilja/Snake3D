package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.Constants;

public class WorldTests {

    @Test
    @DisplayName("World dimensions")
    void getWidth() {
        int width = 5;
        int height = 7;
        World normalWorld = new World(width, height);
        int expectedWidth = Constants.UNIT * width;
        int expectedHeight = Constants.UNIT * height;

        assertEquals(expectedWidth, normalWorld.getWidth(), "The getWidth method should return correct value");
        assertEquals(expectedHeight, normalWorld.getHeight(), "The getHeight method should return correct value");

        World smallWorld = new World(0, 0);

        assertEquals(Constants.MIN_WORLD_SIZE * Constants.UNIT, smallWorld.getHeight(), "The world should not be smaller than allowed");

        World largeWorld = new World(2 * Constants.MAX_WORLD_SIZE, 2 * Constants.MAX_WORLD_SIZE);

        assertEquals(Constants.MAX_WORLD_SIZE * Constants.UNIT, largeWorld.getWidth(), "The world should not be larger than allowed");
    }
    
}
