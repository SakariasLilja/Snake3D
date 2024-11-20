package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class MainPageController {

    /**
     * Navigates via [App] to the view newgame.fxml.
     * @throws IOException The file newgame.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void gotoNewGame() throws IOException {
        App.setRoot("newgame");
    }

    /**
     * Navigates via [App] to the view loadgame.fxml.
     * @throws IOException The file loadgame.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void gotoLoadGame() throws IOException {
        App.setRoot("loadgame");
    }

    /**
     * Navigates via [App] to the view scoreboard.fxml.
     * @throws IOException The file scoreboard.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void gotoScoreboard() throws IOException {
        App.setRoot("scoreboard");
    }
    
}
