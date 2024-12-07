package com.sakariaslilja.services;


import com.sakariaslilja.IConstants;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

public class GameClock extends AnimationTimer implements IConstants {

    GameEngine engine;
    Renderer renderer;
    Label score;
    long startTime;

    public GameClock(GameEngine engine, Renderer renderer, Label score) {
        this.engine = engine;
        this.renderer = renderer;
        this.score = score;
        this.startTime = System.nanoTime();
    }

    @Override
    public void handle(long arg0) {
        long currentTime = System.nanoTime();
        if (FPS <= (currentTime - startTime)) {
            engine.update();
            renderer.render();
            score.setText("" + engine.getScore());
            startTime = currentTime;
        }
    }
    
}
