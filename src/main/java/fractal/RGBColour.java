package fractal;

public class RGBColour {

	private final byte _r;
	private final byte _g;
	private final byte _b;

	public RGBColour(byte r, byte g, byte b) {
		_r = r;
		_g = g;
		_b = b;
	}

	public byte getR() { return _r; }
	public byte getG() { return _g; }
	public byte getB() { return _b; }
}
