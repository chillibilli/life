package life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 391888201559212934L;
	private static final int CELL_HEIGHT = 6;
	private static final int CELL_WIDTH = 6;
	
	private LifeModel m;
	
	private Image[] cellImage;
	private Image dead;
	private Image alive;
	
	
	public DisplayPanel() {
		cellImage = new BufferedImage[2];
		dead = cellImage[0] = emptyCellImage();
		alive = cellImage[1] = filledCellImage();
	}
	
	
	public void setModel(LifeModel m) {
		this.m = m;
		setPreferredSize(new Dimension(m.nCols() * CELL_WIDTH, m.nRows() * CELL_HEIGHT));
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < m.nRows(); i++) {
			for (int j = 0; j < m.nCols(); j++) {
				g.drawImage(cellImage[m.get(i, j)], i * CELL_HEIGHT, j * CELL_WIDTH, null);
			}
		}

	}
	
	static Image emptyCellImage() {
		BufferedImage image = new BufferedImage(CELL_WIDTH, CELL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fill(new Rectangle(0,0,CELL_WIDTH, CELL_HEIGHT));
	    return image;
	}
	
	static Image filledCellImage() {
		BufferedImage image = new BufferedImage(CELL_WIDTH, CELL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fill(new Rectangle(0,0,CELL_WIDTH, CELL_HEIGHT));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
	    g.fill(new Ellipse2D.Double(0, 0, CELL_WIDTH - 1, CELL_HEIGHT - 1));
	    return image;
	}
	
}
