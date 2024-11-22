package com.sakariaslilja.models;

import com.sakariaslilja.Constants;
import java.lang.Math;
import java.util.ArrayList;
import java.util.function.Predicate;

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
     * Calculates the vertices of the world shape.
     * Inserts each vertex to their location in the corresponding array.
     * @return 3 arrays:
     * <p>
     *         0: Left to right
     * <p>
     *         1: Top to bottm
     * <p>
     *         2: Front to back
     */
    public Vector3D[][] getVertices() {
        int columns = this.width + (Constants.WORLD_ACCURACY * (this.width - 1));
        int rows = this.height + (Constants.WORLD_ACCURACY * (this.height - 1));
        int layers = this.depth + (Constants.WORLD_ACCURACY * (this.depth - 1));

        Vector3D[][] out = new Vector3D[3][columns*rows*layers];

        for (int layer = 0; layer < layers; layer++) {
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    int indexLR = column + row*columns + layer*columns*rows;
                    int indexTB = row + column*rows + layer*columns*rows;
                    int indexFB = layer + column*layers + row*layers*columns;

                    double x = Math.rint(1.0 * column / (Constants.WORLD_ACCURACY + 1));
                    double y = Math.rint(1.0 * row / (Constants.WORLD_ACCURACY + 1));
                    double z = Math.rint(1.0 * layer / (Constants.WORLD_ACCURACY + 1));

                    Vector3D vector3d = new Vector3D((int) x, (int) y, (int) z);

                    out[0][indexLR] = vector3d;
                    out[1][indexTB] = vector3d;
                    out[2][indexFB] = vector3d;
                }
            }
        }

        return out;
    }

    /**
     * Every edge of the world's walls.
     * An edge is the connection between two vertices.
     * Edges come in the order:
     * <p>
     * - Left to right
     * <p>
     * - Top to bottom
     * <p>
     * - Front to back
     * @return [Tuple] array containing every edge present in the world's walls.
     */
    public ArrayList<Tuple> getEdges() {
        int columns = this.width + (Constants.WORLD_ACCURACY * (this.width - 1));
        int rows = this.height + (Constants.WORLD_ACCURACY * (this.height - 1));
        int layers = this.depth + (Constants.WORLD_ACCURACY * (this.depth - 1));
        int[] dimensions = {columns, rows, layers};

        Vector3D[][] verticesArr = this.getVertices();
        int vertexCount = verticesArr[0].length;

        ArrayList<Tuple> edges = new ArrayList<Tuple>();

        Predicate<Double> isZero = (d) -> d.doubleValue() == 0;

        Predicate<DoubleVector3D> hasZero = (v) -> v.exists(isZero);
        Predicate<DoubleVector3D> hasValidX = (v) -> v.getX() == 0 || v.getX() == width - 1;
        Predicate<DoubleVector3D> hasValidY = (v) -> v.getY() == 0 || v.getY() == height - 1;
        Predicate<DoubleVector3D> hasValidZ = (v) -> v.getZ() == 0 || v.getZ() == depth - 1;

        ArrayList<Predicate<DoubleVector3D>> predicates = new ArrayList<>();
        predicates.add(hasValidX);
        predicates.add(hasValidY);
        predicates.add(hasValidZ);

        for (int arr = 0; arr < verticesArr.length; arr++) {
            ArrayList<Integer> predIndex = new ArrayList<>();
            predIndex.add(0);
            predIndex.add(1);
            predIndex.add(2);
            predIndex.remove(arr);

            for (int i = 1; i < vertexCount; i++) {
                if (i % dimensions[arr] == 0) {
                    continue;
                }
                DoubleVector3D first = verticesArr[arr][i-1].toDoubleVector3D();
                DoubleVector3D second = verticesArr[arr][i].toDoubleVector3D();
    
                Tuple tuple = new Tuple(first, second);
                if (tuple.forAll(hasZero) || tuple.forAll(predicates.get(predIndex.get(0))) || tuple.forAll(predicates.get(predIndex.get(1)))) {
                    edges.add(tuple);
                }
            }
        }
        
        return edges;
    }
    
}
