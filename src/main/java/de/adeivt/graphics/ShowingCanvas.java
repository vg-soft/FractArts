package de.adeivt.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ShowingCanvas extends JPanel {
	private PreparingCanvas prep;
	BufferedImage bufImg;
	
	public BufferedImage getBufImg() {
		return bufImg;
	}

	public ShowingCanvas(PreparingCanvas prep) {
		this.prep = prep;
		Dimension d = prep.getPreferredSize();
		this.setSize(d);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		
		bufImg = new BufferedImage(prep.getPreferredSize().width, prep.getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
	     prep.paint(bufImg.getGraphics());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bufImg, 0, 0, null);
	}
}
