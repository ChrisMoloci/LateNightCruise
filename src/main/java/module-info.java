module com.gearshifgroove.late_night_cruise {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens com.gearshifgroove.late_night_cruise to javafx.fxml;
    exports com.gearshifgroove.late_night_cruise;
    exports com.gearshifgroove.late_night_cruise.panes;
    opens com.gearshifgroove.late_night_cruise.panes to javafx.fxml;
}