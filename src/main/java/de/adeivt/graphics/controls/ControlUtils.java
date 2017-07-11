package de.adeivt.graphics.controls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ControlUtils {
	public static Dimension getPreferredSizeFromVisibleComponentsVertical(Container ct, int addHeight)
	{
		int w = 0;
		int h = 0;
		for(Component c: ct.getComponents()) {
			if(c.isVisible()) {
				Dimension d = c.getPreferredSize();
				if(w<d.width) w = d.width;
				h += d.height;
			}
		}
		return new Dimension(w, h+addHeight);
	}
	public static Dimension getPreferredSizeFromVisibleComponentsVertical(Container ct) {
		return getPreferredSizeFromVisibleComponentsVertical(ct, 0);
	}
	private static JColorChooser testControl = null;
	public static int getCommonPreferedWidth() {
		if(testControl==null) {
			testControl = new JColorChooser();
		}
		return testControl.getPreferredSize().width;
	}
	public static void setWidthOfAllComponents(Container cont, int width) {
		List<JPanel> panLst = new ArrayList<JPanel>();
		for(Component comp: cont.getComponents()) {
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.add(comp);
			panLst.add(p);
		}
		cont.removeAll();
		cont.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = c.gridy = 0;
		for(JPanel pan: panLst) {
			pan.setBorder(new LineBorder(Color.gray, 1));
			pan.setPreferredSize(new Dimension(ControlUtils.getCommonPreferedWidth(), pan.getPreferredSize().height));
			cont.add(pan, c);
			c.gridy++;
		}
	}
	public static void setCommonWidthOfAllComponents(Container c) {
		setWidthOfAllComponents(c, getCommonPreferedWidth());
	}
	public static void setPreferedWidth(Component c, int w) {
		if(c!=null)
		c.setPreferredSize(new Dimension(w, c.getPreferredSize().height));
	}
	public static void setPreferedWidth(Component c, Dimension d) {
		if(c!=null)
		c.setPreferredSize(new Dimension(d.width, c.getPreferredSize().height));
	}
	public static void setPreferedHeight(Component c, int h) {
		if(c!=null)
		c.setPreferredSize(new Dimension(c.getPreferredSize().width, h));
	}
	public static void setPreferedHeight(Component c, Dimension d) {
		if(c!=null)
		c.setPreferredSize(new Dimension(c.getPreferredSize().width, d.height));
	}
	public static void setPreferedWidthToCommon(Component c) {
		setPreferedWidth(c, getCommonPreferedWidth());
	}
	public static Dimension getPreferredSizeWithCommonWidth(Component c, Dimension oldPrefSize) {
		return new Dimension(getCommonPreferedWidth(), oldPrefSize.height);
	}
}
