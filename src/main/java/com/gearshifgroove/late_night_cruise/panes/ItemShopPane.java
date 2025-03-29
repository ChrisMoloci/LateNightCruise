
package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.Const;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

// Not used
public class ItemShopPane extends BorderPane {

    public static Pane displayPane = new Pane();

    public ItemShopPane (){

        VBox sideMenu = new VBox();
        sideMenu.setMinWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setMaxWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setSpacing(10);
        sideMenu.setBackground(new Background(new BackgroundFill(Color.rgb(250,250,250),CornerRadii.EMPTY, Insets.EMPTY)));


        /// Search bar
        TextField search = new TextField("Search..");
        search.setMinWidth(sideMenu.getMinWidth());


        /// Item
        Button weapons = new Button("Weapons");
        weapons.setMinWidth(sideMenu.getMinWidth());
        weapons.setMaxWidth(sideMenu.getMinWidth());


        Button upgrades = new Button("Upgrades");
        upgrades.setMinWidth(sideMenu.getMinWidth());
        upgrades.setMaxWidth(sideMenu.getMinWidth());


        Button items = new Button("Items");
        items.setMinWidth(sideMenu.getMinWidth());
        items.setMaxWidth(sideMenu.getMaxWidth());

        sideMenu.getChildren().addAll(search,weapons,upgrades,items);

        /// MAin content
        displayPane = new Pane();
        displayPane.getChildren().add(new Text("Welcome to the Item Shop"));


        weapons.setOnAction(e->{
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Text("Weapons for Sale"));
        });

        upgrades.setOnAction(e->{
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Text("Upgrades for Sale"));
        });

        items.setOnAction(e->{
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Text("Items for Sale"));
        });


        this.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255),CornerRadii.EMPTY, Insets.EMPTY)));
        this.setLeft(sideMenu);
        this.setCenter(displayPane);

    }
}
