package com.sakariaslilja.models;

import com.sakariaslilja.Constants;

/**
 * An instance of a world with a set width and height.
 */
public class World {

    // Constants of the world.
    private final int width;
    private final int height;

    /**
     * World constructor with two parameters.
     * Parameters are unmodifiable.
     * 
     * The dimension sizes are validated before assignment
     * to avoid an invalid world size.
     * @param width The width of the world. 
     * @param height The height of the world.
     */
    public World(int width, int height) {
        this.width = validateSize(width);
        this.height = validateSize(height);
    }

    /**
     * World constructor with one parameter.
     * Parameters are unmodifiable.
     * 
     * The size is validated before assignment to
     * avoid an invalid world size.
     * @param size The width and height of the world.
     */
    public World(int size) {
        int validatedSize = validateSize(size);
        this.height = validatedSize;
        this.width = validatedSize;
    }

    /**
     * Validates the size of the parameter to be according to
     * [Constants], i.e. dimensions must be between minimum world
     * size and maximum world size.
     * @param param Dimension to verify
     * @return The validated size
     */
    private int validateSize(int param) {
        if (param < Constants.MIN_WORLD_SIZE) {
            return Constants.MIN_WORLD_SIZE;
        }

        if (param > Constants.MAX_WORLD_SIZE) {
            return Constants.MAX_WORLD_SIZE;
        }

        return param;
    }

    /**
     * The width is calculated with the world width and 
     * the game's unit size.
     * @return The converted world width.
     */
    public int getWidth() {
        return this.width * Constants.UNIT;
    }

    /**
     * The height is calculated with the world height and
     * the game's unit size.
     * @return The converted world height.
     */
    public int getHeight() {
        return this.height * Constants.UNIT;
    }
    
}
