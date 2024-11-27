package com.sakariaslilja.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.models.IHeading;
import com.sakariaslilja.models.Vector3D;

public class SnakeTests implements IHeading {

    @Test
    @DisplayName("Snake move")
    void snakeMove() {
        Snake snake = new Snake(new Vector3D(0, 0, 501), FORWARD, UP);
        Vector3D expected = new Vector3D(0, 0, 502);
        snake.move();
        
        assertEquals(expected, snake.getPosition(), "The position should update correctly when move is called");
    }
    
}
