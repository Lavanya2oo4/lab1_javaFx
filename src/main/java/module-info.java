module com.example.lab1_lavanya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab1_lavanya to javafx.fxml;
    exports com.example.lab1_lavanya;
}