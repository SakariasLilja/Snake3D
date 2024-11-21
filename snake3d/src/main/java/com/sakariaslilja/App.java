package com.sakariaslilja;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App for the Snake3D game.
 */
public class App extends Application {

    private static Scene scene;

    // Variables of the scene.
    private static final String primarySceneFXML = "mainpage";

    // Sets the primary scene of the application.
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        stage.setTitle(Constants.GAME_NAME);
        scene = new Scene(loadFXML(primarySceneFXML), Constants.WIDTH, Constants.HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root of the application's scene to be the inputed fxml-file.
     * Used for navigation between views.
     * @param fxml File that is to be made the application's scene's root.
     * @throws IOException File wasn't found/able to be read.
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Method for loading a fxml-file.
     * @param fxml The fxml-file to load.
     * @return The loaded fxml-file.
     * @throws IOException File wasn't found/able to be read.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Stops the application from running.
     */
    public static void closeApp() {
        Platform.exit();
    }

    /**
     * Main method that launches the application.
     * @param args Default parameter for Java-applications.
     */
    public static void main(String[] args) {
        launch();
    }

}