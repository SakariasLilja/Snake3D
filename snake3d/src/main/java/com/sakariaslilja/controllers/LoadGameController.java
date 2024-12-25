package com.sakariaslilja.controllers;

import com.sakariaslilja.App;
import com.sakariaslilja.models.GameModel;
import com.sakariaslilja.services.GamesService;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Controller for loadgame.fxml.
 * View for loading a previously saved game.
 */
public class LoadGameController {

    ObservableList<GameModel> games = FXCollections.observableArrayList();

    @FXML
    private ListView<GameModel> gamesList;

    /**
     * Method called when the scene is loaded.
     */
    public void initialize() {
        gamesList.setItems(games);
        ArrayList<GameModel> savedGames = GamesService.getGames();
        savedGames.forEach(g -> games.add(g));
    }

    /**
     * Navigates via [App] to mainpage.fxml
     * @throws IOException The file mainpage.fxml wasn't found/couldn't be read.
     */
    @FXML
    private void gotoMainPage() throws IOException {
        App.setRoot("mainpage");
    }
    
}
