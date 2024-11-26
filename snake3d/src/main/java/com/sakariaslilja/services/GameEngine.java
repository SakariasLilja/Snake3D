package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.sakariaslilja.IConstants;
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
public class GameEngine implements IConstants {

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
        this.camera = camera;
        this.rotX = game.rotX;
        this.rotY = game.rotY;
        this.rotZ = game.rotZ;

        // Adding the grid positions of the world
        for (int layer = 0; layer < game.worldDepth; layer++) {
            for (int row = 0; row < game.worldHeight; row++) {
                for (int column = 0; column < game.worldWidth; column++) {
                    gridPositions.add(new Vector3D(column, row, layer));
                }
            }
        }
    }

    // Engine getters and setters

    public ArrayList<Tuple> getEdges() { return edges; }
    protected int gridPositionCount() { return gridPositions.size(); }

    public ArrayList<Apple> getApples() { return apples; }
    protected int countApples() { return apples.size(); }
    public void setApples(ArrayList<Apple> apples) { this.apples = apples; }

    public DoubleVector3D getCamera() { return camera; }

    public int getScore() { return score; }

    /**
     * Gets the x-rotation in radians.
     * @return The x-rotation in radians
     */
    public double getRotX() { return Math.PI * rotX / 180; }

    /**
     * Gets the y-rotation in radians.
     * @return The y-rotation in radians
     */
    public double getRotY() { return Math.PI * rotY / 180; }

    /**
     * Gets the z-rotation in radians.
     * @return The z-rotation in radians
     */
    public double getRotZ() { return Math.PI * rotZ / 180; }


    /**
     * Increases the score by 1.
     */
    public void incrementScore() { score++; }

    /**
     * Increases the score by a given amount.
     * @param amount The amount to increase the score by
     */
    public void increaseScore(int amount) { score += amount; }

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
        rotY += 1;
    }

    /**
     * Gets the non-occupied grid positions.
     * Non-occupied entails that no entity is present at the location.
     * @param obstructors Vararg of collections of entities
     * @return An ArrayList of free spaces
     */
    @SuppressWarnings("unchecked")
    protected <T extends Entity> ArrayList<Vector3D> getAvailableGridPositions(ArrayList<T>... obstructors) {
        ArrayList<Vector3D> available = new ArrayList<>();
        available.addAll(this.gridPositions);

        for (ArrayList<T> obstructor : obstructors) {
            for (T entity : obstructor) {
                available.removeIf( (pos) -> pos.equals(entity.getGridPos()) );
            }
        }

        return available;
    }

    /**
     * Spawns an apple at a random location in the world if the number of
     * apples is less than a given limit.
     * <p> 
     * An apple can only spawn if there are unoccupied locations in the world.
     * @param limit The max number of apples a world can have
     */
    @SuppressWarnings("unchecked")
    protected void spawnApple(int limit) {
        if (this.countApples() < limit) {
            ArrayList<Vector3D> availableGridPositions = getAvailableGridPositions(apples);

            if (availableGridPositions.size() == 0) {
                return;
            }

            int locationIndex = random.nextInt(availableGridPositions.size());
            Vector3D offset = new Vector3D(500, 500, 500);
            Apple apple = new Apple(availableGridPositions.get(locationIndex).mul(UNIT).add(offset));
            apples.add(apple);
        }
    }
    
}
