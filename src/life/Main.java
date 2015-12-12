package life;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	private JFrame frame;
	private DisplayPanel displayPanel;
	private JButton btnRun;
	private JButton btnStep;
	private JButton btnClear;
	private JButton btnEdit;
	private JButton btnRandom;
	
	private LifeModel m;
	private ViewElements view;
	private Controller ctr;
	
	public class ViewElements {
		public JFrame frame;
		public DisplayPanel displayPanel;
		public JButton btnRun;
		public JButton btnStep;
		public JButton btnClear;
		public JButton btnEdit;
		public JButton btnRandom;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		setSystemLookAndFeel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		
		view = new ViewElements();
		view.frame = frame;
		view.displayPanel = displayPanel;
		view.btnRun = btnRun;
		view.btnClear = btnClear;
		view.btnEdit = btnEdit;
		view.btnRandom = btnRandom;
		
		m = new SequentialModel(100, 100);
		//m = new DummyModel();
		displayPanel.setModel(m);
		
		ctr = Controller.instance;
		ctr.init(m, view);

		// test config
		m.set(10, 10, 1);
		m.set(10, 11, 1);
		m.set(10, 12, 1);
		m.set(9, 12, 1);
		m.set(8, 11, 1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel buttonsPanel = new JPanel();
		frame.getContentPane().add(buttonsPanel, BorderLayout.EAST);
		GridBagLayout gbl_buttonsPanel = new GridBagLayout();
		gbl_buttonsPanel.columnWidths = new int[]{51, 0};
		gbl_buttonsPanel.rowHeights = new int[]{23, 23, 0, 0, 0, 0, 0, 0};
		gbl_buttonsPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonsPanel.setLayout(gbl_buttonsPanel);
		
		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				ctr.toggleRun(); 
			}
		});
		GridBagConstraints gbc_btnRun = new GridBagConstraints();
		gbc_btnRun.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRun.anchor = GridBagConstraints.NORTH;
		gbc_btnRun.insets = new Insets(0, 0, 5, 0);
		gbc_btnRun.gridx = 0;
		gbc_btnRun.gridy = 0;
		buttonsPanel.add(btnRun, gbc_btnRun);
		
		btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctr.step();
			}
		});
		GridBagConstraints gbc_btnStep = new GridBagConstraints();
		gbc_btnStep.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStep.insets = new Insets(0, 0, 5, 0);
		gbc_btnStep.gridx = 0;
		gbc_btnStep.gridy = 1;
		buttonsPanel.add(btnStep, gbc_btnStep);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 2;
		buttonsPanel.add(verticalStrut, gbc_verticalStrut);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctr.clear();
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 0);
		gbc_btnClear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClear.gridx = 0;
		gbc_btnClear.gridy = 4;
		buttonsPanel.add(btnClear, gbc_btnClear);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctr.edit();
			}
		});
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 5;
		buttonsPanel.add(btnEdit, gbc_btnEdit);
		
		btnRandom = new JButton("Random");
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctr.fillRandom();
			}
		});
		GridBagConstraints gbc_btnRandom = new GridBagConstraints();
		gbc_btnRandom.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRandom.gridx = 0;
		gbc_btnRandom.gridy = 6;
		buttonsPanel.add(btnRandom, gbc_btnRandom);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		displayPanel = new DisplayPanel();
		displayPanel.setBackground(Color.WHITE);
		scrollPane.setViewportView(displayPanel);
	}

	private static void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
