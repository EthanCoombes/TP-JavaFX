package iut.gon.TP3;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

public class GrilleModel {
	SimpleStringProperty tab[][] = new SimpleStringProperty[3][3];
	
	public GrilleModel() {
		SimpleStringProperty s = new SimpleStringProperty("Case");
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				
				tab[i][j]=s;
			}
		}
	}

	public Property<String> getCase(int l, int c) {
		return tab[l][c];
	}
	
	public void setCase (int l, int c, String s) {
		tab[l][c].setValue(s);
	}
	
}
