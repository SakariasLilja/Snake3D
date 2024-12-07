package com.sakariaslilja.datastructures;

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
     * @param angle The angle to rotate around this axis in radians
     */
    public Quaternion(DoubleVector3D vector, double angle) {
        this.w = Math.cos(angle * 0.5);
        DoubleVector3D angledVector = vector.duplicate();
        angledVector.normalized();
        angledVector.mul(Math.sin(angle * 0.5));
        this.x = angledVector.getX();
        this.y = angledVector.getY();
        this.z = angledVector.getZ();
    }

    public double getW() { return w; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    /**
     * The conjugate quaternion, i.e. all of its vector components are multiplied by -1.
     */
    public void conjugate() {
        x = -x;
        y = -y;
        z = -z;
    }

    /**
     * Mutliplies this quaternion with another.
     * @param other The other quaternion whose values to multiply with
     */
    public void mul(Quaternion other) {
        double newW = w*other.getW() - x*other.getX() - y*other.getY() - z*other.getZ();
        double newX = w*other.getX() + x*other.getW() + y*other.getZ() - z*other.getY();
        double newY = w*other.getY() - x*other.getZ() + y*other.getW() + z*other.getX();
        double newZ = w*other.getZ() + x*other.getY() - y*other.getX() + z*other.getW();
        
        w = newW;
        x = newX;
        y = newY;
        z = newZ;
    }

    /**
     * Applies the quaternion rotation to the vector.
     * @param vector The vector to rotate
     */
    public void applyRotation(DoubleVector3D vector) {
        double vectorX = (1-2*y*y-2*z*z)*vector.getX() + (2*x*y-2*w*z)*vector.getY() + (2*x*z+2*w*y)*vector.getZ();
        double vectorY = (2*x*y+2*w*z)*vector.getX() + (1-2*x*x-2*z*z)*vector.getY() + (2*y*z-2*w*x)*vector.getZ();
        double vectorZ = (2*x*z-2*w*y)*vector.getX() + (2*y*z+2*w*x)*vector.getY() + (1-2*x*x-2*y*y)*vector.getZ();

        vector.setX(vectorX);
        vector.setY(vectorY);
        vector.setZ(vectorZ);
    }
    
    protected double magnitude() {
        return Math.sqrt(w*w + x*x + y*y + z*z);
    }

    public void normalize() {
        double reduction = 1 / this.magnitude();
        
        w *= reduction;
        x *= reduction;
        y *= reduction;
        z *= reduction;
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
