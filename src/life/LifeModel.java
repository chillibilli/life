package life;

interface LifeModel {
	public void clear();
	
	public void set(int r, int c, int v);
	public byte get(int r, int c);
	
	public int nRows();
	public int nCols();
	
	public void step();
}
