package life;

import java.util.Random;

public class Controller {
	public static final Controller instance = new Controller();
	
	private LifeModel model;
	private Main.ViewElements view;
	
	private volatile boolean isRunning = false;
	
	public synchronized void init(LifeModel model, Main.ViewElements view) {
		this.model = model;
		this.view = view;
	}
	
	public void toggleRun() {
		if (isRunning) {
			isRunning = false;
		}
		else {
			isRunning = true;
			run();
		}
	}

	private void run() {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (isRunning) {
					step();
				}
			}
		};
		t.start();
	}
	
	public void step() {
		model.step();
		view.displayPanel.repaint();
	}
	
	
	
	public void clear() {
		model.clear();
		view.displayPanel.repaint();
	}

	public void fillRandom() {
		Random rnd = new Random();
		double density = 0.3;
		model.clear();
		for (int i = 0; i < model.nRows(); i++) {
			for (int j = 0; j < model.nCols(); j++)
				model.set(i, j, (rnd.nextDouble() < density ? 1 : 0));
		}
		view.displayPanel.repaint();
	}

	public void edit() {
		// TODO Auto-generated method stub
		
	}

}
