package com.sakariaslilja.entities;

import java.util.ArrayList;

import com.sakariaslilja.IConstants;
import com.sakariaslilja.datastructures.DoubleVector3D;
import com.sakariaslilja.datastructures.Vector3D;

/**
 * Entity class for all entities.
 */
public abstract class Entity implements IConstants {

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
     * <p>
     * For movable entities, this value is determined with the
     * heading of the entity.
     * @return Grid coordinates of this entity
     */
    public Vector3D getGridPos() {
        Vector3D subtractOne = new Vector3D(-1, -1, -1);
        DoubleVector3D p1 = position.add(subtractOne).toDoubleVector3D();
        p1.mul(1.0 / UNIT);
        return p1.toVector3D();
    }

    /**
     * Getter for the world vertices of the entity.
     * NOT in base UNIT.
     * @return The world vertices of this entity
     */
    public abstract ArrayList<DoubleVector3D> getVertices();

    /**
     * Default size of an entity is one.
     * @return The size of the entity
     */
    protected double size() { return 1; }

}
