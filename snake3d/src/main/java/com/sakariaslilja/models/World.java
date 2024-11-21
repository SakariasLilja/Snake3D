package com.sakariaslilja.models;

import com.sakariaslilja.Constants;
import java.lang.Math;

/**
 * An instance of a world with a set width, height and depth.
 */
public class World {

    // Constants of the world.
    private final int width;
    private final int height;
    private final int depth;

    /**
     * World constructor with two parameters.
     * Parameters are unmodifiable.
     * <p>
     * The dimension sizes are validated before assignment
     * to avoid an invalid world size.
     * @param width The width of the world. 
     * @param height The height of the world.
     * @param depth The depth of the world.
     */
    public World(int width, int height, int depth) {
        this.width = validateSize(width);
        this.height = validateSize(height);
        this.depth = validateSize(depth);
    }

    /**
     * World constructor with one parameter.
     * Parameters are unmodifiable.
     * <p>
     * The size is validated before assignment to
     * avoid an invalid world size.
     * @param size The width, height and depth of the world.
     */
    public World(int size) {
        int validatedSize = validateSize(size);
        this.height = validatedSize;
        this.width = validatedSize;
        this.depth = validatedSize;
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
     * The depth is calculated with the world height and
     * the game's unit size.
     * @return The converted world depth.
     */
    public int getDepth() {
        return this.depth * Constants.UNIT;
    }

    /**
     * Every vertex of the world. 
     * Vertices are in order:
     * <p>
     * Clockwise starting from z=0 (loop through z-coords)
     * @return An array of 3D coordinates
     */
    public Vector3D[] getVerticesClockwise() {
        int columns = this.width + (Constants.WORLD_ACCURACY * (this.width - 1));
        int rows = this.height + (Constants.WORLD_ACCURACY * (this.height - 1));
        int layers = this.depth + (Constants.WORLD_ACCURACY * (this.depth - 1));

        Vector3D[] out = new Vector3D[columns*rows*layers];

        for (int k = 0; k < layers; k++) {
            for (int j = 0; j < rows; j++) {
                for (int i = 0; i < columns; i++) {
                    int index = i + j*columns + k*columns*rows;

                    double x = Math.rint(1.0 * i * Constants.UNIT / (Constants.WORLD_ACCURACY + 1));
                    double y = Math.rint(1.0 * j * Constants.UNIT / (Constants.WORLD_ACCURACY + 1));
                    double z = Math.rint(1.0 * k * Constants.UNIT / (Constants.WORLD_ACCURACY + 1));

                    out[index] = new Vector3D((int) x, (int) y, (int) z);
                }
            }
        }

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
        // TODO: implement similarly to getVerticesClockwise
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
        // TODO: implement w/ both getVertices-methods
        return out;
    }
    
}
