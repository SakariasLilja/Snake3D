package com.sakariaslilja.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.datastructures.IHeading;
import com.sakariaslilja.datastructures.Vector3D;

public class SnakeTests implements IHeading {

    @Test
    @DisplayName("Snake move")
    public void snakeMove() {
        Snake snake = new Snake(new Vector3D(0, 0, 501), FORWARD, UP);
        Vector3D expected = new Vector3D(0, 0, 502);
        snake.move();
        
        assertEquals(expected, snake.getPosition(), "The position should update correctly when move is called");
    }

    @Test
    @DisplayName("Snake turn left and right")
    public void snakeTurnHorizontal() {
        Snake snake = new Snake(null, RIGHT, DOWN);
        snake.turnRight();
        assertEquals(FORWARD, snake.getHeading(), "Snake should turn right");
        snake.turnLeft();
        snake.turnLeft();
        assertEquals(BACKWARD, snake.getHeading(), "Snake should turn left");
    }

    @Test
    @DisplayName("Snake turn up and down")
    public void snakeTurnVertical() {
        Snake snake = new Snake(null, DOWN, BACKWARD);
        snake.turnUp();
        assertEquals(BACKWARD, snake.getHeading(), "The snake should be facing the correct way");
        assertEquals(UP, snake.getNormal(), "The snake should turn upward");
        snake.turnDown();
        snake.turnDown();
        assertEquals(FORWARD, snake.getHeading(), "The snake should be facing the correct way");
        assertEquals(DOWN, snake.getNormal(), "The snake should turn downward");
    }

    @Test
    @DisplayName("Snake apply turn")
    public void applyTurn() {
        Snake snake = new Snake(null, DOWN, BACKWARD, Turn.R);
        snake.applyTurn();
        assertEquals(BACKWARD, snake.getNormal(), "The normal should not change");
        assertEquals(LEFT, snake.getHeading(), "The apply turn method should work as expected");
    }
    
}
