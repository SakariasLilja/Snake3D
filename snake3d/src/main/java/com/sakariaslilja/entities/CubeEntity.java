package com.sakariaslilja.entities;

import java.util.ArrayList;

import com.sakariaslilja.Constants;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.Vector3D;

import javafx.scene.paint.Color;

/**
 * Abstract class for all cube entities
 */
public abstract class CubeEntity extends Entity {

    /**
     * A cube entity is an entity whose model is a cube.
     * @param position The world position of the entity
     * @param color The color of the entity
     */
    public CubeEntity(Vector3D position, Color color) {
        super(position, color);
    }

    @Override
    public ArrayList<DoubleVector3D> getVertices() {
        ArrayList<DoubleVector3D> vertices = new ArrayList<>();
        DoubleVector3D center = this.getPosition().toDoubleVector3D().mul(1 / Constants.UNIT);

        for (int zDim = -1; zDim <= 1; zDim += 2) {
            for (int yDim = -1; yDim <= 1; yDim += 2) {
                for (int xDim = -1; xDim <= 1; xDim += 2) {
                    DoubleVector3D offset = new DoubleVector3D(0.5*xDim, 0.5*yDim, 0.5*zDim);
                    DoubleVector3D vertex = center.add(offset);
                    vertices.add(vertex);
                }
            }
        }

        return vertices;
    }
    
}
