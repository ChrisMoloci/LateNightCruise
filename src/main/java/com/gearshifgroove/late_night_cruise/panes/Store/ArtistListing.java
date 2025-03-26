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

public class ArtistListing extends GridPane {
    public ArtistListing(Artist artist) {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        this.setPadding(new Insets(10, 0, 10, 10));
        this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), null, new Insets(3, 5, 3, 5))));

        ColumnConstraints artistInfo = new ColumnConstraints();
        artistInfo.setPercentWidth(92);
        ColumnConstraints artistSelect = new ColumnConstraints();
        artistSelect.setPercentWidth(8);

        this.getColumnConstraints().addAll(artistInfo, artistSelect);

        Text artistName = new Text(artist.getName());
        artistName.setFill(Color.WHITE);
        artistName.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Button viewArtist = new Button(">");
        viewArtist.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 0))));
        viewArtist.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        viewArtist.setTextFill(Color.WHITE);

        viewArtist.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new ArtistPage(artist));
        });

        this.add(artistName, 0, 0);
        this.add(viewArtist, 1, 0);

        this.setMinWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
        this.setMaxWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);

    }
}
