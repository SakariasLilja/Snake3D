package com.sakariaslilja.entities;

import java.util.ArrayList;

import com.sakariaslilja.datastructures.DoubleVector3D;
import com.sakariaslilja.datastructures.Vector3D;

/**
 * Abstract class for all cube entities
 */
public abstract class CubeEntity extends Entity {

    /**
     * A cube entity is an entity whose model is a cube.
     * @param position The world position of the entity
     * @param color The color of the entity
     */
    public CubeEntity(Vector3D position) {
        super(position);
    }

    @Override
    public ArrayList<DoubleVector3D> getVertices() {
        ArrayList<DoubleVector3D> vertices = new ArrayList<>();
        DoubleVector3D topLeft = this.getPosition().add(new Vector3D((int) (-500 * size()), (int) (-500 * size()), (int) (-500 * size()))).toDoubleVector3D();
        topLeft.mul(1.0 / UNIT);

        for (int zDim = 0; zDim <= 1; zDim ++) {
            for (int yDim = 0; yDim <= 1; yDim ++) {
                for (int xDim = 0; xDim <= 1; xDim ++) {
                    DoubleVector3D offset = new DoubleVector3D(xDim * size(), yDim * size(), zDim * size());
                    DoubleVector3D vertex = topLeft.duplicate();
                    vertex.add(offset);
                    vertices.add(vertex);
                }
            }
        }

        return vertices;
    }
    
}
