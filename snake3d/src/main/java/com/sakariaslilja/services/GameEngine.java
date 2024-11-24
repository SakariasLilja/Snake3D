package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.Random;

import com.sakariaslilja.entities.Apple;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.Tuple;
import com.sakariaslilja.models.World;

/**
 * The game engine.
 */
public class GameEngine {

    // The variables of the engine

    private long seed;
    private Random random;

    private int worldWidth;
    private int worldHeight;
    private int worldDepth;

    private ArrayList<Tuple> edges;
    private ArrayList<Apple> apples = new ArrayList<>();

    private DoubleVector3D camera;

    private double rotX;
    private double rotY;
    private double rotZ;

    /**
     * To create an instance of a game engine, the world dimensions are needed
     * @param seed The random seed of the game
     * @param worldWidth Width of the world
     * @param worldHeight Height of the world
     * @param worldDepth Depth of the world
     */
    public GameEngine(long seed, int worldWidth, int worldHeight, int worldDepth, DoubleVector3D camera, int rotX, int rotY, int rotZ) {
        this.seed = seed;
        this.random = new Random(seed);
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.worldDepth = worldDepth;
        World world = new World(worldWidth, worldHeight, worldDepth);
        this.edges = world.getEdges();
        this.camera = camera;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }

    // Engine getters and setters

    public long getSeed() { return seed; }

    public int getWorldWidth() { return worldWidth; }
    public int getWorldHeight() { return worldHeight; }
    public int getWorldDepth() { return worldDepth; }

    public ArrayList<Tuple> getEdges() { return edges; }

    public ArrayList<Apple> getApples() { return apples; }
    public void setApples(ArrayList<Apple> apples) { this.apples = apples; }

    public DoubleVector3D getCamera() { return camera; }

    public double getRotX() { return rotX; }
    public double getRotY() { return rotY; }   
    public double getRotZ() { return rotZ; }

    /**
     * Update method of the world.
     */
    public void update() {
        rotY += Math.PI / 180;
    }
    
}
