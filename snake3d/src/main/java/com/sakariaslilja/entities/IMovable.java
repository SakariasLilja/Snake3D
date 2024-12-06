package com.sakariaslilja.entities;

import com.sakariaslilja.datastructures.Vector3D;

/**
 * Interface for all movable entities
 */
public interface IMovable {

    /**
     * Getter for the heading of this entity
     * @return The heading of this entity
     */
    public Vector3D getHeading();

    /**
     * Method for moving this entity
     */
    public void move();
    
}
