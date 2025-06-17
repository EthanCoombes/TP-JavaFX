module iut.gon.TP3 {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.base;
	requires javafx.base;

    opens iut.gon.TP3 to javafx.fxml;
    exports iut.gon.TP3;
}
