package com.sakariaslilja.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.Constants;

public class WorldTests {

    @Test
    @DisplayName("World with width and height parameters")
    void setDimensions() {
        int width = 5;
        int height = 7;
        int depth = 18;

        World normalWorld = new World(width, height, depth);

        int expectedWidth = Constants.UNIT * width;
        int expectedHeight = Constants.UNIT * height;
        int expectedDepth = Constants.UNIT * depth;

        assertEquals(expectedWidth, normalWorld.getWidth(), "The getWidth method should return correct value");
        assertEquals(expectedHeight, normalWorld.getHeight(), "The getHeight method should return correct value");
        assertEquals(expectedDepth, normalWorld.getDepth(), "The getDepth method should return correct value");

        World smallWorld = new World(0, 0, 0);
        World largeWorld = new World(2 * Constants.MAX_WORLD_SIZE, 2 * Constants.MAX_WORLD_SIZE, 2 * Constants.MAX_WORLD_SIZE);

        assertEquals(Constants.MIN_WORLD_SIZE * Constants.UNIT, smallWorld.getHeight(), "The world should not be smaller than allowed");
        assertEquals(Constants.MAX_WORLD_SIZE * Constants.UNIT, largeWorld.getWidth(), "The world should not be larger than allowed");
    }

    @Test
    @DisplayName("World with size parameter")
    void setSize() {
        int size = 3;
        World normalWorld = new World(size);
        int expectedHeight = Constants.UNIT * size;
        int expectedWidth = expectedHeight;
        int expectedDepth = expectedHeight;
        
        assertEquals(expectedHeight, normalWorld.getHeight(), "The height should be set properly");
        assertEquals(expectedWidth, normalWorld.getWidth(), "The width should be set properly");
        assertEquals(expectedDepth, normalWorld.getDepth(), "The depth should be set properly");

        World smallWorld = new World(0);
        World largeWorld = new World(2* Constants.MAX_WORLD_SIZE);

        assertEquals(Constants.MIN_WORLD_SIZE * Constants.UNIT, smallWorld.getHeight(), "The world should not be too small");
        assertEquals(Constants.MAX_WORLD_SIZE * Constants.UNIT, largeWorld.getHeight(), "The world should not be too large");
    }

    @Test
    @DisplayName("World vertices")
    void getVertices() {
        int width = 2;
        int height = 3;
        int depth = 4;
        World world = new World(width, height, depth);

        Vector3D[][] verticesArr = world.getVertices();

        int rowSize = width + (Constants.WORLD_ACCURACY * (width - 1));
        int columnSize = height + (Constants.WORLD_ACCURACY * (height - 1));
        int layerSize = depth + (Constants.WORLD_ACCURACY * (depth - 1));

        int expectedSize = rowSize*columnSize*layerSize;

        assertEquals(expectedSize, verticesArr[0].length, "The number of vertices should be correct");

        Vector3D head = new Vector3D(0, 0, 0);
        Vector3D last = new Vector3D((width - 1), (height - 1), (depth - 1)).mul(Constants.UNIT);

        assertEquals(head, verticesArr[0][0], "First element should be located at (0, 0, 0)");
        assertEquals(last, verticesArr[0][expectedSize - 1], "Last element should be correct");

        int x = width - 1;
        int y = height - 1;
        int z = depth - 1;

        double weirdX = Math.rint(1.0 * x * Constants.UNIT / (Constants.WORLD_ACCURACY + 1));
        double weirdY = Math.rint(1.0 * y * Constants.UNIT / (Constants.WORLD_ACCURACY + 1));
        double weirdZ = Math.rint(1.0 * z * Constants.UNIT / (Constants.WORLD_ACCURACY + 1));

        Vector3D oddLocation = new Vector3D((int) weirdX, (int) weirdY, (int) weirdZ);
        int expectedIndex = x + y * rowSize + z * rowSize * columnSize;
        
        assertEquals(oddLocation, verticesArr[0][expectedIndex]);
    }

    @Test
    @DisplayName("World edges test")
    void getEdges() {
        int width = 2;
        int height = 3;
        int depth = 4;

        World world = new World(width, height, depth);

        ArrayList<Tuple> edges = world.getEdges();

        HashSet<Vector3D> edgeVertices = new HashSet<Vector3D>();

        for (Tuple edge : edges) {
            Vector3D value1 = edge.value1;
            Vector3D value2 = edge.value2;
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

        Predicate<Integer> largerThanZero = (c) -> c > 0;
        Predicate<Vector3D> isInvalidVector = (v) -> v.forAll(largerThanZero) && v.getX() < x && v.getY() < y && v.getZ() < z;
        Predicate<Vector3D> isValidVector = (v) -> !(v.forAll(largerThanZero) && v.getX() < x && v.getY() < y && v.getZ() < z);

        validVertices.removeIf(isInvalidVector);
        invalidVertices.removeIf(isValidVector);

        boolean edgesAreValid = true;
        boolean edgesContainAllValidVertices = true;

        for (Vector3D vertex : edgeVertices) {
            if (invalidVertices.contains(vertex)) {
                edgesAreValid = false;
            }
            if (!validVertices.contains(vertex)) {
                edgesAreValid = false;
            }
        }

        assertEquals(true, edgesAreValid, "No invalid edges should be present in new edges");
        assertEquals(true, edgesContainAllValidVertices, "All valid vertices should be found in edges");
    }
    
}
