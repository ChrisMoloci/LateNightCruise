package com.gearshifgroove.late_night_cruise;

import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import javafx.application.Application;
import javafx.stage.Stage;
//import javafx.scene.media.Media;

import java.io.IOException;

// Author(s): Christian Moloci

// Main class, the entry point of the program
public class LateNightCruise extends Application {
    // Set a public stage that we can modify from other classes
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        // Set the mainStage to stage
        mainStage = stage;

        // Set a title
        mainStage.setTitle("Late Night Cruise");

        // Set the entry scene (the main menu scene)
        mainStage.setScene(new MainMenuScene());

        // Show the stage
        mainStage.show();
    }

    // Main method which launches the start function
    public static void main(String[] args) {
        launch();
    }
}