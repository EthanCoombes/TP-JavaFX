package iut.gon.tp2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TP2App extends Application {

  private BorderPane contenu;
  private ListView<String> gauche;
  private ListView<String> droite;
  private Button versGauche;
  private Button versDroite;
  private Button retireTout;
  private Button ajouteTout;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(TP2App.class.getResource("Tp2.fxml"));
    contenu = fxmlLoader.load();
    Scene scene = new Scene(contenu);
    extraitIds(scene);

    prepareMenus((MenuBar) scene.lookup("#menus"));
    prepareListe();
    prepareBoutons();
    prepareFermeture(stage);

    stage.setTitle("Gestion de groupe");
    stage.setScene(scene);
    stage.show();
  }

  /** Prépare la fenêtre pour demander confirmation avant fermeture */
  private void prepareFermeture(Stage stage) {
    stage.setOnCloseRequest(event -> {
    	Alert a = new Alert(AlertType.CONFIRMATION, "Voulez vous fermez la fenêtre?", ButtonType.YES, ButtonType.NO);
    	a.setTitle("Fermeture de la fenêtre");
    	ButtonType b = new ButtonType(null);
    	b = a.showAndWait().get();
    	if (b != ButtonType.YES) {
    		event.consume();
    	}
    });
  }

  /** Prépare les actions des boutons */
  private void prepareBoutons() {
    ajouteTout.setOnAction(this::onAjouteTout);
    retireTout.setOnAction(this::onRetireTout);
    versDroite.setOnAction(e -> {
    	int i = gauche.getSelectionModel().getSelectedIndex();
    	if (i != -1) {
    		String s = gauche.getItems().remove(i);
    		droite.getItems().add(s);
    	}
    	gauche.getSelectionModel().clearSelection();
    });
    versGauche.setOnAction(e -> {
    	int i = droite.getSelectionModel().getSelectedIndex();
    	if (i != -1) {
    		String s = droite.getItems().remove(i);
    		gauche.getItems().add(s);
    	}
    	droite.getSelectionModel().clearSelection();
    });
  }

  /** Ajoute tous les éléments de gauche dans la liste de droite
   Active le bouton "Retirer tout" et désactive le bouton "Ajouter tout" */
  private void onAjouteTout(ActionEvent actionEvent) {
    droite.getItems().addAll(gauche.getItems());
    gauche.getItems().clear();
    ajouteTout.setDisable(true);
    retireTout.setDisable(false);
  }

  /** Ajoute tous les éléments de droite dans la liste de gauche
   Active le bouton "Ajouter tout" et désactive le bouton "Retirer tout" */
  private void onRetireTout(ActionEvent actionEvent) {
    gauche.getItems().addAll(droite.getItems());
    droite.getItems().clear();
    ajouteTout.setDisable(false);
    retireTout.setDisable(true);
  }

  /** Prépare les menus et leurs événements */
  private void prepareMenus(MenuBar menus) {
    Menu m1 = new Menu("_Fichiers");
    Menu m2 = new Menu("_Aide");
    menus.getMenus().addAll(m1,m2);
    MenuItem i1 = new MenuItem ("Quitter");
    MenuItem i2 = new MenuItem ("À propos");
    MenuItem i3 = new MenuItem ("Quitter");
    MenuItem i4 = new MenuItem ("À propos");
    m2.getItems().addAll(i1,i2);
    m1.getItems().addAll(i3,i4);
    Alert a = new Alert(AlertType.NONE, "Fait par moi!", ButtonType.CLOSE);
    a.setTitle("Alerte de à propos");
    i1.setOnAction(e -> Platform.exit());
    i3.setOnAction(e -> Platform.exit());
    i2.setOnAction(e -> a.show());
    i4.setOnAction(e -> a.show());
    
  }

  /**
   Remplit la liste de gauche avec des valeurs
   Active le bouton "Ajouter tout"
   */
  private void prepareListe() {
	  gauche.getItems().addAll("un", "deux", "trois");
	  ajouteTout.setDisable(false);
  }

  private void extraitIds(Scene scene) {
    gauche = (ListView<String>) scene.lookup("#gauche");
    droite = (ListView<String>) scene.lookup("#droite");
    versGauche = (Button) scene.lookup("#versGauche");
    versDroite = (Button) scene.lookup("#versDroite");
    retireTout = (Button) scene.lookup("#retireTout");
    ajouteTout = (Button) scene.lookup("#ajouteTout");
  }

  public static void main(String[] args) {
    launch();
  }
}
