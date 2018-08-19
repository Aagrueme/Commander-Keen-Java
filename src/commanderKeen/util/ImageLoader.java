package commanderKeen.util;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static Image loadImage(String path) {
		try {
			return ImageIO.read(ResourceLoader.load(path));
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
			System.exit(1);
		}
		return null;

	}

}
