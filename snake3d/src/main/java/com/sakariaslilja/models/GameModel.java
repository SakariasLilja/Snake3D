package com.sakariaslilja.models;

import java.util.Date;

/**
 * Game model used for Gson (de)serialization.
 * Utilised by GameEngine class.
 */
public class GameModel {

    public String gameTitle = "";
    public Date creationDate = new Date();
    public Date playDate = new Date();
    public int score = 0;
    public long seed = System.nanoTime();
    public int wordWidth = 5;
    public int worldHeight = 5;
    public int worldDepth = 5;
    public int rotX = 0;
    public int rotY = 0;
    public int rotZ = 0;
    
}
