package de.adeivt.graphics.controls;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public abstract class LisChkAbstractValue  implements FocusListener{
	public static final Color bgWithFocus = new Color(255, 255, 128);
	public static final Color bgNoFocus = new Color(255, 255, 255);
	 String currValue;
	 JTextField field;
	 public LisChkAbstractValue(JTextField f) {
		 this.field = f;
		 this.currValue = f.getText();
	 }
	 public abstract  boolean newValueIsValid(String newValue);
	 
	@Override
	public void focusGained(FocusEvent e) {
		field.setBackground(bgWithFocus);
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(!newValueIsValid(field.getText())) {
			field.setText(this.currValue);
		} else {
			this.currValue = field.getText();
		}
		field.setBackground(bgNoFocus);
	}

		  

}
