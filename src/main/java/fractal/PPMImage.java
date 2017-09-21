package fractal;

import java.lang.RuntimeException;
import java.lang.IllegalArgumentException;

public class PPMImage {

	private final int _width;
	private final int _height;
	private final RGBColour[] _buff;

	public PPMImage(RGBColour[] buff, int width, int height) {
		if(buff == null)
			throw new RuntimeException("Given buffer must not be null");
		if(width <= 0 || height <= 0)
			throw new RuntimeException("Given image dimensions invalid: " + width + "x" + height);
		if(width * height != buff.length)
			throw new RuntimeException("Width and height do not match buffer dimensions: " + width + "x" + height + " buff " + buff.length);

		_width = width;
		_height = height;
		_buff = buff;
	}

	public int getWidth() { return _width; }
	public int getHeight() { return _height; }

	public RGBColour getPixelColour(int x, int y) {
		if(x >= _width || y >= _height)
			throw new IllegalArgumentException("Given image coordinates out of range: " + x + "x" + y);

		return _buff[y * _width + x];
	}

	public void setPixelColour(int x, int y, RGBColour c) {
		if(x >= _width || y >= _height)
			throw new IllegalArgumentException("Given image coordinates out of range: " + x + "x" + y);
		if(c == null) throw new IllegalArgumentException("Given colour must not be null");

		_buff[y * _width + x] = c;
	}
}
