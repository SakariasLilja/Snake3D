package com.sakariaslilja.models;

import java.util.Date;

import com.sakariaslilja.IConstants;

/**
 * Model for a highscore set in the game.
 * Each ended game will have a final score, a date when the game was ended,
 * and the dimensions of the world the score was set in.
 */
public class HighscoreModel implements IConstants {

    public int score = 0;
    public Date creationDate = new Date();
    public Date date = new Date();
    public int worldWidth = MIN_WORLD_SIZE;
    public int worldHeight = MIN_WORLD_SIZE;
    public int worldDepth = MIN_WORLD_SIZE;
    
}
