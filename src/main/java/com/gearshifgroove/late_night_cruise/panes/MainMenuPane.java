package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainMenuPane extends BorderPane {
    public MainMenuPane() {
        Button button = new Button("Start");
        button.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
        });

//      this.setOnKeyPressed(event -> {
//            System.out.println(event.getCode());
//        });
        this.setCenter(button);
    }
}
