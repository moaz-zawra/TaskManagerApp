module com.moaz {
    requires javafx.controls;
    requires javafx.fxml;

    // Jackson dependencies
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    // Allow reflection for FXML and serialization
    opens com.moaz to javafx.fxml, javafx.base, com.fasterxml.jackson.databind;

    exports com.moaz;
}
