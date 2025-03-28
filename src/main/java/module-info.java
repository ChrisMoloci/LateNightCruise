module com.gearshifgroove.late_night_cruise {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires javafx.media;
    requires javafx.graphics;


    opens com.gearshifgroove.late_night_cruise to javafx.fxml;
    exports com.gearshifgroove.late_night_cruise;
    exports com.gearshifgroove.late_night_cruise.panes;
    opens com.gearshifgroove.late_night_cruise.panes to javafx.fxml;
    exports com.gearshifgroove.late_night_cruise.panes.Store.Data;
    opens com.gearshifgroove.late_night_cruise.panes.Store.Data to javafx.fxml;
}