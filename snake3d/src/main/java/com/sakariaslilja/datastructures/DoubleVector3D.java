package com.sakariaslilja.datastructures;

import java.lang.Math;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.function.Predicate;

/**
 * 3D vector class for double precision.
 * Vectors are considered equal if each of their values are the same.
 */
public class DoubleVector3D {

    private double x;
    private double y;
    private double z;

    /**
     * 3D vector utilising doubles instead of integers.
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param z The z-coordinate
     */
    public DoubleVector3D (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return this.y; }
    public void setY(double y) { this.y = y; }

    public double getZ() { return this.z; }
    public void setZ(double z) { this.z = z; }

    /**
     * Multiplies this DoubleVector3D by a scalar.
     * @param scalar The scalar with which to multiply this vector with
     */
    public void mul(double scalar) {
        x *= scalar; 
        y *= scalar;
        z *= scalar;
    }

    /**
     * Rounds every value of the vector's values to the closest integer.
     */
    public void round() {
        x = Math.round(x);
        y = Math.round(y);
        z = Math.round(z);
    }

    /**
     * Conversion method from this DoubleVector3D to a Vector3D.
     * Round the values of this vector with the {@code round} method.
     * @return Vector3D with this DoubleVector3D's values rounded to the closest integer.
     */
    public Vector3D toVector3D() {
        DoubleVector3D rounded = this.duplicate();
        rounded.round();
        return new Vector3D((int) rounded.getX(), (int) rounded.getY(), (int) rounded.getZ());
    }

    /**
     * Adds the values of another vector to this vector.
     * @param other The vector whose values to add with
     */
    public void add(DoubleVector3D other) {
        x += other.getX();
        y += other.getY();
        z += other.getZ();
    }

    /**
     * Duplicates this vector.
     * @return A new DoubleVector3D with identical values
     */
    public DoubleVector3D duplicate() {
        return new DoubleVector3D(x, y, z);
    }

    /**
     * Negates the values of this vector.
     */
    public void neg() {
        this.mul(-1);
    }

    /**
     * Method for checking if a predicate holds for any of the values
     * @param predicate Predicate to check
     * @return If any of the values of this vector fulfilled the predicate
     */
    public boolean exists(Predicate<Double> predicate) {
        return predicate.test(x) || predicate.test(y) || predicate.test(z);
    }

    /**
     * @return The magnitude of this DoubleVector3D
     */
    public double magnitude() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    /**
     * Normalizes this vector, i.e. the magnitude of this vector is 1.
     */
    public void normalized() {
        this.mul(1.0 / this.magnitude());
    }

    /**
     * Tests a predicate for all of the vector's values.
     * @param predicate The predicate to check with
     * @return If the predicate holds for all values of this vector
     */
    public boolean forAll(Predicate<Double> predicate) {
        return predicate.test(x) && predicate.test(y) && predicate.test(z);
    }

    /**
     * Converts this DoubleVector3D to a Vector3D by casting its
     * values to integers.
     * <p> I.e. applies the floor function to each of the values.
     * @return Vector3D with the floor of the coordinates
     */
    public Vector3D flooredVector3D() {
        return new Vector3D((int) x, (int) y, (int) z);
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
