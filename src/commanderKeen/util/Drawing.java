package commanderKeen.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Drawing {

	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		// Set the font
		g.setFont(font);
		// Draw the String
		g.drawString(text, x, y);
	}

	public void drawCenteredRect(Graphics g, Rectangle rect, int width, int height, Color color) {
		Color defaultColor = g.getColor();
		g.setColor(color);
		// Determine the X coordinate for the text
		int x = rect.x + rect.width /2 - width /2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + rect.height /2 - height /2;
		// Draw the Rectangle
		g.fillRect(x, y, width, height);

		g.setColor(defaultColor);
	}

}
