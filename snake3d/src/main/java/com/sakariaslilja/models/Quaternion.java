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
     * @param angle The angle to rotate around this axis in radians
     */
    public Quaternion(DoubleVector3D vector, double angle) {
        this.w = Math.cos(angle * 0.5);
        DoubleVector3D angledVector = vector.normalized().mul(Math.sin(angle * 0.5));
        this.x = angledVector.getX();
        this.y = angledVector.getY();
        this.z = angledVector.getZ();
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
        double newW = w*other.getW() - x*other.getX() - y*other.getY() - z*other.getZ();
        double newX = w*other.getX() + x*other.getW() + y*other.getZ() - z*other.getY();
        double newY = w*other.getY() - x*other.getZ() + y*other.getW() + z*other.getX();
        double newZ = w*other.getZ() + x*other.getY() - y*other.getX() + z*other.getW();
        return new Quaternion(newW, newX, newY, newZ);
    }

    /**
     * Extracts the DoubleVector3D from this quaternion
     * @return The extracted vector
     */
    public DoubleVector3D extractVectorComponent() {
        return new DoubleVector3D(x, y, z);
    }

    /**
     * Applies the quaternion rotation to the vector
     * @param vector The vector to rotate
     * @return The rotated vector
     */
    public DoubleVector3D applyRotation(DoubleVector3D vector) {
        double vectorX = (1-2*y*y-2*z*z)*vector.getX() + (2*x*y-2*w*z)*vector.getY() + (2*x*z+2*w*y)*vector.getZ();
        double vectorY = (2*x*y+2*w*z)*vector.getX() + (1-2*x*x-2*z*z)*vector.getY() + (2*y*z-2*w*x)*vector.getZ();
        double vectorZ = (2*x*z-2*w*y)*vector.getX() + (2*y*z+2*w*x)*vector.getY() + (1-2*x*x-2*y*y)*vector.getZ();
        return new DoubleVector3D(vectorX, vectorY, vectorZ);
    }
    
    protected double magnitude() {
        return Math.sqrt(w*w + x*x + y*y + z*z);
    }

    public Quaternion normalized() {
        double reduction = 1 / this.magnitude();
        return new Quaternion(reduction * w, reduction * x, reduction * y, reduction * z);
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
