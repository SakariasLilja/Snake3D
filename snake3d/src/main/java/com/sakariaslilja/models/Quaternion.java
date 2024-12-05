package com.sakariaslilja.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Quaternion.
 * Used for rotations.
 */
public class Quaternion {

    private double w;
    private double x;
    private double y;
    private double z;

    /**
     * A quaternion with all the components.
     * @param w The w component of the quaternion
     * @param x The x component of the quaterion's vector
     * @param y The y component of the quaterion's vector
     * @param z The z component of the quaterion's vector
     */
    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Quaternion from a vector and an angle.
     * @param vector Axis-vector to rotate around, will be normalized
     * @param angle The angle to rotate around this axis
     */
    public Quaternion(DoubleVector3D vector, double angle) {
        this.w = Math.cos(angle * 0.5);
        DoubleVector3D angledVector = vector.normalized().mul(Math.sin(angle * 0.5));
        this.x = angledVector.getX();
        this.y = angledVector.getY();
        this.z = angledVector.getZ();
    }

    /**
     * Quaternion from a single vector.
     * Vector is NOT normalized, and angle is set to 0.
     * @param vector The vector to be turned into quaternion form.
     */
    public Quaternion(DoubleVector3D vector) {
        this.w = 0;
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
    }

    public double getW() { return w; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    /**
     * @return The conjugate quaternion, i.e. all of its vector components are multiplied by -1.
     */
    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    /**
     * Mutliplies this quaternion with another.
     * @param other The other quaternion
     * @return A new Quaternion with the values formed through quaternion multiplication
     */
    public Quaternion mul(Quaternion other) {
        double newW = w * other.getW();
        double newX = - x * other.getX();
        double newY = - y * other.getY();
        double newZ = - z * other.getZ();
        return new Quaternion(newW, newX, newY, newZ);
    }

    /**
     * Extracts the DoubleVector3D from this quaternion
     * @return The extracted vector
     */
    public DoubleVector3D extractVector() {
        return new DoubleVector3D(x, y, z);
    }

    @Override
    public String toString() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#0.000", dfs);
        return "Q(" + df.format(w) + ", " + df.format(x) + ", " + df.format(y) + ", " + df.format(z) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Quaternion other = (Quaternion)obj;
        if (w == other.getW() && x == other.getX() && y == other.getY() && z == other.getZ()) {
            return true;
        }
        
        return false;
    }
    
}
