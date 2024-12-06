package com.sakariaslilja.services;

import com.sakariaslilja.IConstants;

import javafx.animation.AnimationTimer;

public class GameClock extends AnimationTimer implements IConstants {

    GameEngine engine;
    Renderer renderer;
    long startTime;

    public GameClock(GameEngine engine, Renderer renderer) {
        this.engine = engine;
        this.renderer = renderer;
        this.startTime = System.nanoTime();
    }

    @Override
    public void handle(long arg0) {
        long currentTime = System.nanoTime();
        if (FPS <= (currentTime - startTime)) {
            engine.update();
            renderer.render();
            startTime = currentTime;
        }
    }
    
}
