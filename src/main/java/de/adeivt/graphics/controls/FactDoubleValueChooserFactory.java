package de.adeivt.graphics.controls;

import javax.swing.JComponent;

public class FactDoubleValueChooserFactory implements IFactoryOfComponents {
	public static FactDoubleValueChooserFactory instance = new FactDoubleValueChooserFactory();
	public JComponent createElement(String name) {
		return new CtrlDoubleValueChooser(name, 0., 1., 0.5, 3); 
	}
}
