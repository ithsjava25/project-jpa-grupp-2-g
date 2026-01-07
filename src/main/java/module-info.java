module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires javafx.graphics;
    requires javafx.base;
    requires java.xml;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;

    opens frontend to javafx.fxml;
    exports frontend;
}
