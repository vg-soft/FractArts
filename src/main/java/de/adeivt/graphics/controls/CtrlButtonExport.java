package de.adeivt.graphics.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import de.adeivt.graphics.ImageExporter;
import de.adeivt.graphics.ImageGenerator;
import de.adeivt.graphics.model.Model;
import de.vg_soft.IoUtil;

public class CtrlButtonExport extends JButton {
	private ControlFrame ctrl;
	public CtrlButtonExport(String name, ControlFrame ctrl) {
		super(name);
		this.ctrl = ctrl;
		this.addActionListener( e ->  {
    		RenderedImage rim = ((CtrlButtonExport)e.getSource()).ctrl.getImage();
    		ImageExporter iex = new ImageExporter(rim);
    		try {
				iex.export();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
}
