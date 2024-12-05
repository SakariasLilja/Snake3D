package com.sakariaslilja;

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
    int FPS = 120;

    /**
     * The focal length of the game's camera.
     */
    double FOCAL_LENGTH = 0.1;

    /**
     * The game's unit size.
     */
    int UNIT = 1000;

    /**
     * The base movement speed of the snake.
     * The amount the snake moves per frame.
     */
    int STEP_SIZE = 1;

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
    
}
