module org.cbf.cbf_teste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports org.cbf.cbf.repository;
    opens org.cbf.cbf.repository to javafx.fxml;

    exports org.cbf.cbf.controller;
    opens org.cbf.cbf.controller to javafx.fxml;

    exports org.cbf.cbf.model.entity;
    opens org.cbf.cbf.model.entity to javafx.fxml;

    exports org.cbf.cbf.model.utils;
    opens org.cbf.cbf.model.utils to javafx.fxml;

    exports org.cbf.cbf.main;
    opens org.cbf.cbf.main to javafx.fxml;
}
