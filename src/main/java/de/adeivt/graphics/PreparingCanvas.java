package de.adeivt.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

class PreparingCanvas extends Canvas {
	
	public static class ColoredLine {
		public int x1;
		public int y1;
		public int x2;
		public int y2;
		public Color c;
		public ColoredLine(int x1, int y1, int x2, int y2, Color c) {
			this.x1=x1;
			this.y1=y1;
			this.x2 = x2;
			this.y2=y2;
			this.c=c;
		}
	}
	private List<ColoredLine> lineList = new ArrayList<>();
	private Color currColor;
	private Color bgColor;
	public PreparingCanvas (int width,  int height,  Color bgColor) {
       setBackground (bgColor);
       setSize(width, height);
       Dimension d = new Dimension(width, height);
       this.setMinimumSize(d);
       this.setMaximumSize(d);
       this.setPreferredSize(d);
       this.bgColor = bgColor;
       this.currColor = Color.BLACK;
       // ^^^^^keine Freiheit: es ist FEST:
    }
    
    public void setLine(int x1, int y1, int x2, int y2, Color c) {
    	this.lineList.add(new ColoredLine(x1, y1, x2, y2, c));
    }
    public void setLine(int x1, int y1, int x2, int y2) {
    	this.setLine(x1,  y1, x2, y2, this.currColor);
    }
    public void setPoint(int x, int y, Color c) {
    	this.setLine(x,  y,  x,  y, c);
    }
    public void setPoint(int x, int y) {
    	this.setLine(x,  y,  x,  y);
    }
    public void setColor(Color c) {
    	this.currColor = c;
    }
    public void reset() {
    	this.lineList = new ArrayList<>();
    	this.repaint();
    }
    @Override
    public void paint(Graphics g) {
    	g.setColor(this.bgColor);
    	g.fillRect(0, 0, this.getPreferredSize().width, this.getPreferredSize().height);
    	for(ColoredLine cl: this.lineList) {
    		g.setColor(cl.c);
    		g.drawLine(cl.x1, cl.y1, cl.x2, cl.y2);
    	}
    }
}