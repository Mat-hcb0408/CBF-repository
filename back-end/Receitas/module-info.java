module com.cbf1.cbf1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.cbf1.cbf1.receitas to javafx.fxml, javafx.graphics;
    opens com.cbf1.cbf1.federacao to javafx.fxml, javafx.graphics;

    exports com.cbf1.cbf1.receitas;
    exports com.cbf1.cbf1.federacao;
}
