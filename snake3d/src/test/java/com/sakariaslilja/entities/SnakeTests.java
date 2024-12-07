package com.sakariaslilja.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.IConstants;
import com.sakariaslilja.datastructures.IHeading;
import com.sakariaslilja.datastructures.Vector3D;

public class SnakeTests implements IHeading, IConstants {

    @Test
    @DisplayName("Snake move")
    public void snakeMove() {
        Snake snake = new Snake(new Vector3D(0, 0, 501), FORWARD, UP);
        Vector3D expected = new Vector3D(0, 0, 501 + STEP_SIZE);
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

    @Test
    @DisplayName("Snake toString")
    public void snakeToString() {
        Snake snake = new Snake(new Vector3D(0, 0, 0), FORWARD, UP);

        String part1 = "Position: Vector3D(0, 0, 0)\n";
        String part2 = "Heading: Vector3D(0, 0, 1)\n";
        String part3 = "Normal: Vector3D(0, -1, 0)\n";
        String part4 = "GridPos: Vector3D(0, 0, 0)\n";
        String part5 = "Next turn: N\n";

        StringBuilder sb = new StringBuilder();
        sb.append(part1);
        sb.append(part2);
        sb.append(part3);
        sb.append(part4);
        sb.append(part5);

        assertEquals(sb.toString(), snake.toString(), "The toString method should work as expected");
    }

    @Test
    @DisplayName("Snake getGridPos")
    public void getGridPos() {
        Snake snake = new Snake(new Vector3D(1500, 500, 500), LEFT, UP);
        Vector3D expectedGridPos1 = new Vector3D(1, 0, 0);
        
        assertEquals(expectedGridPos1, snake.getGridPos());

        snake.setPosition(new Vector3D(1499, 500, 500));

        Vector3D expectedGridPos2 = new Vector3D(0, 0, 0);

        assertEquals(expectedGridPos2, snake.getGridPos());
    }
    
}
