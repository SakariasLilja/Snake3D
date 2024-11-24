package com.sakariaslilja.entities;

import java.util.ArrayList;

import com.sakariaslilja.Constants;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.Vector3D;

/**
 * Entity class for all entities.
 */
public abstract class Entity implements Constants {

    private Vector3D position;
    
    /**
     * Entity with a position.
     * To see how much a unit of position is, refer to [Constants].
     * @param position The position of the entity
     */
    public Entity(Vector3D position){
        this.position = position;
    }

    /**
     * Getter for the entity's position
     * @return The position of this entity
     */
    public Vector3D getPosition() {
        return this.position;
    }

    /**
     * Setter for the entity's position
     * @param position The entity's new position
     */
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * Getter for the grid position of the entity.
     * Values are rounded to their closest values exceptions 
     * being for the value 500 which is rounded down, e.g.
     * <p> - 501 => 1
     * <p> - 499 => 0
     * <p> - 500 => 0
     * @return Grid coordinates of this entity
     */
    public Vector3D getGridPos() {
        Vector3D subtractOne = new Vector3D(-1, -1, -1);
        return position.add(subtractOne).toDoubleVector3D().mul(1.0 / UNIT).toVector3D();
    }

    /**
     * Getter for the world vertices of the entity.
     * NOT in base UNIT.
     * @return The world vertices of this entity
     */
    public abstract ArrayList<DoubleVector3D> getVertices();

}
