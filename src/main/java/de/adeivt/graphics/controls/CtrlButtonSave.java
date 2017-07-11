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

public class CtrlButtonSave extends JButton {
	private ControlFrame ctrl;
	public CtrlButtonSave(String name, ControlFrame ctrl) {
		super(name);
		this.ctrl = ctrl;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FileDialog oeffnen:
				JFileChooser jfc = new JFileChooser();
			    int retrival = jfc.showSaveDialog(null);
			    if (retrival == JFileChooser.APPROVE_OPTION) {
	        	String modelStr = ctrl.convertToModel().toJSONString();
	        	try {
	        		File f = jfc.getSelectedFile();
	        		String fName = f.getAbsolutePath();
	        		if(!fName.toLowerCase().endsWith(".json")) {
	        			fName += ".json";
	        		}
					IoUtil.saveFile(new File(fName), modelStr);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    }
			}
		});
	}
}
