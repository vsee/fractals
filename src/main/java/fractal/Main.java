package fractal;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Running fractals ...");
		
		int w = 800;
		int h = 800;
		PPMImage redImage = generateRedImage(w,h);
		PPMImage mcImage = generateMultiColourImage(w, h);
		PPMImage fractalImage = generateFractal(w, h);
		try {
			ImageUtils.writeImage(Paths.get("allRed.ppm"), redImage);
			ImageUtils.writeImage(Paths.get("mcImage.ppm"), mcImage);
			ImageUtils.writeImage(Paths.get("fractal.ppm"), fractalImage);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Writing image file failed.");
		}
	}

	private static PPMImage generateFractal(int w, int h) {
		RGBColour[] buff = new RGBColour[w*h];
		for(int y = 0; y < h; y++) {
			double imag = mapToY(y / (double)h);
			for(int x = 0; x < w; x++) {
				double real = mapToX(x / (double)w);
				
				ComplexNumber c = new ComplexNumber(real,imag);
				int n;
				ComplexNumber z = new ComplexNumber(0,0);
				for(n = 1; n <= iterMax; n++) {
					z = (z.multiply(z)).add(c);
					if(z.abs() > 2) break;
				}
				
				int channel = mapToChannel(n);
				buff[y * w + x] = new RGBColour((byte)channel,
						(byte)channel,(byte)channel);
			}
		}
		
		return new PPMImage(buff, w, h);
	}

	private static int mapToChannel(int n) {
		final int cmin = 0;
		final int cmax = 255;
		return (cmax - cmin) / (iterMax - 0) * n;
	}

	private static int iterMax = 32;
	private static double zoom = 0.00;
	private static double moveX = 0;
	private static double moveY = 0;

	private static double mapToY(double x) {
		final double ymin = -1.5 + zoom + moveY;
		final double ymax = 1.5 - zoom + moveY;
		return (ymax - ymin) * x + ymin;
	}

	private static double mapToX(double x) {
		final double xmin = -2 + zoom + moveX;
		final double xmax = 1 - zoom + moveY;
		return (xmax - xmin) * x + xmin;
	}

	private static PPMImage generateMultiColourImage(int w, int h) {
		RGBColour[] buff = new RGBColour[w*h];
		for(int y = 0; y < h; y++) {
			float yInter = y / 255f;
			for(int x = 0; x < w; x++) {
				float xInter = x / 255f;
				buff[y * w + x] = new RGBColour((byte)(255 * xInter),
						(byte)0,(byte)(255 * yInter));
			}
		}
		
		return new PPMImage(buff, w, h);
	}
	
	private static PPMImage generateRedImage(int w, int h) {
		RGBColour[] buff = new RGBColour[w*h];
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				buff[y * w + x] = new RGBColour((byte)255,(byte)0,(byte)0);
			}
		}
		
		return new PPMImage(buff, w, h);

	}
	
}
