package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.datastructures.Heading;
import com.sakariaslilja.datastructures.Vector3D;
import com.sakariaslilja.entities.Snake;
import com.sakariaslilja.entities.Turn;

public class SnakeModelTests {
    
    @Test
    @DisplayName("SnakeModel createSnake")
    public void createSnakeTest() {
        SnakeModel model = new SnakeModel();
        model.x = 700;
        model.y = 100;
        model.z = -128;
        model.heading = Heading.BACKWARD;
        model.normal = Heading.DOWN;
        model.nextTurn = Turn.L;
        Snake snake = model.createSnake();

        Snake expected = new Snake(new Vector3D(700, 100, -128), Heading.BACKWARD, Heading.DOWN, Turn.L);

        assertEquals(expected.getPosition(), snake.getPosition(), "The position should be set correctly");
        assertEquals(expected.getHeading(), snake.getHeading(), "The heading should be set correctly");
        assertEquals(expected.getNormal(), snake.getNormal(), "The normal should be set correctly");
        assertEquals(expected.getTurn(), snake.getTurn(), "The next turn should be set correctly");
    }

}
