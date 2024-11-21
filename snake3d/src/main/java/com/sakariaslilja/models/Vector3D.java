package com.sakariaslilja.models;

import java.util.function.Function;

/**
 * Class representing a 3D vector.
 * Vectors are considered equal if each of their values are the same.
 */
public class Vector3D {

    // Private values of this vector.
    // Cannot be updated upon creation.
    private final int x;
    private final int y;
    private final int z;

    /**
     * 3D vector in space.
     * Values aren't updatable.
     * @param x x-value
     * @param y y-value
     * @param z z-value
     */
    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Static directions used for calculations.
    public static Vector3D Up = new Vector3D(0, -1, 0);
    public static Vector3D Down = new Vector3D(0, 1, 0);
    public static Vector3D Left = new Vector3D(-1, 0, 0);
    public static Vector3D Right = new Vector3D(1, 0, 0);
    public static Vector3D Forward = new Vector3D(0, 0, 1);
    public static Vector3D Backward = new Vector3D(0, 0, -1);

    /**
     * Getter for the x-coordinate.
     * @return The x-coordinate of this [Vector3D]
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-coordinate.
     * @return The y-coordinate of this [Vector3D]
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for the z-coordinate.
     * @return The z-coordinate of this [Vector3D]
     */
    public int getZ() {
        return z;
    }

    /**
     * Function for adding two [Vector3D]-vectors.
     * @param other The other [Vector3D]
     * @return A new [Vector3D] with the resultant values of the addition.
     */
    public Vector3D add(Vector3D other) {
        Vector3D resultantVector = new Vector3D(this.x + other.getX(), this.y + other.getY(), this.z + other.getZ());
        return resultantVector;
    }

    /**
     * Function for negating this vector.
     * @return A new [Vector3D] with the negated values of this vector.
     */
    public Vector3D neg() {
        return new Vector3D(-this.x, -this.y, -this.z);
    }

    /**
     * Function for multiplying this [Vector3D] with a scalar.
     * @param scalar The scalar with which to scale the vector.
     * @return A new [Vector3D] with the scaled values.
     */
    public Vector3D mul(int scalar) {
        return new Vector3D(scalar * this.x, scalar * this.y, scalar * this.z);
    }

    /**
     * Method that takes an anonymous function and applies it to
     * each coordinate of this Vector3D.
     * @param function The anonymous function to apply to each coordinate
     * @return If the function is true for every coordinate
     */
    public boolean forAll(Function<Integer, Boolean> function) {
        return function.apply(x) && function.apply(y) && function.apply(z);
    }

    /**
     * Converts this Vector3D to a DoubleVector3D.
     * Has the same values.
     */
    public DoubleVector3D toDoubleVector3D() {
        return new DoubleVector3D(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3D(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        final Vector3D other = (Vector3D) obj;
        if (x == other.x && y == other.y && z == other.z) {
            return true;
        }
        
        return false;
    }
    
}
