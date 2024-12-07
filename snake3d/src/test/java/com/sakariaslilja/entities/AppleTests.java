package com.sakariaslilja.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.datastructures.DoubleVector3D;
import com.sakariaslilja.datastructures.Vector3D;

public class AppleTests {

    @Test
    @DisplayName("Apple position")
    public void getPosition() {
        Vector3D position = new Vector3D(1, 2, -2);
        Apple apple = new Apple(position);

        assertEquals(position, apple.getPosition(), "The getPosition method should work as expected");
    }

    @Test
    @DisplayName("Apple getGridPos")
    public void getGridPos() {
        Vector3D position = new Vector3D(500, 499, 501);
        Apple apple = new Apple(position);
        Vector3D expected = new Vector3D(0, 0, 1);

        assertEquals(expected, apple.getGridPos(), "The getGridPos should work as expected");
    }

    @Test
    @DisplayName("Apple Vertices")
    public void getVertices() {
        Vector3D position = new Vector3D(500, 500, 500);
        Apple apple = new Apple(position);
        apple.setSize(1);
        ArrayList<DoubleVector3D> vertices = apple.getVertices();

        ArrayList<DoubleVector3D> expected = new ArrayList<>();
        expected.add(new DoubleVector3D(0,0,0));
        expected.add(new DoubleVector3D(1,0,0));
        expected.add(new DoubleVector3D(0,1,0));
        expected.add(new DoubleVector3D(1,1,0));
        expected.add(new DoubleVector3D(0,0,1));
        expected.add(new DoubleVector3D(1,0,1));
        expected.add(new DoubleVector3D(0,1,1));
        expected.add(new DoubleVector3D(1,1,1));

        assertEquals(expected.size(), vertices.size(), "The number of vertices of an apple should be correct");

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), vertices.get(i), "The vertices should be correct");
        }
        
    }
    
}
