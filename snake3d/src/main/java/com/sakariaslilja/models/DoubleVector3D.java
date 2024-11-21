package com.sakariaslilja.models;

import java.lang.Math;

/**
 * 3D vector class for double precision.
 * Vectors are considered equal if each of their values are the same.
 */
public class DoubleVector3D {

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
    
}
