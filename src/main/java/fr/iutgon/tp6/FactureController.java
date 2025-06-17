package fr.iutgon.tp6;

import fr.iutgon.tp6.modele.FabriqueProduits;
import fr.iutgon.tp6.modele.Ligne;
import fr.iutgon.tp6.modele.Produit;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
  public TableView<Ligne> table;
  public TableColumn<Ligne, Integer> qte;
  public TableColumn<Ligne, Produit> produit;
  public TableColumn<Ligne, Number> prixUnitaire;
  public TableColumn<Ligne, Number> totalHT;
  public TableColumn<Ligne, Number> totalTTC;
  public TextField sommeFacture;
  private SimpleFloatProperty sum = new SimpleFloatProperty(0);

  /**
   Called to initialize a controller after its root element has been completely processed.

   @param location  The location used to resolve relative paths for the root object, or
   {@code null} if the location is not known.
   @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
    produit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ligne,Produit>, ObservableValue<Produit>>()    {
		
		@Override
		public ObservableValue<Produit> call(CellDataFeatures<Ligne, Produit> param) {
			return param.getValue().produitProperty();
		}
	});
    prixUnitaire.setCellValueFactory(cell -> cell.getValue().getProduit().prixProperty());
    totalHT.setCellValueFactory(cell -> cell.getValue().totalHTProperty());
    totalTTC.setCellValueFactory(cell -> cell.getValue().totalTTCProperty());
    
    
    

    qte.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    produit.setCellFactory(cell -> new ChoiceBoxTableCell<>(new StringConverter<Produit>() {
    	@Override
    	public String toString(Produit object) {
    		return object.getNom();
    	}
    	
    	@Override
    	public Produit fromString(String s) {
    		int j = 0;
    		for (int i = 0; i<FabriqueProduits.getProduits().size(); i++) {
    			  if (FabriqueProduits.getProduits().get(i).getNom().equals(s)) {
    				  j = i;
    			  }
    		  }
    		return FabriqueProduits.getProduits().get(j);
    	}
    }, FXCollections.observableList(FabriqueProduits.getProduits())));
    sommeFacture.textProperty().bind(sum.asString());
    totalHT.setCellFactory(cell -> new Affichage<>());
    totalTTC.setCellFactory(cell -> new Affichage<>());
    prixUnitaire.setCellFactory(cell -> new Affichage<>());
  }


  public void onAjouter(ActionEvent actionEvent) {
	Random r = new Random();
	int i = r.nextInt()%2+2;
	Ligne ligne = new Ligne(r.nextInt()%50+50, FabriqueProduits.getProduits().get(i));
	table.getItems().add(ligne);
	sum.set(Bindings.add(sum, ligne.totalTTCProperty()).floatValue());
  }
  

}
