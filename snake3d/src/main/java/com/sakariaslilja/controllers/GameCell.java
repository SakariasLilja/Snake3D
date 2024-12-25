package com.sakariaslilja.controllers;

import java.io.IOException;

import com.sakariaslilja.models.GameModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 * Game cell for ListViews that correctly displays the contents of the GameModel.
 */
public class GameCell extends ListCell<GameModel> {

    @FXML
    private Label gameTitle;

    @FXML
    private Label creationDate;

    @FXML
    private Label lastPlayed;

    /**
     * GameCell that loads up an fxml-file to display elements of a game.
     */
    public GameCell() {
        loadFXML();
    }

    /**
     * Method for loading up the associated fxml file
     */
    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } 
        
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(GameModel model, boolean empty) {
        super.updateItem(model, empty);

        if (empty || model == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        else {
            gameTitle.setText((model.gameTitle != "") ? model.gameTitle : "Game Id: " + model.seed);
            creationDate.setText(model.creationDate.toString());
            lastPlayed.setText(model.playDate.toString());

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
    
}
