package fr.unicaen.iut.tp5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource("Demineur.fxml"));
		Scene scene = new Scene(fxmlloader.load(), 800, 600);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
	    launch();
	}

}
