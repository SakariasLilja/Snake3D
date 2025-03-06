package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import com.sakariaslilja.IConstants;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for newgame.fxml.
 * View for starting a new game and giving it parameters.
 */
public class NewGameController implements IConstants {

    @FXML
    Button cancelbtn;

    @FXML
    Button createbtn;

    @FXML
    Button incrwbtn;

    @FXML
    Button decrwbtn;

    @FXML
    Button incrhbtn;

    @FXML
    Button decrhbtn;

    @FXML
    Button incrdbtn;

    @FXML
    Button decrdbtn;

    @FXML
    Label widthLabel;

    @FXML
    Label heightLabel;

    @FXML
    Label depthLabel;

    private Integer width = MIN_WORLD_SIZE;
    private Integer height = MIN_WORLD_SIZE;
    private Integer depth = MIN_WORLD_SIZE;

    /** Called upon launch. Initializes integer properties */
    public void initialize() {
        widthLabel.setText(width.toString());
        heightLabel.setText(height.toString());
        depthLabel.setText(depth.toString());
    }

    @FXML
    private void increaseWidth() {
        width = Math.min(MAX_WORLD_SIZE, width + 1);
        widthLabel.setText(width.toString());
    }

    @FXML
    private void decreaseWidth() {
        width = Math.max(MIN_WORLD_SIZE, width - 1);
        widthLabel.setText(width.toString());
    }

    @FXML
    private void increaseHeight() {
        height = Math.min(MAX_WORLD_SIZE, height + 1);
        heightLabel.setText(height.toString());
    }

    @FXML
    private void decreaseHeight() {
        height = Math.max(MIN_WORLD_SIZE, height - 1);
        heightLabel.setText(height.toString());
    }

    @FXML
    private void increaseDepth() {
        depth = Math.min(MAX_WORLD_SIZE, depth + 1);
        depthLabel.setText(depth.toString());
    }

    @FXML
    private void decreaseDepth() {
        depth = Math.max(MIN_WORLD_SIZE, depth - 1);
        depthLabel.setText(depth.toString());
    }

    /**
     * Navigates via [App] to mainpage.fxml
     * @throws IOException The file mainpage.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void gotoMainPage() throws IOException {
        App.setRoot("mainpage");
    }

    /**
     * Navigates via [App] to game.fxml
     * @throws IOException The file game.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void createGame() throws IOException {
        App.setRoot("game");
    }
    
}
