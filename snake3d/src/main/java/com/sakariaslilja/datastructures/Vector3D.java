package com.sakariaslilja.datastructures;

import java.util.function.Predicate;

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
     * The scalar is rounded to the nearest integer value.
     * @param scalar The scalar with which to scale the vector.
     * @return A new [Vector3D] with the scaled values.
     */
    public Vector3D mul(double scalar) {
        int scalarInt = (int) Math.rint(scalar);
        return new Vector3D(scalarInt * this.x, scalarInt * this.y, scalarInt * this.z);
    }

    /**
     * Method that takes a predicate and applies it to
     * each coordinate of this Vector3D.
     * @param predicate The predicate to apply to each coordinate
     * @return If the function is true for every coordinate
     */
    public boolean forAll(Predicate<Integer> predicate) {
        return predicate.test(x) && predicate.test(y) && predicate.test(z);
    }

    /**
     * Converts this Vector3D to a DoubleVector3D.
     * Has the same values.
     */
    public DoubleVector3D toDoubleVector3D() {
        return new DoubleVector3D(x, y, z);
    }

    /**
     * Method that checks if a predicate holds for any of the Vector3D's values
     * @param predicate Predicate to check
     * @return If the predicate held for any of the values
     */
    public boolean exists(Predicate<Integer> predicate) {
        return predicate.test(x) || predicate.test(y) || predicate.test(z);
    }

    /**
     * Returns the cross product of this and another vector.
     * <p> The product order is: this x that
     * @param other The vector to perform the cross product with
     * @return The cross product vector
     */
    public Vector3D crossProd(Vector3D other) {
        int resX = y * other.getZ() - z * other.getY();
        int rexY = z * other.getX() - x * other.getZ();
        int rexZ = x * other.getY() - y * other.getX();
        return new Vector3D(resX, rexY, rexZ);
    }

    /**
     * Simple 90 degree rotation matrix.
     * <p> Rotates 90 degrees either positively or negatively.
     * @param isPositive If the rotation value is positive
     * @return The rotated matrix
     */
    public Vector3D rotateX(boolean isPositive) {
        int amount = isPositive ? 1 : -1;
        return new Vector3D(x, -z * amount, y * amount);
    }

    /**
     * Simple 90 degree rotation matrix.
     * <p> Rotates 90 degrees either positively or negatively.
     * @param isPositive If the rotation value is positive
     * @return The rotated matrix
     */
    public Vector3D rotateY(boolean isPositive) {
        int amount = isPositive ? 1 : -1;
        return new Vector3D(z * amount, y, -x * amount);
    }

    /**
     * Simple 90 degree rotation matrix.
     * <p> Rotates 90 degrees either positively or negatively.
     * @param isPositive If the rotation value is positive
     * @return The rotated matrix
     */
    public Vector3D rotateZ(boolean isPositive) {
        int amount = isPositive ? 1 : -1;
        return new Vector3D(-y * amount, x * amount, z);
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
