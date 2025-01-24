package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for newgame.fxml.
 * View for starting a new game and giving it parameters.
 */
public class NewGameController {

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
