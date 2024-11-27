package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import com.sakariaslilja.IConstants;
import com.sakariaslilja.services.GameEngine;
import com.sakariaslilja.services.Renderer;

import javafx.event.EventHandler;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class GameController implements IConstants {

    @FXML
    Canvas gameCanvas;

    @FXML
    VBox bgoverlay;

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

        EventHandler<KeyEvent> buttonPressed = e -> {
            if (e.getCode().equals(KeyCode.ESCAPE) || e.getCode().equals(KeyCode.P)) { this.triggerPause(); }
            else { engine.doButtonAction(e.getCode()); }
        };

        bgoverlay.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (oldScene != null) {
                oldScene.removeEventHandler(KeyEvent.KEY_PRESSED, buttonPressed);
            }
            if (newScene != null) {
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, buttonPressed);
            }
        });
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

    /**
     * Triggers the visibility of the pause overlay and
     * pauses the game.
     */
    private void triggerPause() {
        bgoverlay.setVisible(!bgoverlay.isVisible());
        engine.togglePause();
    }
    
}
