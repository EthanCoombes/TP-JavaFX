package fr.iutgon.tp6;


import java.text.DecimalFormat;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;

public class Affichage<T> extends TableCell<T,Number>{
	@Override 
	public void updateItem(Number item, boolean empty) {
		super.updateItem(item, empty);
		setAlignment(Pos.CENTER_RIGHT);
		if (empty && item == null) {
			setGraphic(null);
			setText(null);
		}
		else {
			DecimalFormat f = new DecimalFormat("#0.00");
			setText(f.format(item));
			PseudoClass negatifCSS = PseudoClass.getPseudoClass("negatif");
			if (item.floatValue() < 0) {
				pseudoClassStateChanged(negatifCSS, true);
			} else {
				pseudoClassStateChanged(negatifCSS, false);
			}
		}		
	}
}
