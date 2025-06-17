package iut.gon.TP3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GrilleController implements Initializable{
	@FXML
	GridPane grille;
	Label tab[][] = new Label[3][3];
	GrilleModel gm;
	
	public GrilleController (GrilleModel gm) {
		this.gm = gm;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				Label label = new Label();
				label.textProperty().bind(gm.tab[i][j]);
				final int o = i;
				final int p = j;
				label.setOnMouseClicked(e->{
					gm.setCase(o, p, "Bonjour");
				});
				label.setMaxSize(1000, 1000);
				label.setAlignment(Pos.CENTER);
				tab[i][j] = label;
				grille.add(tab[i][j], j, i);

			}
		}
	}

}
