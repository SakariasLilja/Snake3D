package com.sakariaslilja.services;

import com.sakariaslilja.IConstants;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

/**
 * Clock that runs the game's update cycle
 */
public class GameClock extends AnimationTimer implements IConstants {

    private GameEngine engine;
    private Renderer renderer;
    private Label score;
    private long startTime;

    /**
     * Instantiates the clock to to a game instance and a renderer
     * @param engine The engine to be updated
     * @param renderer The renderer to be updated
     * @param score The score label to be updated
     */
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
