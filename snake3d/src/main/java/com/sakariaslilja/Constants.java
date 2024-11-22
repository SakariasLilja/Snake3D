package com.sakariaslilja;

/**
 * Class for defining global constants.
 */
public class Constants {

    /**
     * The title name of the game.
     */
    public static final String GAME_NAME = "Snake 3D";

    /**
     * The frames per second the game is running and rendered at.
     */
    public static final int FPS = 60;

    /**
     * The display's width in pixels.
     */
    public static final int WIDTH = 640;

    /**
     * The display's height in pixels.
     */
    public static final int HEIGHT = 480;

    /**
     * The focal length of the game's camera.
     */
    public static final double FOCAL_LENGTH = 0.085;

    /**
     * The game camera's sensor size in the x-dimension.
     */
    public static final double SENSOR_SIZE_X = 0.0001 * WIDTH;

    /**
     * The game camera's sensor size in the y-dimension.
     */
    public static final double SENSOR_SIZE_Y = 0.0001 * HEIGHT;

    /**
     * The game's unit size.
     */
    public static final int UNIT = 1000;

    /**
     * The base movement speed of the snake.
     * The amount the snake moves per frame.
     */
    public static final int STEP_SIZE = 1;

    /**
     * The minimum size of any of the world's dimensions.
     */
    public static final int MIN_WORLD_SIZE = 2;

    /**
     * The maximum size of any of the world's dimensions.
     */
    public static final int MAX_WORLD_SIZE = 100;

    /**
     * The number of vertices (exclusize) between each main vertex.
     */
    public static final int WORLD_ACCURACY = 0;
    
}
