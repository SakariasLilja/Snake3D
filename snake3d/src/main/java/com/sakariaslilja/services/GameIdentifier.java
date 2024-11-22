package com.sakariaslilja.services;

/**
 * Identifier for a game.
 */
public class GameIdentifier {

    // Private variables
    private String gameTitle;
    private String creationDate;
    private String playDate;
    private int score = 0;
    private int seed = 0;

    /**
     * An instance of a previously played game.
     * @param gameTitle The title of the game
     * @param creationDate The date of the game's creation
     * @param playDate The date when this game was last played
     * @param score The score of this game
     * @param seed The seed of the game
     */
    public GameIdentifier(String gameTitle, String creationDate, String playDate, int score, int seed) {
        this.gameTitle = gameTitle;
        this.creationDate = creationDate;
        this.playDate = playDate;
        this.score = score;
        this.seed = seed;
    }

    /**
     * An instance of a new game
     * @param gameTitle The title of the game
     * @param date The date the game was made
     */
    public GameIdentifier(String gameTitle, String date) {
        this.gameTitle = gameTitle;
        this.creationDate = date;
        this.playDate = date;
    }

    /**
     * Sets the score of this game
     * @param newScore
     */
    public void setScore(int newScore) {
        this.score = newScore;
    }

    /**
     * Sets the seed of this game
     * @param newSeed
     */
    public void setSeed(int newSeed) {
        this.seed = newSeed;
    }

    /**
     * Getter for the game's title
     * @return The title of this game
     */
    public String getGameTitle() {
        return this.gameTitle;
    }

    /**
     * Getter for the game's creation date
     * @return The creation date of this game
     */
    public String getCreationDate() {
        return this.creationDate;
    }

    /**
     * Getter for the date the game was last played
     * @return The date of when the game was last played
     */
    public String getLastPlayedDate() {
        return this.playDate;
    }

    /**
     * Getter for the game's score
     * @return The score of this game
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Getter for the game's seed
     * @return The seed of this game
     */
    public int getSeed() {
        return this.seed;
    }
    
}
