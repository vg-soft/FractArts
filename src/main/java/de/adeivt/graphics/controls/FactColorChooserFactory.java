package de.adeivt.graphics.controls;

import javax.swing.JComponent;

public class FactColorChooserFactory implements IFactoryOfComponents {

	public JComponent createElement(String name) {
		return new CtrlCompactColorChooser(name);
	}

}
