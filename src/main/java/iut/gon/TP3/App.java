package iut.gon.TP3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        GrilleModel gm = new GrilleModel();
        GrilleController gc = new GrilleController(gm);
        scene = new Scene(loadFXML("primary", gc), 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
        	switch (event.getText()) {
        	case "1" : gm.setCase(2,0, "Touche"); break;
        	case "2" : gm.setCase(2,1, "Touche"); break;
        	case "3" : gm.setCase(2,2, "Touche"); break;
        	case "4" : gm.setCase(1,0, "Touche"); break;
        	case "5" : gm.setCase(1,1, "Touche"); break;
        	case "6" : gm.setCase(1,2, "Touche"); break;
        	case "7" : gm.setCase(0,0, "Touche"); break;
        	case "8" : gm.setCase(0,1, "Touche"); break;
        	case "9" : gm.setCase(0,2, "Touche"); break;
        	}
        	});
    }

    private static Parent loadFXML(String fxml, GrilleController gc) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(gc);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}