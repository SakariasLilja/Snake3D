package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class GameController {

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
