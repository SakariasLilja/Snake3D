package com.sakariaslilja.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.IConstants;
import com.sakariaslilja.datastructures.Heading;
import com.sakariaslilja.datastructures.Vector3D;

public class SnakeTests implements IConstants {

    @Test
    @DisplayName("Snake move")
    public void snakeMove() {
        Snake snake = new Snake(new Vector3D(0, 0, 501), Heading.FORWARD.direction, Heading.UP.direction);
        Vector3D expected = new Vector3D(0, 0, 501 + STEP_SIZE);
        snake.move();
        
        assertEquals(expected, snake.getPosition(), "The position should update correctly when move is called");
    }

    @Test
    @DisplayName("Snake turn left and right")
    public void snakeTurnHorizontal() {
        Snake snake = new Snake(null, Heading.RIGHT.direction, Heading.DOWN.direction);
        snake.turnRight();
        assertEquals(Heading.FORWARD.direction, snake.getHeading(), "Snake should turn right");
        snake.turnLeft();
        snake.turnLeft();
        assertEquals(Heading.BACKWARD.direction, snake.getHeading(), "Snake should turn left");
    }

    @Test
    @DisplayName("Snake turn up and down")
    public void snakeTurnVertical() {
        Snake snake = new Snake(null, Heading.DOWN.direction, Heading.BACKWARD.direction);
        snake.turnUp();
        assertEquals(Heading.BACKWARD.direction, snake.getHeading(), "The snake should be facing the correct way");
        assertEquals(Heading.UP.direction, snake.getNormal(), "The snake should turn upward");
        snake.turnDown();
        snake.turnDown();
        assertEquals(Heading.FORWARD.direction, snake.getHeading(), "The snake should be facing the correct way");
        assertEquals(Heading.DOWN.direction, snake.getNormal(), "The snake should turn downward");
    }

    @Test
    @DisplayName("Snake apply turn")
    public void applyTurn() {
        Snake snake = new Snake(null, Heading.DOWN.direction, Heading.BACKWARD.direction, Turn.R);
        snake.applyTurn();
        assertEquals(Heading.BACKWARD.direction, snake.getNormal(), "The normal should not change");
        assertEquals(Heading.LEFT.direction, snake.getHeading(), "The apply turn method should work as expected");
    }

    @Test
    @DisplayName("Snake toString")
    public void snakeToString() {
        Snake snake = new Snake(new Vector3D(0, 0, 0), Heading.FORWARD.direction, Heading.UP.direction);

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
        Snake snake = new Snake(new Vector3D(1500, 500, 500), Heading.LEFT.direction, Heading.UP.direction);

        Vector3D expectedGridPos = new Vector3D(1, 0, 0);
        assertEquals(expectedGridPos, snake.getGridPos(), "The gridPos should not move when at center of block");

        snake.setPosition(new Vector3D(1501, 500, 500));
        assertEquals(expectedGridPos, snake.getGridPos(), "The gridPos should not move when before center of block");

        snake.setPosition(new Vector3D(1499, 500, 500));
        expectedGridPos = new Vector3D(0, 0, 0);
        assertEquals(expectedGridPos, snake.getGridPos(), "The gridPos should move when past center of block");

        snake = new Snake(new Vector3D(500, 500, 500), Heading.FORWARD.direction, Heading.UP.direction);
        assertEquals(expectedGridPos, snake.getGridPos(), "The gridPos should not move when at center of block");

        snake.setPosition(new Vector3D(500, 500, 499));
        assertEquals(expectedGridPos, snake.getGridPos(), "The gridPos should not move when before the center of block");

        snake.setPosition(new Vector3D(500, 500, 501));
        expectedGridPos = new Vector3D(0, 0, 1);
        assertEquals(expectedGridPos, snake.getGridPos(), "The gridPos should move when after the center of block");
    }
    
}
