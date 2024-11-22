package com.sakariaslilja.services;

import java.util.ArrayList;

import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.Tuple;
import com.sakariaslilja.models.World;

/**
 * The game engine.
 */
public class GameEngine {

    // The variables of the engine
    private int seed;
    private int worldWidth;
    private int worldHeight;
    private int worldDepth;
    private ArrayList<Tuple> edges;
    private DoubleVector3D camera;
    private DoubleVector3D cameraNormal;

    /**
     * To create an instance of a game engine, the world dimensions are needed
     * @param seed The random seed of the game
     * @param worldWidth Width of the world
     * @param worldHeight Height of the world
     * @param worldDepth Depth of the world
     */
    public GameEngine(int seed, int worldWidth, int worldHeight, int worldDepth, DoubleVector3D camera, DoubleVector3D cameraNormal) {
        this.seed = seed;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.worldDepth = worldDepth;
        World world = new World(worldWidth, worldHeight, worldDepth);
        this.edges = world.getEdges();
        this.camera = camera;
        this.cameraNormal = cameraNormal;
    }

    /**
     * Returns the edges associated with this world.
     * @return The edges of this world.
     */
    public ArrayList<Tuple> getEdges() {
        return edges;
    }

    /**
     * Getter for seed attribute
     * @return The seed of the game
     */
    public int getSeed() {
        return seed;
    }

    /**
     * Getter for the world's width
     * @return The width of the world
     */
    public int getWorldWidth() {
        return worldWidth;
    }

    /**
     * Getter for the world's height
     * @return The height of the world
     */
    public int getWorldHeight() {
        return worldHeight;
    }

    /**
     * Getter for the world's depth
     * @return The depth of the world
     */
    public int getWorldDepth() {
        return worldDepth;
    }

    /**
     * Getter for the game's camera
     * @return The game's camera
     */
    public DoubleVector3D getCamera() {
        return camera;
    }

    /**
     * Getter for the game camera's normal vector
     * @return The normal of the camera
     */
    public DoubleVector3D getCameraNormal() {
        return cameraNormal;
    }

    /**
     * Update method of the world.
     */
    public void update() {
    }
    
}
