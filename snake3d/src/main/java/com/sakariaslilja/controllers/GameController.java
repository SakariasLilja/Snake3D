package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import com.sakariaslilja.IConstants;
import com.sakariaslilja.services.GameEngine;
import com.sakariaslilja.services.Renderer;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;

public class GameController implements IConstants {

    @FXML
    Canvas gameCanvas;

    @FXML
    VBox bgoverlay = new VBox();

    GameEngine engine;
    Renderer renderer;

    long startTime = System.nanoTime();
    AnimationTimer gameClock = new AnimationTimer() {
        @Override
        public void handle(long arg0) {
            long currentTime = System.nanoTime();
            if (1000000000l / (long) FPS <= (currentTime - startTime)) {
                engine.update();
                renderer.render();
                startTime = currentTime;
            }
        };
    };
    
    public void initialize() {
        gameCanvas.setWidth(App.getWidth());
        gameCanvas.setHeight(App.getHeight());
        this.engine = App.getEngine();
        this.renderer = new Renderer(gameCanvas.getGraphicsContext2D(), engine);
        gameClock.start();
    }

    /**
     * Navigates via [App] to mainpage.fxml.
     * @throws IOException The file mainpage.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void gotoMainPage() throws IOException {
        App.setRoot("mainpage");
    }

    /**
     * Calls closeApp from [App].
     * Stops the application.
     */
    @FXML
    private void saveAndQuit() {
        App.closeApp();
    }
    
}
