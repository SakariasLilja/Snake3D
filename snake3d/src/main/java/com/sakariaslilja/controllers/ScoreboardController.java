package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Controller for scoreboard.fxml.
 * View for viewing previous highscores etc.
 */
public class ScoreboardController {

    /**
     * Navigates via [App] to mainpage.fxml
     * @throws IOException The file mainpage.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void gotoMainPage() throws IOException {
        App.setRoot("mainpage");
    }
    
}
