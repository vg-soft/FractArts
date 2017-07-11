package de.adeivt.graphics.controls;

import javax.swing.JComponent;

public class Fact2DPointWithColorFactory implements IFactoryOfComponents {
	public static Fact2DPointWithColorFactory instance = new Fact2DPointWithColorFactory();
	public JComponent createElement(String name) {
		return CtrlXDimPoint.gen2DPointWithColor(name);
	}
}
