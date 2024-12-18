package com.sakariaslilja.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.IConstants;
import com.sakariaslilja.datastructures.Heading;
import com.sakariaslilja.datastructures.Vector3D;
import com.sakariaslilja.entities.Apple;
import com.sakariaslilja.entities.Snake;
import com.sakariaslilja.entities.Turn;
import com.sakariaslilja.models.GameModel;

public class GameEngineTests implements IConstants {

    @Test
    @DisplayName("GameEngine incrementScore")
    public void incrementScore() {
        GameEngine engine = new GameEngine(new GameModel());
        int initialScore = engine.getScore();
        engine.incrementScore();
        int newScore = initialScore + 1;
        assertEquals(newScore, engine.getScore(), "The incrementScore method should work as expected");
    }

    @Test
    @DisplayName("GameEngine toGameModel")
    public void toGameModel() {
        GameModel model = new GameModel();
        GameEngine engine = new GameEngine(model);

        engine.incrementScore();
        GameModel createdModel = engine.toGameModel();

        assertEquals(createdModel.seed, model.seed, "The toGameModel should work as expected");
        assertNotEquals(model.score, createdModel.score, "The score should be different after incrementation");
    }

    @Test
    @DisplayName("GameEngine getAvailableGridPositions")
    public void getAvailableGridPositions() {
        GameEngine engine = new GameEngine(new GameModel());
        Vector3D position = new Vector3D(0, 0, 0);

        ArrayList<Vector3D> apples = new ArrayList<>();
        ArrayList<Vector3D> allPositions = engine.getAvailableGridPositions(apples);

        Apple apple = new Apple(position);
        apples.add(apple.getGridPos());
        ArrayList<Vector3D> appleOccupied = engine.getAvailableGridPositions(apples);

        assertNotEquals(allPositions.size(), appleOccupied.size(), "Passing elements to remove should remove said elements");
        int index = allPositions.indexOf(position);
        assertNotEquals(-1, index, "The position should exist in the original array");
        assertNotEquals(allPositions.get(index), appleOccupied.get(index), "The position at the deleted index should not be the same");
    }

    @Test
    @DisplayName("GameEngine apple count")
    public void appleCount() {
        GameEngine engine = new GameEngine(new GameModel());

        assertEquals(0, engine.countApples(), "The number of apples should be 0 if not set to anything");

        ArrayList<Apple> apples = new ArrayList<>();
        int expectedAppleCount = 3;
        for (int i = 0; i < expectedAppleCount; i ++) {
            Apple apple = new Apple(new Vector3D(i, i, i));
            apples.add(apple);
        }
        engine.setApples(apples);

        assertEquals(expectedAppleCount, engine.countApples(), "The number of apples should be correct");
    }

    @Test
    @DisplayName("GameEngine increaseScore")
    public void increaseScore() {
        GameEngine engine = new GameEngine(new GameModel());
        int oldScore = engine.getScore();
        int increaseAmount = 5;
        engine.increaseScore(increaseAmount);

        assertEquals(oldScore + increaseAmount, engine.getScore(), "The score should be increased by the correct amount");
    }

    @Test
    @DisplayName("GameEngine gridPositions")
    public void gridPositions() {
        int width = 3;
        int height = 4;
        int depth = 7;

        GameModel model = new GameModel();
        model.worldWidth = width;
        model.worldHeight = height;
        model.worldDepth = depth;
        GameEngine engine = new GameEngine(model);

        int expectedSize = width * height * depth;

        assertEquals(expectedSize, engine.gridPositionCount(), "The number of grid positions should be correct");
    }

    @Test
    @DisplayName("GameEngine spawnApple")
    public void spawnApple() {
        GameModel model = new GameModel();
        GameEngine engine = new GameEngine(model);
        engine.setSnake(new ArrayList<Snake>());
        int oldAppleCount = engine.countApples();

        Apple apple = new Apple(new Vector3D(0,0,0));
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(apple);
        engine.setApples(apples);

        int appleLimit = 3;
        engine.spawnApple(appleLimit);

        assertEquals(oldAppleCount + 2, engine.countApples(), "The number of apples should be correct");

        for (int i = 0; i < 10; i++) {
            engine.spawnApple(appleLimit);
        }

        assertEquals(appleLimit, engine.countApples(), "The number of apples should not exceed the limit");

        int worldSize = model.worldDepth * model.worldHeight * model.worldWidth;
        int largeNumber = 2 * worldSize;

        for (int i = 0; i < largeNumber; i++) {
            engine.spawnApple(largeNumber);
        }

        assertEquals(worldSize, engine.countApples(), "The number of apples should not exceed world size");
    }

    @Test
    @DisplayName("GameEngine pause")
    public void pauseGame() {
        GameEngine engine = new GameEngine(new GameModel());

        int appleCount = engine.countApples();

        engine.togglePause();
        engine.update();

        assertEquals(appleCount, engine.countApples(), "Updating the game shouldn't spawn new apples if the game is paused");
    }

    @Test
    @DisplayName("GameEngine setSnake")
    public void setSnake() {
        GameEngine engine = new GameEngine(new GameModel());

        Vector3D snakePos = new Vector3D(0, 4, 5).mul(UNIT / 2);
        Snake snakeHead = new Snake(snakePos, Heading.FORWARD, Heading.UP);

        ArrayList<Snake> snake = new ArrayList<>();
        snake.add(snakeHead);

        engine.setSnake(snake);
        
        assertEquals(snakeHead, engine.getSnake().get(0));
    }

    @Test
    @DisplayName("GameEngine grow snake")
    public void growSnake() {
        GameEngine engine = new GameEngine(new GameModel());

        Vector3D snakePos = new Vector3D(0, 1, 2).mul(UNIT / 2).add(new Vector3D(500, 500, 500));
        Snake snakeHead = new Snake(snakePos, Heading.FORWARD, Heading.UP);

        ArrayList<Snake> snake = new ArrayList<>();
        snake.add(snakeHead);

        engine.setSnake(snake);
        engine.growSnake();

        ArrayList<Snake> gameSnake = engine.getSnake();

        Vector3D expectedPos = gameSnake.get(0).getGridPos().add(new Vector3D(0, 0, -1));

        assertEquals(expectedPos, gameSnake.get(gameSnake.size() - 1).getGridPos(), "The growSnake method should work as expected");
    }

    @Test
    @DisplayName("GameEngine applyTurns")
    public void applyTurns() {
        GameEngine engine = new GameEngine(new GameModel());

        Vector3D snakePos = new Vector3D(0, 1, 2).mul(UNIT / 2).add(new Vector3D(500, 500, 500));
        Snake snakeHead = new Snake(snakePos, Heading.FORWARD, Heading.UP);

        ArrayList<Snake> snake = new ArrayList<>();
        snake.add(snakeHead);

        engine.setSnake(snake);
        engine.growSnake();

        ArrayList<Snake> gameSnake = engine.getSnake();

        for (Snake s : gameSnake) { s.setTurn(Turn.D); }

        engine.applyTurns();

        gameSnake = engine.getSnake();
        for (Snake s : gameSnake) {
            assertEquals(Heading.DOWN.vec, s.getHeading(),"The turns should be applied correctly");
        }
    }

    @Test
    @DisplayName("GameEngine propagateTurns")
    public void propagateTurns() {
        GameEngine engine = new GameEngine(new GameModel());

        Vector3D snakePos = new Vector3D(0, 1, 2).mul(UNIT / 2).add(new Vector3D(500, 500, 500));
        Snake snakeHead = new Snake(snakePos, Heading.FORWARD, Heading.UP);
        
        ArrayList<Snake> snake = new ArrayList<>();
        snake.add(snakeHead);

        engine.setSnake(snake);
        engine.growSnake();
        snakeHead.setTurn(Turn.D);
        engine.propagateTurns();

        ArrayList<Snake> gameSnake = engine.getSnake();

        assertEquals(Turn.N, gameSnake.get(0).getTurn(), "The head's next move should be reset");
        assertEquals(Turn.D, gameSnake.get(1).getTurn(), "The next turn should have propagated to the next one");
    }

    @Test
    @DisplayName("GameEngine snake collisions")
    public void snakeCollisions() {
        GameEngine engine = new GameEngine(new GameModel());

        assertFalse(engine.checkSnakeCollisions(), "No collision should occur at start");

        Vector3D snakePos = new Vector3D(0, 1, 2).mul(UNIT / 2).add(new Vector3D(500, 500, 500));
        Snake snakeHead = new Snake(snakePos, Heading.FORWARD, Heading.UP);

        ArrayList<Snake> snake = new ArrayList<>();
        snake.add(snakeHead);
        snake.add(snakeHead);

        engine.setSnake(snake);

        assertTrue(engine.checkSnakeCollisions(), "The collision should be detected");

        snakeHead.setPosition(new Vector3D(-2, 0, 0).mul(UNIT / 2).add(new Vector3D(500, 500, 500)));
        snake.clear();
        snake.add(snakeHead);

        assertTrue(engine.checkSnakeCollisions(), "Player should not be allowed out of bounds");
    }

    @Test
    @DisplayName("GameEngine apple collisions")
    public void appleCollisions() {
        GameEngine engine = new GameEngine(new GameModel());

        Vector3D pos = new Vector3D(0, 1, 2).mul(UNIT / 2).add(new Vector3D(500, 500, 500));
        Snake snakeHead = new Snake(pos, Heading.FORWARD, Heading.UP);
        Apple apple = new Apple(pos.add(new Vector3D(0, 0, 0)));

        ArrayList<Snake> snake = new ArrayList<>();
        ArrayList<Apple> apples = new ArrayList<>();

        snake.add(snakeHead);
        apples.add(apple);

        engine.setSnake(snake);
        engine.setApples(apples);

        snake = engine.getSnake();

        assertEquals(1, engine.countApples(), "There should be an apple present to start");
        assertEquals(1, snake.size(), "There should be a snake of length one at start");
        assertTrue(engine.checkAppleCollisions(), "The apple should be collided with");
        assertEquals(0, engine.countApples(), "The apple should be removed when eaten");
        assertEquals(1, engine.getScore(), "The score should increment properly");
        assertEquals(2, snake.size(), "The snake should've grown");
    }

    @Test
    @DisplayName("GameEngine toGameModel snakes")
    public void toGameModelSnakesTest() {
        GameEngine game = new GameEngine(new GameModel());
        int oldSize = game.getSnake().size();
        game.growSnake();
        GameModel gameModel = game.toGameModel();
        assertEquals(oldSize + 1, gameModel.snake.length, "The growth should be present in the new game model");
    }
    
}
