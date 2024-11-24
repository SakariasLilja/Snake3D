package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import com.sakariaslilja.entities.Apple;
import com.sakariaslilja.entities.Entity;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.GameModel;
import com.sakariaslilja.models.Tuple;
import com.sakariaslilja.models.Vector3D;
import com.sakariaslilja.models.World;

/**
 * The game engine.
 * The rotation values are in degrees.
 */
public class GameEngine {

    // The variables of the engine

    private String gameTitle;
    private long seed;
    private Random random;
    private Date creationDate;
    private int score;

    private int worldWidth, worldHeight, worldDepth;

    private ArrayList<Tuple> edges;
    private ArrayList<Vector3D> gridPositions = new ArrayList<>();
    private ArrayList<Apple> apples = new ArrayList<>();

    private DoubleVector3D camera;

    // In degrees
    private int rotX, rotY, rotZ;

    /**
     * To create an instance of a game engine, the world dimensions are needed
     * @param seed The random seed of the game
     * @param worldWidth Width of the world
     * @param worldHeight Height of the world
     * @param worldDepth Depth of the world
     */
    public GameEngine(GameModel game, DoubleVector3D camera) {
        this.gameTitle = game.gameTitle;
        this.seed = game.seed;
        this.random = new Random(seed);
        this.creationDate = game.creationDate;
        this.score = game.score;
        this.worldWidth = game.worldWidth;
        this.worldHeight = game.worldHeight;
        this.worldDepth = game.worldDepth;
        World world = new World(worldWidth, worldHeight, worldDepth);
        this.edges = world.getEdges();
        Collections.addAll(gridPositions, world.getVertices()[0]);
        this.camera = camera;
        this.rotX = game.rotX;
        this.rotY = game.rotY;
        this.rotZ = game.rotZ;
    }

    // Engine getters and setters

    public int getScore() { return score; }

    public void incrementScore() { score++; }

    public ArrayList<Tuple> getEdges() { return edges; }

    public ArrayList<Apple> getApples() { return apples; }
    public void setApples(ArrayList<Apple> apples) { this.apples = apples; }

    public DoubleVector3D getCamera() { return camera; }

    public double getRotX() { return rotX; }
    public double getRotY() { return rotY; }   
    public double getRotZ() { return rotZ; }

    /**
     * Converts this GameEngine instance to a GameModel.
     * Used for saving the game.
     * @return A GameModel instance with the attributes of this game
     */
    public GameModel toGameModel() {
        GameModel model = new GameModel();

        model.gameTitle = gameTitle;
        model.creationDate = creationDate;
        model.score = score;
        model.seed = seed;
        model.worldWidth = worldWidth;
        model.worldHeight = worldHeight;
        model.worldDepth = worldDepth;
        model.rotX = rotX;
        model.rotY = rotY;
        model.rotZ = rotZ;

        return model;
    }

    /**
     * Update method of the world.
     */
    public void update() {
        rotY += Math.PI / 180;
    }

    /**
     * Gets the non-occupied grid positions.
     * Non-occupied entails that no entity is present at the location.
     * @param obstructors Vararg of collections of entities
     * @return An ArrayList of free spaces
     */
    private ArrayList<Vector3D> getAvailableGridPositions(ArrayList<Entity>... obstructors) {
        ArrayList<Vector3D> available = new ArrayList<>();
        available.addAll(this.gridPositions);

        for (ArrayList<Entity> obstructor : obstructors) {
            for (Entity entity : obstructor) {
                available.removeIf( (pos) -> pos.equals(entity.getGridPos()) );
            }
        }

        return available;
    }
    
}
