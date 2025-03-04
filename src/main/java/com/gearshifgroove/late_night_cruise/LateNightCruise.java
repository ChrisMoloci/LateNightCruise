package com.gearshifgroove.late_night_cruise;

import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import com.gearshifgroove.late_night_cruise.scenes.StoreScene;
import javafx.application.Application;
import javafx.stage.Stage;
//import javafx.scene.media.Media;

import java.io.IOException;

public class LateNightCruise extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainStage.setTitle("Late Night Cruise");
//        mainStage.setScene(new MainMenuScene());
        mainStage.setScene(new StoreScene());

        mainStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}