package com.sakariaslilja.models;

import java.lang.Math;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.function.Predicate;

/**
 * 3D vector class for double precision.
 * Vectors are considered equal if each of their values are the same.
 */
public class DoubleVector3D extends AbstractVector {

    // Private values of this complex vector.
    private final double x;
    private final double y;
    private final double z;

    /**
     * 3D vector utilising doubles instead of integers.
     * Used for specific rotations.
     * @param x The x-coordinate of this [DoubleVector3D].
     * @param y The y-coordinate of this [DoubleVector3D].
     * @param z The z-coordinate of this [DoubleVector3D].
     */
    public DoubleVector3D (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Getter for the x-coordinate.
     * @return The x-coordinate of this [DoubleVector3D]
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getter for the y-coordinate.
     * @return The y-coordinate of this [DoubleVector3D]
     */
    public double getY() {
        return this.y;
    }

    /**
     * Getter for the z-coordinate.
     * @return The z-coordinate of this [DoubleVector3D]
     */
    public double getZ() {
        return this.z;
    }

    /**
     * Inhertied method.
     * Multiplies this DoubleVector3D with a scalar
     * @param scalar The scalar with which to multiply this vector with
     * @return A new DoubleVector3D with scaled values
     */
    public DoubleVector3D mul(double scalar) {
        return new DoubleVector3D(scalar * this.x, scalar * this.y, scalar * this.z);
    }

    /**
     * Function for rotating this [DoubleVector3D] around the x-axis.
     * @param radians Rotation amount in radians
     * @return A new [DoubleVector3D] with the calculated values.
     */
    public DoubleVector3D rotateX(double radians) {
        return new DoubleVector3D(x, y*Math.cos(radians)-z*Math.sin(radians), y*Math.sin(radians)+z*Math.cos(radians));
    }

    /**
     * Function for rotating this [DoubleVector3D] around the y-axis.
     * @param radians Rotation amount in radians
     * @return A new [DoubleVector3D] with the calculated values.
     */
    public DoubleVector3D rotateY(double radians) {
        return new DoubleVector3D(x*Math.cos(radians)+z*Math.sin(radians), y, -x*Math.sin(radians)+z*Math.cos(radians));
    }

    /**
     * Function for rotating this [DoubleVector3D] around the z-axis.
     * @param radians Rotation amount in radians
     * @return A new [DoubleVector3D] with the calculated values.
     */
    public DoubleVector3D rotateZ(double radians) {
        return new DoubleVector3D(x*Math.cos(radians)-y*Math.sin(radians), x*Math.sin(radians)+y*Math.cos(radians), z);
    }

    /**
     * Rounds every value of the vector's values to the closest integer.
     * @return A new [DoubleVector3D] with the rounded values.
     */
    public DoubleVector3D round() {
        return new DoubleVector3D(Math.rint(x), Math.rint(y), Math.rint(z));
    }

    /**
     * Conversion method from this DoubleVector3D to a Vector3D.
     * Round the values of this vector with the {@code round} method.
     * @return Vector3D with this DoubleVector3D's values rounded to the closest integer.
     */
    public Vector3D toVector3D() {
        DoubleVector3D rounded = this.round();
        return new Vector3D((int) rounded.getX(), (int) rounded.getY(), (int) rounded.getZ());
    }

    /**
     * Method for adding two DoubleVector3D to each other
     * @param other The DoubleVector3D to add to this one
     * @return A new DoubleVector3D with the added values
     */
    public DoubleVector3D add(DoubleVector3D other) {
        return new DoubleVector3D(x + other.getX(), y + other.getY(), z + other.getZ());
    }

    /**
     * Duplicates this DoubleVector3D
     * @return A new DoubleVector3D with identical values
     */
    public DoubleVector3D duplicate() {
        return new DoubleVector3D(x, y, z);
    }

    /**
     * Negates the values of this DoubleVector3D
     * @return A negated DoubleVector3D
     */
    public DoubleVector3D neg() {
        return new DoubleVector3D(-x, -y, -z);
    }

    /**
     * Method for checking if a predicate holds for any of the values
     * @param predicate Predicate to check
     * @return If any of the values of this vector fulfilled the predicate
     */
    public boolean exists(Predicate<Double> predicate) {
        return predicate.test(x) || predicate.test(y) || predicate.test(z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        
        DoubleVector3D other = (DoubleVector3D)obj;
        if (x == other.x && y == other.y && z == other.z) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#0.000", dfs);
        return "DV(" + df.format(x) + "  " + df.format(y) + "  " + df.format(z) + ")";
    }
    
}
