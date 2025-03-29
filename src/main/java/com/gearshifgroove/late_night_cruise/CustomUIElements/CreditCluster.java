package com.gearshifgroove.late_night_cruise.CustomUIElements;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

// Author(s): Christian Moloci

// Template to create a cluster of credits which take a title and credits (names/resources)
public class CreditCluster extends VBox {
    private Text title;
    private ArrayList<Text> texts = new ArrayList<>();

    // Constructor, which take a title and a list of Text nodes and creates the cluster
    public CreditCluster(Text title, ArrayList<Text> credits) {
        // Set style for the title
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);
        // Add the title plus some padding
        this.getChildren().addAll(title, new PaddingBox(20, 0));
        // for each text node, style and add to VBox with some padding
        for (Text credit : credits) {
            credit.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
            credit.setFill(Color.WHITE);
            credit.setTextAlignment(TextAlignment.CENTER);
            this.getChildren().addAll(credit, new PaddingBox(30, 0));
        }

    }
}
