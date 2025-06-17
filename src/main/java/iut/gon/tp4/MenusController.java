package iut.gon.tp4;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class MenusController {
	private GrilleModel modele;
	private @FXML GridPane grille;
	private Scores table;

	  @FXML
	  public void onMenuNouvelle(ActionEvent evt) {
	    modele.nouvellePartie();
	  }
	  @FXML
	  public void onMenuTable(ActionEvent evt) {
		  afficherTable();
	  }

	  @FXML
	  public void onMenuQuitter(ActionEvent evt) {
	    Platform.exit();
	  }
	  @FXML
	  public void afficherTable() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GrilleController.class.getResource("table.fxml"));
			grille.getScene().setRoot(fxmlLoader.load());
			TableController tableController = fxmlLoader.getController();
			tableController.setScores(table);
		} catch (IOException e) {
			e.printStackTrace();
			}
	  }
	  
	  @FXML 
	  public void setParams(GrilleModel grilleModel, Scores scores) {
		  modele = grilleModel;
		  table = scores;
	  }
}
