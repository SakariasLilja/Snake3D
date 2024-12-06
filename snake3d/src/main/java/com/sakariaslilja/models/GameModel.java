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
    public int worldWidth = 5;
    public int worldHeight = 5;
    public int worldDepth = 5;
    public double qW = 1;
    public double qX = 0;
    public double qY = 0;
    public double qZ = 0;
    
}
