package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Controller for newgame.fxml.
 * View for starting a new game and giving it parameters.
 */
public class NewGameController {

    /**
     * Navigates via [App] to mainpage.fxml
     * @throws IOException The file mainpage.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void gotoMainPage() throws IOException {
        App.setRoot("mainpage");
    }
    
}
