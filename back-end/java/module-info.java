module org.cbf.cbf_teste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.cbf.cbf_teste to javafx.fxml;
    exports org.cbf.cbf_teste;
}