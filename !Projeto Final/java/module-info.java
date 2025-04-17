module org.cbf.cbf_teste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.cbf.cbf to javafx.fxml;
    exports org.cbf.cbf;
    exports org.cbf.cbf.DB;
    opens org.cbf.cbf.DB to javafx.fxml;
    exports org.cbf.cbf.Controller;
    opens org.cbf.cbf.Controller to javafx.fxml;
    exports org.cbf.cbf.POJO;
    opens org.cbf.cbf.POJO to javafx.fxml;

}