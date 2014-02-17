package fi.seco.util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

public class GraphicsUtil {
	public static enum XLoc {
		LEFT, RIGHT, CENTER
	}

	public static enum YLoc {
		TOP, BOTTOM, CENTER
	}

	public static void drawString(Graphics2D g, int tox, int toy, XLoc xloc, YLoc yloc, String message) {
		Font f = g.getFont();
		// Measure the font and the message
		FontRenderContext frc = g.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, frc);
		LineMetrics metrics = f.getLineMetrics(message, frc);
		float width = (float) bounds.getWidth(); // The width of our text
		float lineheight = metrics.getHeight(); // Total line height
		float ascent = metrics.getAscent(); // Top of text to baseline
		float x = tox;
		float y = toy + ascent;
		switch (xloc) {
		case LEFT:
			break;
		case RIGHT:
			x = x - width;
			break;
		case CENTER:
			x = x - width / 2;
			break;
		}
		switch (yloc) {
		case TOP:
			break;
		case BOTTOM:
			y = y - lineheight;
			break;
		case CENTER:
			y = y - lineheight / 2;
			break;
		}
		g.drawString(message, x, y);
	}

}
