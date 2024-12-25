package com.sakariaslilja.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sakariaslilja.App;
import com.sakariaslilja.models.GameModel;
import com.sakariaslilja.services.GameEngine;
import com.sakariaslilja.services.GamesService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

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

    @FXML
    private HBox selectActions;

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

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
            FXMLLoader loader = new FXMLLoader(App.class.getResource("game_cell.fxml"));
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
            gameTitle.setText((model.gameTitle.length() > 0) ? model.gameTitle : "Game Id: " + model.seed);
            creationDate.setText("Created: " + df.format(model.creationDate));
            lastPlayed.setText("Last played: " + df.format(model.playDate));

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        if (isSelected()) { selectActions.setVisible(true); }
        else { selectActions.setVisible(false); }
    }

    @FXML
    private void playGame() throws IOException {
        ObservableList<GameModel> list = listViewProperty().get().getItems();
        GameModel game = list.get(this.getIndex());
        App.setEngine(new GameEngine(game));
        App.setRoot("game");
    }

    @FXML
    private void deleteGame() {
        ObservableList<GameModel> list = listViewProperty().get().getItems();
        GameModel game = list.get(this.getIndex());
        if (GamesService.deleteGame(game)) {
            list.remove(this.getIndex());
        }
    }
    
}
