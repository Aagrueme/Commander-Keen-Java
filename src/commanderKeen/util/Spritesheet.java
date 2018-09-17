package commanderKeen.util;

import java.awt.image.BufferedImage;

public class Spritesheet {

	private BufferedImage sprite;
	private int row;
	private int col;
	private int width;
	private int height;
	private int x;
	private int y;
	private boolean hasXY;

	public Spritesheet(BufferedImage sprite, int row, int col, int width, int height) {
		super();
		this.sprite = sprite;
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;
	}

	public Spritesheet(BufferedImage sprite, int row, int col, int width, int height, int x, int y) {
		super();
		this.sprite = sprite;
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;
		hasXY = true;
		this.x = x;
		this.y = y;
	}

	public BufferedImage getTexture(int id) {
		if (hasXY) {
			int y = (id / col);
			int x = (id % col);
			if (y > row) {
				throw new IndexOutOfBoundsException("Your row or your col is too big!");
			}
			return sprite.getSubimage(x * width, y * height, width, height);
		} else {
			int y = (id / col);
			int x = (id % col);
			if (y > row) {
				throw new IndexOutOfBoundsException("Your row or your col is too big!");
			}
			return sprite.getSubimage(x * width + this.x, y * height + this.y, width, height);
		}
	}

	public BufferedImage getImage(int row, int col) {
		if (hasXY) {
			if (row > this.row || col > this.col) {
				throw new IndexOutOfBoundsException("Your row or your col is too big!");
			}
			return sprite.getSubimage(col * width, row * height, width, height);
		}else {
			if (row > this.row || col > this.col) {
				throw new IndexOutOfBoundsException("Your row or your col is too big!");
			}
			return sprite.getSubimage(col * width + this.x, row * height + this.x, width, height);
		}
	}
}
