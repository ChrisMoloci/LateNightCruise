module com.gearshifgroove.late_night_cruise {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.desktop;
    requires javafx.media;


    opens com.gearshifgroove.late_night_cruise to javafx.fxml;
    exports com.gearshifgroove.late_night_cruise;
    exports com.gearshifgroove.late_night_cruise.panes;
    opens com.gearshifgroove.late_night_cruise.panes to javafx.fxml;
    exports com.gearshifgroove.late_night_cruise.scenes.Store.Data;
    opens com.gearshifgroove.late_night_cruise.scenes.Store.Data to javafx.fxml;
}