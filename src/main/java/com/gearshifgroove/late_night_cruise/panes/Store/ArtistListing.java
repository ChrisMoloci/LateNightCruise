package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

// Author(s): Christian Moloci

// A custom node that is used in various places to show info and actions for an artist
public class ArtistListing extends GridPane {
    // Artist listings take an Artist object and turn it into a UI element
    public ArtistListing(Artist artist) {
        // Set the styling of the GridPane
        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        this.setPadding(new Insets(10, 0, 10, 10));
        this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), null, new Insets(3, 5, 3, 5))));

        // Set some column constraints to better control the layout
        ColumnConstraints artistInfo = new ColumnConstraints();
        artistInfo.setPercentWidth(92);
        ColumnConstraints artistSelect = new ColumnConstraints();
        artistSelect.setPercentWidth(8);

        // Apply the column constraints
        this.getColumnConstraints().addAll(artistInfo, artistSelect);

        // Text node that displays the artists name
        Text artistName = new Text(artist.getName());
        artistName.setFill(Color.WHITE);
        artistName.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Button to open an artists page
        Button viewArtist = new Button(">");
        viewArtist.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 0))));
        viewArtist.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        viewArtist.setTextFill(Color.WHITE);

        // Button that opens a new Artist Page that shows the aetists songs
        viewArtist.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new ArtistPage(artist));
        });

        // Add the elements to their appropriate locations
        this.add(artistName, 0, 0);
        this.add(viewArtist, 1, 0);

        // Set the dimensions
        this.setMinWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
        this.setMaxWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);

    }
}
