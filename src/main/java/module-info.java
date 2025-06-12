module org.sysestoque {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.sysestoque.controller;
    exports org.sysestoque.dao;
    exports org.sysestoque.model;

    opens org.sysestoque to javafx.graphics, javafx.fxml;
    opens org.sysestoque.controller to javafx.fxml;
    opens org.sysestoque.model to javafx.base;
}
