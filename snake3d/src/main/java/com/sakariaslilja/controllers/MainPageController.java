package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class MainPageController {

    @FXML
    private void gotoNewGame() throws IOException {
        App.setRoot("secondary");
    }
    
}
