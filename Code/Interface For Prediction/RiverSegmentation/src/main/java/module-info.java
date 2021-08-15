module com.mycompany.riversegmentation {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.riversegmentation to javafx.fxml;
    exports com.mycompany.riversegmentation;
}
