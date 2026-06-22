package main;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class UtilityTool {
	//improving the rending efficiency by scaling before the game loop
	 public BufferedImage scaleImage(BufferedImage src, int width, int height) {
	        // preserving transparency
	        BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	        Graphics2D g2 = dst.createGraphics();
	        g2.setComposite(AlphaComposite.Src);

	        //  pixel-art: nearest-neighbor
	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_OFF);
	        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
	                            RenderingHints.VALUE_RENDER_SPEED);

	        g2.drawImage(src, 0, 0, width, height, null);
	        g2.dispose();

	        return dst;
	    }
	}

