package de.adeivt.graphics.controls;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.JTextComponent;

public class LisOnFocusLostArranger implements FocusListener {
	IArrangableByText arrangable;
	public LisOnFocusLostArranger(IArrangableByText arrangable) {
		this.arrangable = arrangable;
	}
	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextComponent comp = (JTextComponent)e.getComponent();
		this.arrangable.arrange(comp.getText());
	}

}
