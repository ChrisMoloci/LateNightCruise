module com.gearshifgroove.late_night_cruise {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gearshifgroove.late_night_cruise to javafx.fxml;
    exports com.gearshifgroove.late_night_cruise;
}