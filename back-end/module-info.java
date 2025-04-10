module com.cbf.cbf {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.cbf.cbf to javafx.fxml;
    exports com.cbf.cbf;
}