package life;

import java.util.Random;

public class DummyModel implements LifeModel {
	Random rnd;
	
	public DummyModel() {
		rnd = new Random();
	}
	
	@Override
	public void clear() {}

	@Override
	public void set(int r, int c, int v) {}

	@Override
	public byte get(int r, int c) {
		return (byte)rnd.nextInt(2);
	}

	@Override
	public int nRows() {
		return 100;
	}

	@Override
	public int nCols() {
		return 100;
	}

	@Override
	public void step() {}

	
}
