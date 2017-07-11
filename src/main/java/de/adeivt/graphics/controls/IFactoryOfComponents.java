package de.adeivt.graphics.controls;

import javax.swing.JComponent;

public interface IFactoryOfComponents {
	public JComponent createElement(String name);
}
