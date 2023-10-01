module com.tests.app {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.tests.app to javafx.fxml;
    exports com.tests.app;
}