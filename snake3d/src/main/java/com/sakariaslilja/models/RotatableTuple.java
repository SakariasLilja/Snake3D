package com.sakariaslilja.models;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Rotatable tuple for storing numbers.
 * Works in place.
 */
public class RotatableTuple implements Iterable<Integer> {

    private ArrayList<Integer> values = new ArrayList<>();

    /**
     * Creates a new rotatable tuple of three values.
     * <p> Rotatable meaning that 3x3 matrix transforms can be applied to them.
     * <p> Works in place.
     * @param x x-value
     * @param y y-value
     * @param z z-value
     */
    public RotatableTuple(Integer x, Integer y, Integer z) {
        values.add(x);
        values.add(y);
        values.add(z);
    }

    public Integer getX() { return values.get(0); }
    public Integer getY() { return values.get(1); }
    public Integer getZ() { return values.get(2); }

    public void setX(int value) { values.set(0, value); }
    public void setY(int value) { values.set(1, value); }
    public void setZ(int value) { values.set(2, value); }

    /**
     * Applies a 90-degree x-rotation matrix to this tuple.
     * Works in place.
     * @param isPositive If the rotation is positive
     * @return The updated object
     */
    public RotatableTuple rotateX(boolean isPositive) {
        Integer amount = isPositive ? 1 : -1;

        Integer newY = values.get(2).intValue() * -amount;
        Integer newZ = values.get(1).intValue() * amount;
        values.set(1, newY);
        values.set(2, newZ);
        return this;
    }

    /**
     * Applies a 90-degree y-rotation matrix to this tuple.
     * Works in place.
     * @param isPositive If the rotation is positive
     * @return The updated object
     */
    public RotatableTuple rotateY(boolean isPositive) {
        int amount = isPositive ? 1 : -1;
        Integer newX = values.get(2).intValue() * amount;
        Integer newZ = values.get(0).intValue() * -amount;
        values.set(0, newX);
        values.set(2, newZ);
        return this;
    }

    /**
     * Applies a 90-degree z-rotation matrix to this tuple.
     * Works in place.
     * @param isPositive If the rotation is positive
     * @return The updated object
     */
    public RotatableTuple rotateZ(boolean isPositive) {
        int amount = isPositive ? 1 : -1;
        Integer newX = values.get(1).intValue() * -amount;
        Integer newY = values.get(0).intValue() * amount;
        values.set(0, newX);
        values.set(1, newY);
        return this;
    }

    // Inherited class for iterating over the elements of this tuple
    @Override
    public Iterator<Integer> iterator() {
        return values.iterator();
    }

    @Override
    public String toString() {
        return "RT(" + values.get(0).intValue() + ", " + values.get(1).intValue() + ", " + values.get(2).intValue() + ")";
    }
    
}
