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
     * <p>
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
     * <p>
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

    /**
     * Every vertex of the world. 
     * Vertices are in order:
     * <p>
     * Clockwise starting from z=0 (loop through z-coords)
     * @return An array of 3D coordinates
     */
    public Vector3D[] getVerticesClockwise() {
        Vector3D[] out = new Vector3D[100];
        return out;
    }

    /**
     * Every vertex of the world.
     * Vertices are in order:
     * <p>
     * Lenghtwise starting from x=0 (loop through x-coords)
     * @return An array of 3D coordinates
     */
    public Vector3D[] getVerticesLengthwise() {
        Vector3D[] out = new Vector3D[2];
        return out;
    }

    /**
     * Every edge of the world.
     * An edge is the connection between two vertices.
     * Edges come in the order:
     * <p>
     * - Clockwise starting from z=0 (loop through z-coords).
     * <p>
     * - Left to right starting from x=0 (loop through x-coords).
     * @return [Tuple] array.
     */
    public Tuple[] getEdges() {
        Tuple[] out = new Tuple[100];
        return out;
    }
    
}
