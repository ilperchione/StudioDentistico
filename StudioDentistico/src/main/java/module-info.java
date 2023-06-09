module com.example.studiodentistico {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.studiodentistico to javafx.fxml;
    exports com.example.studiodentistico;
}