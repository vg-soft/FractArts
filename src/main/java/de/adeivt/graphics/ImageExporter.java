package de.adeivt.graphics;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import de.vg_soft.IoUtil;

public class ImageExporter {
	RenderedImage im;

	public ImageExporter(RenderedImage im) {
		this.im = im;
	}

	public void export() throws IOException {
		File fileToExport = dialogForFileToExport();
		if(fileToExport != null) {
			ImageIO.write(im, "png", fileToExport);
		}
	}

	private File dialogForFileToExport() {
		JFileChooser jfc = new JFileChooser();
	    int retrival = jfc.showDialog(null, "Export to png, jpg or bmp");
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	    	File f = jfc.getSelectedFile();
			String fNameLc = f.getAbsolutePath().toLowerCase();
			if(!fNameLc.endsWith(".png") && !fNameLc.endsWith(".jpg") && !fNameLc.endsWith(".bmp")) {
				return new File(f.getAbsolutePath() + ".png");
			}
			return f;
	    }
		return null;
	}
}
