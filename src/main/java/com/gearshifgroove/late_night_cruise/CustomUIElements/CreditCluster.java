package com.gearshifgroove.late_night_cruise.CustomUIElements;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

// Author: Christian Moloci
// Student #: 0874409
// Date: 02/28/2025
// Class: MAD200 002

// Template to create a cluster of credits which take a title and credits (names/resources)
public class CreditCluster extends VBox {
    private Text title;
    private ArrayList<Text> texts = new ArrayList<>();

    public CreditCluster(Text title, ArrayList<Text> credits) {
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);
        this.getChildren().addAll(title, new PaddingBox(20, 0));
        for (Text credit : credits) {
            credit.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
            credit.setFill(Color.WHITE);
            credit.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().addAll(credit, new PaddingBox(30, 0));
        }

    }
}
