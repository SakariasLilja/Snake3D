package com.sakariaslilja.entities;

import java.util.ArrayList;

import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.Vector3D;

import javafx.scene.paint.Color;

/**
 * Entity class for all entities.
 */
public abstract class Entity {

    private Vector3D position;
    private Color color;
    
    /**
     * Entity with a position.
     * To see how much a unit of position is, refer to [Constants].
     * @param position The position of the entity
     */
    public Entity(Vector3D position, Color color){
        this.position = position;
        this.color = color;
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
     * Getter for the color of this entity.
     * @return The color of the entity
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for the world vertices of the entity.
     * NOT in base UNIT.
     * @return The world vertices of this entity
     */
    public abstract ArrayList<DoubleVector3D> getVertices();

}
