package de.adeivt.graphics.controls;

import javax.swing.JComponent;

public class Fact2DPointWithColorMagnitudeFactory implements IFactoryOfComponents {
	public static Fact2DPointWithColorMagnitudeFactory instance = new Fact2DPointWithColorMagnitudeFactory();
	public JComponent createElement(String name) {
		return CtrlXDimPoint.gen2DPointWithColorMagnitude(name);
	}
}
