package com.gamemaker.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.gamemaker.controllers.DisplayController;
import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.ScoreCalculation;
import com.gamemaker.helper.Dimensions;
import com.gamemaker.main.GameMaker;
//import com.gamemaker.helper.OsUtility;
import com.gamemaker.models.Sprite;

/**
 * This class displays the Game play view. It is displayed during game is being played
 *
 */
public class GamePlayView extends JFrame {

	private JButton playButton;
	private final JButton stopButton;
	private JComboBox saveLoadCB;
	
	private final JLabel msgLabel;
	private JLabel scoreLabel;
	
	private JLabel NameLabel;
	
	private JLabel TimeLabel;

	public JPanel contentPane;
	public JPanel panelLeft;
		private final JLayeredPane layeredPane;
	private List<Sprite> spritesData;
	private ArrayList<Sprite> dynamicSprites;
	private String gameName;
	private GamePanel gamePanelObj;
	
	public boolean firsttime = false;
	public int gwidth=1;
	public int gheight=1;
	double dwidth=1;
	double dheight=1;
	
	private JButton getStopButton() {
		return stopButton;
	}

	/*
	 * public GamePlayView(ActionListener spriteButtonListener, ActionListener
	 * playListener, ActionListener saveListener , ActionListener loadListener,
	 * ActionListener stopListener, ActionListener removeListener)
	 */
	private DisplayController controller = null;

	public GamePlayView(DisplayController controller) {
		this.controller = controller;
		setTitle("GamePlay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH+250, Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		layeredPane = new JLayeredPane();
		panelLeft = new JPanel();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		gamePanelObj = new GamePanel();
		gamePanelObj.setBackground(Color.WHITE);
		gamePanelObj.setBounds(225, 0, Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH, Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);
		gamePanelObj.repaint();
		
		msgLabel = new JLabel();
		msgLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		msgLabel.setBounds(200,200,200,50);
		
		TimeLabel = new JLabel();
		TimeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		TimeLabel.setForeground(Color.white);
		TimeLabel.setBounds(50,15,200,50);
		
		NameLabel = new JLabel();
		NameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		NameLabel.setForeground(Color.white);
	
		
		
		scoreLabel = new JLabel();
		scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		scoreLabel.setForeground(Color.white);
		
		
		layeredPane.add(gamePanelObj, BorderLayout.NORTH);	
		
		panelLeft.setBackground(Color.LIGHT_GRAY);
		panelLeft.setBounds(10, 0, 200, 80);
		panelLeft.setLayout(new GridLayout(2, 1));
		layeredPane.add(panelLeft, BorderLayout.SOUTH);

		playButton = new JButton();
		playButton.setPreferredSize(new Dimension(80, 30));
		playButton.setText("Play");
		playButton.setFocusable(false);
		playButton.setEnabled(true);
		panelLeft.add(playButton);
		playButton.addActionListener(new playListener());

		stopButton = new JButton();
		stopButton.setPreferredSize(new Dimension(80, 30));
		stopButton.setText("Stop");
		stopButton.setFocusable(false);
		stopButton.setEnabled(true);
		panelLeft.add(stopButton);
		stopButton.addActionListener(new stopListener());
	
		addKeyListener(new StrokeAdapter());
		
		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				dwidth = e.getComponent().getSize().width;
				dheight = e.getComponent().getSize().height;
				Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH =(int) dwidth; 
				Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT=(int) dheight;
				
				if(firsttime==false)
				{
					gwidth=(int) dwidth;
					gheight=(int) dheight;
					firsttime=true;
				}
				
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {

			}

			@Override
			public void componentMoved(ComponentEvent arg0) {

			}

			@Override
			public void componentShown(ComponentEvent arg0) {

			}
		});

	}

	public JComboBox getSaveLoadCB() {
		return saveLoadCB;
	}

	public void setSaveLoadCB(JComboBox saveLoadCB) {
		this.saveLoadCB = saveLoadCB;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	public class GamePanel extends JPanel {

		private final BufferedImage image;
		private final Graphics2D bufferedGraphics;

		public GamePanel() {
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			bufferedGraphics = image.createGraphics();
			this.setBackground(Color.LIGHT_GRAY);
			this.setLayout(null);
		}

		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.removeAll();
			NameLabel.setBounds(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH-370,15,200,50);
			scoreLabel.setBounds(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH-370,35,200,50);
			this.add(msgLabel);
			this.add(scoreLabel);
			this.add(TimeLabel);
			setUserName(GameMaker.loggedInUser.getUserName());
			this.add(NameLabel);
			bufferedGraphics.setColor(Color.LIGHT_GRAY);
			paintSprites(spritesData);
			paintSprites(dynamicSprites);
			
			this.validate();
			this.repaint();
		}
		
		private void paintSprites(List<Sprite> sprites) {
			
			if (sprites != null) {
				for (int i = 0; i < sprites.size(); i++) {				
						
					//get all sprites with getresource
					URL imagePath = this.getClass().getResource(sprites.get(i).getImageName().toString());
					ImageIcon icon = new ImageIcon(imagePath);
					Image reImage = icon.getImage();
					reImage = reImage.getScaledInstance((int) (icon.getIconWidth()*dwidth/gwidth),
							(int) (icon.getIconHeight()*dheight/gheight), Image.SCALE_SMOOTH);
					icon=new ImageIcon(reImage);
					JLabel picLabel = new JLabel(icon);
					picLabel.setBounds((int) ((sprites.get(i).getX())*dwidth/gwidth), (int) ((sprites.get(i).getY())*dheight/gheight),(icon.getIconWidth()),
							 (icon.getIconHeight()));
					
						this.add(picLabel);
					
				}
				
				ImageIcon bgImageIcon = new ImageIcon(this.getClass().getResource(controller.getGameModel().getBkgURL()));
				Image reImage = bgImageIcon.getImage();
				reImage = reImage.getScaledInstance(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH, Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT, Image.SCALE_SMOOTH);
				bgImageIcon = new ImageIcon(reImage);
				JLabel bkgImageLabel = new JLabel(bgImageIcon);
				bkgImageLabel.setBounds(0, Dimensions.GAME_MAKER_RIGHT_PANEL_Y, Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH, Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);
				gamePanelObj.setBounds(225, 0, Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH, Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);
				this.add(bkgImageLabel);
			
				
			}
		}
	}

	public GamePanel getGamePanelObj() {
		return gamePanelObj;
	}

	public void setGamePanelObj(GamePanel gamePanelObj) {
		this.gamePanelObj = gamePanelObj;
	}

	
	public void paint(Graphics g) {
		super.paint(g);

		gamePanelObj.repaint();
	}

	public void draw(List<Sprite> spritesData) {
		this.spritesData = spritesData;
		repaint();
	}

	class playListener implements ActionListener {

		
		public void actionPerformed(ActionEvent arg0) {
			if (getPlayButton().getText().equals("Play")) {
				getPlayButton().setText("Pause");
				controller.playIsRequested();
				draw(spritesData);
				setMessage("");
			} else if (getPlayButton().getText().equals("Pause")) {
				getPlayButton().setText("Play");
				controller.pauseIsRequested();
			}
			getStopButton().setEnabled(true);
		}
	}

	class stopListener implements ActionListener {

		
		public void actionPerformed(ActionEvent arg0) {
			controller.stopIsRequested();
			getPlayButton().setEnabled(true);
			getPlayButton().setText("Play");
			setMessage("");
			getStopButton().setEnabled(false);
		}
	}
	
	public void setMessage(String message){
		msgLabel.setText(message);
	}
	
	public void setTime(String message){
		TimeLabel.setText(message);
	}
	
	public void setUserName(String name){
		NameLabel.setText(name);
	}
	
	
	public void setGameName(String name){
		this.gameName = name;
		this.setTitle(name);
	}
	

	
	
	

	public void setScore(String score) {

		scoreLabel.setText(score);
	}

	class StrokeAdapter extends KeyAdapter {
		
		public void keyPressed(KeyEvent ke) {
			controller.keyIsPressed(ke);
		}

		
		public void keyReleased(KeyEvent ke) {
			
		}
	}
}
