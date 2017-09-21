package fractal;

import java.nio.file.Path;
import java.io.IOException;
import java.io.FileOutputStream;


public final class ImageUtils {

	private ImageUtils() {}

	public static PPMImage readImage(Path filename) { 
		return null;
	}

	public static void writeImage(Path filename, PPMImage img) throws IOException {
		FileOutputStream fout = new FileOutputStream(filename.toString());
		// write header
		fout.write(("P6\n" + img.getWidth() + " " + img.getHeight() + "\n255\n").getBytes());
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				RGBColour p = img.getPixelColour(x,y);
				fout.write(new byte[] { p.getR(), p.getG(), p.getB() });
			}
		}
		fout.close();
	}

}
