package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.IConstants;
import com.sakariaslilja.datastructures.DoubleVector3D;
import com.sakariaslilja.datastructures.Tuple;
import com.sakariaslilja.datastructures.Vector3D;

public class WorldTests implements IConstants {

    @Test
    @DisplayName("World with width and height parameters")
    public void setDimensions() {
        int width = 5;
        int height = 7;
        int depth = 18;

        World normalWorld = new World(width, height, depth);

        int expectedWidth = UNIT * width;
        int expectedHeight = UNIT * height;
        int expectedDepth = UNIT * depth;

        assertEquals(expectedWidth, normalWorld.getWidth(), "The getWidth method should return correct value");
        assertEquals(expectedHeight, normalWorld.getHeight(), "The getHeight method should return correct value");
        assertEquals(expectedDepth, normalWorld.getDepth(), "The getDepth method should return correct value");

        World smallWorld = new World(0, 0, 0);
        World largeWorld = new World(2 * MAX_WORLD_SIZE, 2 * MAX_WORLD_SIZE, 2 * MAX_WORLD_SIZE);

        assertEquals(MIN_WORLD_SIZE * UNIT, smallWorld.getHeight(), "The world should not be smaller than allowed");
        assertEquals(MAX_WORLD_SIZE * UNIT, largeWorld.getWidth(), "The world should not be larger than allowed");
    }

    @Test
    @DisplayName("World with size parameter")
    public void setSize() {
        int size = 3;
        World normalWorld = new World(size);
        int expectedHeight = UNIT * size;
        int expectedWidth = expectedHeight;
        int expectedDepth = expectedHeight;
        
        assertEquals(expectedHeight, normalWorld.getHeight(), "The height should be set properly");
        assertEquals(expectedWidth, normalWorld.getWidth(), "The width should be set properly");
        assertEquals(expectedDepth, normalWorld.getDepth(), "The depth should be set properly");

        World smallWorld = new World(0);
        World largeWorld = new World(2* MAX_WORLD_SIZE);

        assertEquals(MIN_WORLD_SIZE * UNIT, smallWorld.getHeight(), "The world should not be too small");
        assertEquals(MAX_WORLD_SIZE * UNIT, largeWorld.getHeight(), "The world should not be too large");
    }

    @Test
    @DisplayName("World vertices")
    public void getVertices() {
        int width = 2;
        int height = 3;
        int depth = 4;
        World world = new World(width, height, depth);

        Vector3D[][] verticesArr = world.getVertices();

        int rowSize = width + 1;
        int columnSize = height + 1;
        int layerSize = depth + 1;

        int expectedSize = rowSize*columnSize*layerSize;

        assertEquals(expectedSize, verticesArr[0].length, "The number of vertices should be correct");

        Vector3D head = new Vector3D(0, 0, 0);
        Vector3D last = new Vector3D(width, height, depth);

        assertEquals(head, verticesArr[0][0], "First element should be located at (0, 0, 0)");
        assertEquals(last, verticesArr[0][expectedSize - 1], "Last element should be correct");

        int x = width - 1;
        int y = height - 1;
        int z = depth - 1;

        Vector3D oddLocation = new Vector3D(x, y, z);
        int expectedIndex = x + y * rowSize + z * rowSize * columnSize;
        
        assertEquals(oddLocation, verticesArr[0][expectedIndex]);
    }

    @Test
    @DisplayName("World edges test")
    public void getEdges() {
        int width = 2;
        int height = 3;
        int depth = 4;

        World world = new World(width, height, depth);

        ArrayList<Tuple> edges = world.getEdges();

        ArrayList<DoubleVector3D> edgeVertices = new ArrayList<DoubleVector3D>();

        for (Tuple edge : edges) {
            DoubleVector3D value1 = edge.value1;
            DoubleVector3D value2 = edge.value2;
            edgeVertices.add(value1);
            edgeVertices.add(value2);
        }

        Vector3D[] verticesArr = world.getVertices()[0];
        ArrayList<Vector3D> validVertices = new ArrayList<Vector3D>();
        ArrayList<Vector3D> invalidVertices = new ArrayList<Vector3D>();
        Integer maxX = 0;
        Integer maxY = 0;
        Integer maxZ = 0;

        for (Vector3D vertex : verticesArr) {
            validVertices.add(vertex);
            invalidVertices.add(vertex);
            maxX = Integer.max(maxX, vertex.getX());
            maxY = Integer.max(maxY, vertex.getY());
            maxZ = Integer.max(maxZ, vertex.getZ());
        }

        final int x = maxX.intValue();
        final int y = maxY.intValue();
        final int z = maxZ.intValue();

        Predicate<Integer> largerThanZero = (c) -> c.intValue() > 0;
        Predicate<Integer> isZero = (c) -> c.intValue() == 0;
        Predicate<Vector3D> isInvalidVector = (v) -> v.forAll(largerThanZero) && v.getX() < x && v.getY() < y && v.getZ() < z;
        Predicate<Vector3D> isValidVector = (v) -> v.exists(isZero) || v.getX() == x || v.getY() == y || v.getZ() == z;

        validVertices.removeIf(isInvalidVector);
        invalidVertices.removeIf(isValidVector);

        boolean edgesAreValid = true;
        boolean edgesContainAllValidVertices = true;

        for (DoubleVector3D vertex : edgeVertices) {
            edgesAreValid = invalidVertices.contains(vertex.toVector3D()) ? false : true;
        }

        for (Vector3D validVertex : validVertices) {
            edgesContainAllValidVertices = !edgeVertices.contains(validVertex.toDoubleVector3D()) ? false : true;
        }

        assertEquals(true, edgesAreValid, "No invalid edges should be present in new edges");
        assertEquals(true, edgesContainAllValidVertices, "All valid vertices should be found in edges");
    }
    
}
