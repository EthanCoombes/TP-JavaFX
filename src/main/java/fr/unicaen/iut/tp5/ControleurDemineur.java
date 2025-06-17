package fr.unicaen.iut.tp5;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

public class ControleurDemineur implements Initializable{
	private ModeleDemineur modeleDemineur;
	@FXML private GridPane gridPane;
	@FXML private TextField marque;
	@FXML private TextField inconnue;
	@FXML private ToggleGroup bouton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modeleDemineur = new ModeleDemineur(10,10,10);
		marque.textProperty().bind(modeleDemineur.nbMarquesProperty().asString());
		inconnue.textProperty().bind(modeleDemineur.nbInconnuesProperty().asString());
		bouton.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				initGrille(newValue.getUserData());	
			}
		});		
	}

	public void initGrille (Object object) {
		Background inconnu = new Background(new BackgroundFill(Color.AQUA, new CornerRadii(20), null));
		Background libre = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
		Background echec = new Background(new BackgroundFill(Color.RED, null, null));
		Background marquée = new Background(new BackgroundFill(Color.LEMONCHIFFON, null, null));
		gridPane.getColumnConstraints().clear();
		gridPane.getRowConstraints().clear();
		gridPane.getChildren().clear();
		int[]tab = ModeleDemineur.parseUserData(object.toString());
		modeleDemineur.setTaille(tab[0], tab[1], tab[2]);
		for (int i = 0; i<tab[1]; i++) {
			RowConstraints rc = new RowConstraints();
			rc.setPrefHeight(32);
			gridPane.getRowConstraints().add(rc);
		}
		for (int j = 0; j<tab[0]; j++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setPrefWidth(32);
			gridPane.getColumnConstraints().add(cc);
		}
		gridPane.setAlignment(Pos.CENTER);
		for (int i = 0; i<tab[1]; i++) {
			for (int j = 0; j<tab[0]; j++) {
				Label label = new Label();
				label.setPrefSize(31, 31);;
				label.setBackground(inconnu);
				label.textProperty().bind(modeleDemineur.texteProperty(j, i));
				label.setTextAlignment(TextAlignment.CENTER);
				label.setAlignment(Pos.CENTER);
				final int o = j;
				final int p = i;
				label.setOnMouseClicked(event -> {
					if (event.getButton() == MouseButton.SECONDARY && !modeleDemineur.estPerdu() && label.getBackground()==inconnu) {
						modeleDemineur.marque(o, p);
						label.setBackground(marquée);
					}
					if (event.getButton() == MouseButton.PRIMARY && !modeleDemineur.estPerdu()) {
						modeleDemineur.revele(o, p);
						if (label.getText()=="X") {
							label.setBackground(echec);
						}
						else{
							label.setBackground(libre);
						}
					}
				});;
				gridPane.add(label, o, p);
			}
		}
		
	}

}
