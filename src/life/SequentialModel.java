package life;

class SequentialModel implements LifeModel {
	private byte[][] cells;
	private byte[][] old;
	private int nRows;
	private int nCols;
	
	SequentialModel(int nRows, int nCols) {
		this.nRows = nRows;
		this.nCols = nCols;
		cells = new byte[nRows][nCols];
		old = new byte[nRows][nCols];
	}
	
	@Override
	public synchronized void clear() {
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				cells[i][j] = 0;
			}
		}
		
	}

	@Override
	public void set(int r, int c, int v) {
		cells[r][c] = (byte)v;
		
	}

	@Override
	public byte get(int r, int c) {
		return cells[r][c];
	}

	@Override
	public int nRows() {
		return nRows;
	}

	@Override
	public int nCols() {
		return nCols;
	}

	@Override
	public synchronized void step() {
		// current state becomes old
		byte[][] tmp = old;
		old = cells;
		cells = tmp;
		
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				int around = countAround(i, j);
				if (old[i][j] == 0) {
					// birth
					if (around == 3) 
						cells[i][j] = 1;
					else
						cells[i][j] = old[i][j];
				}
				else {
					// death
					if (around < 2 || around > 3)
						cells[i][j] = 0;
					else
						cells[i][j] = old[i][j];
				}
			}
		}
		
	}

	private int countAround(int r, int c) {
		return
				old[prevRow(r)][prevCol(c)] +
				old[prevRow(r)][c] +
				old[prevRow(r)][nextCol(c)] +
				old[r][prevCol(c)] +
				old[r][nextCol(c)] +
				old[nextRow(r)][prevCol(c)] +
				old[nextRow(r)][c] +
				old[nextRow(r)][nextCol(c)];
	}
	
	private int nextRow(int r) { return (++r >= nRows ? 0 : r); }
	private int prevRow(int r) { return (--r < 0 ? nRows - 1 : r); }
	private int nextCol(int c) { return (++c >= nCols ? 0 : c); }
	private int prevCol(int c) { return (--c < 0 ? nCols -1 : c); }
}
