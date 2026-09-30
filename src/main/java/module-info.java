module de.fritz.raytrace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.xml.dom;
    requires java.sql;
    // requires de.fritz.raytrace;


    opens de.fritz.raytrace to javafx.fxml;
    exports de.fritz.raytrace;
    exports de.fritz.raytrace.objects;
    opens de.fritz.raytrace.objects to javafx.fxml;
    exports de.fritz.raytrace.engine;
    opens de.fritz.raytrace.engine to javafx.fxml;
    exports de.fritz.raytrace.objects.shapes;
    opens de.fritz.raytrace.objects.shapes to javafx.fxml;
}