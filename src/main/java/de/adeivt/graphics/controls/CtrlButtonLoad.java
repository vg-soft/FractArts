package de.adeivt.graphics.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import de.adeivt.graphics.ImageGenerator;
import de.adeivt.graphics.model.Model;
import de.vg_soft.IoUtil;

public class CtrlButtonLoad extends JButton {
	private ControlFrame ctrl;
	public CtrlButtonLoad(String name, ControlFrame ctrl) {
		super(name);
		this.ctrl = ctrl;
		this.addActionListener( e ->  {
				// FileDialog oeffnen:
				JFileChooser jfc = new JFileChooser();
				int retrival = jfc.showDialog(null, "Open json Configuration");
			    if (retrival == JFileChooser.APPROVE_OPTION) {
		        	try {
						String content = IoUtil.loadFromFile(jfc.getSelectedFile());
						((CtrlButtonLoad)e.getSource()).ctrl.initFromString(content);
					} catch (IOException | ExcInconsistentType | ExcIllegalValue e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
			    }
			});
	}
}
