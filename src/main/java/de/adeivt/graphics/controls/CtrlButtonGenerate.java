package de.adeivt.graphics.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.adeivt.graphics.ImageGenerator;
import de.adeivt.graphics.model.Model;

public class CtrlButtonGenerate extends JButton {
	private ControlFrame ctrl;
	public CtrlButtonGenerate(String name, ControlFrame ctrl) {
		super(name);
		this.ctrl = ctrl;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageGenerator imGen = new ImageGenerator(ctrl.convertToModel());
				imGen.generate();
				ctrl.setCanvas(imGen.getCanvas());
			}
		});
	}
}
