package de.adeivt.graphics.controls;

import javax.swing.JComponent;

public class Fact2DPointFactory implements IFactoryOfComponents {

	public static Fact2DPointFactory instance = new Fact2DPointFactory();
	
	public CtrlXDimPoint createElement(String name) {
		return CtrlXDimPoint.gen2DPoint(name);
	}

}
