module org.cbf.cbf_teste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.cbf.cbf_teste to javafx.fxml;
    exports org.cbf.cbf_teste;
    exports org.cbf.cbf_teste.DB;
    opens org.cbf.cbf_teste.DB to javafx.fxml;
    exports org.cbf.cbf_teste.Controller;
    opens org.cbf.cbf_teste.Controller to javafx.fxml;
    exports org.cbf.cbf_teste.POJO;
    opens org.cbf.cbf_teste.POJO to javafx.fxml;
}