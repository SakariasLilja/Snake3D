package com.sakariaslilja;

import java.util.concurrent.TimeUnit;

/**
 * Class for defining global constants.
 */
public interface IConstants {

    /**
     * The title name of the game.
     */
    String GAME_NAME = "Snake 3D";

    /**
     * The frames per second the game is running and rendered at.
     */
    long FPS = TimeUnit.SECONDS.toNanos(1L) / 120;

    /**
     * The focal length of the game's camera.
     */
    double FOCAL_LENGTH = 0.12;

    /**
     * The game's unit size.
     */
    int UNIT = 1000;

    /**
     * The base movement speed of the snake.
     * The amount the snake moves per frame.
     */
    int STEP_SIZE = 25;

    /**
     * The minimum size of any of the world's dimensions.
     */
    int MIN_WORLD_SIZE = 2;

    /**
     * The maximum size of any of the world's dimensions.
     */
    int MAX_WORLD_SIZE = 100;

    /**
     * One degree in radians.
     */
    double ONE_DEG = Math.PI / 180.0;

    /**
     * Mulitplier for how fast the game rotates when turning.
     * 90 MUST be divisible by this value.
     */
    int ROTATION_SPEED = 2;
    
}
