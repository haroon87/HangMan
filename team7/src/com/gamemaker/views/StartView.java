package com.gamemaker.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.MakerController;
import com.gamemaker.helper.Dimensions;
import com.gamemaker.main.GameMaker;
import com.gamemaker.models.GameModel;

import javax.swing.JTabbedPane;


/**
 *  This class displays the view after user logs in. If the user is GameMaker, the game maker view is displayed
 *  If the user is just a game player, the gameplay view is displayed
 */

@SuppressWarnings("serial")
public class StartView extends JFrame {
	private final MakerController makerController;
	private final JPanel contentPane;

	JLayeredPane layeredPane;

	private final JTextField txtNewGameField;
	private final JLabel lblBackground1;
	private final JLabel lblEditviewAnexisting;
	private final JComboBox savedGameList;
	private final String selectedGameName = "";
	private final JScrollPane imageScrollPane;
	private final JPanel imagePanel;
	private JButton imageBtn;
	private String selectedIcon;
	private JCheckBox TimerSelect;
	private final JButton btnGo;
	private final JButton btnPlay;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblEnterA;
	private JLabel lblClickOn;
	private JLabel lblEnterThe;
	private JLabel lblDragAnd;
	private JLabel lblSelectAn;
	private JLabel lblSelectAn_1;
	private JLabel lblAssociateA;
	private JLabel lblAssociateThe;
	private JLabel lblClickThe;
	private JLabel lblRepeatStep;
	private JLabel lblClickOn_1;
	private JLabel lblSelectGame;
	private JLabel lblClickOn_2;
	private JLabel lblSelectPreviously;
	private JLabel lblCreateNew;
	private JLabel lblClickOn_3;
	private JLabel lblClickOn_4;
	private JLabel lblSelectGame_1;
	private JLabel lblClickOn_5;
	private JLabel lblOnThe;

	public StartView(MakerController makerController) {
		this.makerController = makerController;
		setTitle("New Game/Load game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Dimensions.START_WINDOW_WIDTH, Dimensions.START_WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		// Added Panel to saveand load game
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, Dimensions.START_WINDOW_PANEL_WIDTH, Dimensions.START_WINDOW_PANEL_HEIGHT);
		layeredPane.add(panel);
		panel.setLayout(null);

		JLabel lblStartingMakingA = new JLabel("Make a New Game");
		lblStartingMakingA.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStartingMakingA.setBounds(Dimensions.START_WINDOW_MAKING_LABEL_X, Dimensions.START_WINDOW_MAKING_LABEL_Y,
				Dimensions.START_WINDOW_MAKING_LABEL_WIDTH, Dimensions.START_WINDOW_MAKING_LABEL_HEIGHT);
		panel.add(lblStartingMakingA);

		JLabel lblNewGame = new JLabel("Game Name:");
		lblNewGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewGame.setBounds(Dimensions.START_WINDOW_GAME_LABEL_X, Dimensions.START_WINDOW_GAME_LABEL_Y,
				Dimensions.START_WINDOW_GAME_LABEL_WIDTH, Dimensions.START_WINDOW_GAME_LABEL_HEIGHT);
		lblNewGame.setVisible(true);
		panel.add(lblNewGame);
		
		TimerSelect = new JCheckBox();
		TimerSelect.setText("Game Timer");
		TimerSelect.setBounds(50,140,100,50);
		TimerSelect.setVisible(true);
		TimerSelect.setBackground(Color.LIGHT_GRAY);
		panel.add(TimerSelect);
		
		txtNewGameField = new JTextField();
		txtNewGameField.setBounds(Dimensions.START_WINDOW_GAME_TEXTFIELD_X, Dimensions.START_WINDOW_GAME_TEXTFIELD_Y,
				Dimensions.START_WINDOW_GAME_TEXTFIELD_WIDTH, Dimensions.START_WINDOW_GAME_TEXTFIELD_HEIGHT);
		txtNewGameField.setVisible(true);
		panel.add(txtNewGameField);
		txtNewGameField.setColumns(10);

		lblBackground1 = new JLabel("Background");
		lblBackground1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBackground1.setBounds(Dimensions.START_WINDOW_GAME_BACKGROUND_LABEL_X,
				Dimensions.START_WINDOW_GAME_BACKGROUND_LABEL_Y, Dimensions.START_WINDOW_GAME_BACKGROUND_LABEL_WIDTH,
				Dimensions.START_WINDOW_GAME_BACKGROUND_LABEL_HEIGHT);
		lblBackground1.setVisible(true);
		panel.add(lblBackground1);

		imageScrollPane = new JScrollPane();
		imagePanel = new JPanel();
		imagePanel.setLayout(new FlowLayout());
		JPanel jp = new JPanel();
		final List<String> fileNamesImgs = new ArrayList<String>();
		if (true) {
			URL urlString = this.getClass().getClassLoader().getResource("backgrounds.jar");
			ZipInputStream zip;
			try {
				zip = new ZipInputStream(urlString.openStream());
				ZipEntry ze = null;

				while ((ze = zip.getNextEntry()) != null) {
					String entryName = ze.getName();
					if (entryName.endsWith(".jpg") || (entryName.endsWith(".png"))) {
						fileNamesImgs.add("/" + entryName);
					}
				}
			} catch (IOException e1) {
			}
		}

		
		String[] fileListString = new String[fileNamesImgs.size()];
		fileListString = fileNamesImgs.toArray(fileListString);
		

		for (int j = 0; j < fileListString.length; j++) {
		
			URL imageUrl = this.getClass().getResource(fileListString[j]);
			ImageIcon icon12 = new ImageIcon(imageUrl);
			
			/*
			 * Check for images to be of proportion to be represented as with a
			 * size of 70*70. Also maintaining the aspect ratio of these images.
			 * Resize only to be applied if either the width or height is
			 * greater than 70 pixels.
			 */
			if ((icon12.getIconWidth() > 70) || (icon12.getIconHeight() > 70)) {
				Image resizedImage = icon12.getImage();
				if (icon12.getIconWidth() > icon12.getIconHeight()) {
					double imgWidth = icon12.getIconWidth();
					double x = (70 / imgWidth);
					double y = icon12.getIconHeight() * x;
					resizedImage = resizedImage.getScaledInstance(70, (int) y, Image.SCALE_SMOOTH);
				} else {
					double imgHeight = icon12.getIconHeight();
					double x = (70 / imgHeight);
					double y = icon12.getIconWidth() * x;
					resizedImage = resizedImage.getScaledInstance((int) y, 70, Image.SCALE_SMOOTH);
				}
				icon12 = new ImageIcon(resizedImage);
			}
			icon12.setDescription(fileListString[j]);
			imageBtn = new JButton(icon12);
			imageBtn.addActionListener(new SelectAdapter());
			imagePanel.add(imageBtn);
			imagePanel.repaint();
		}
		imageScrollPane.setViewportView(imagePanel);
		imageScrollPane.setBounds(Dimensions.START_WINDOW_IMAGE_SCROLLPANE_LABEL_X,
				Dimensions.START_WINDOW_IMAGE_SCROLLPANE_LABEL_Y, Dimensions.START_WINDOW_IMAGE_SCROLLPANE_LABEL_WIDTH,
				Dimensions.START_WINDOW_IMAGE_SCROLLPANE_LABEL_HEIGHT);
		panel.add(imageScrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, Dimensions.START_WINDOW_SEPARATOR_Y, Dimensions.START_WINDOW_SEPARATOR_WIDTH, 1);
		panel.add(separator);

		lblEditviewAnexisting = new JLabel("Play/Edit an Existing Game");
		lblEditviewAnexisting.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditviewAnexisting.setBounds(Dimensions.START_WINDOW_LOADING_LABEL_X,
				Dimensions.START_WINDOW_LOADING_LABEL_Y, Dimensions.START_WINDOW_LOADING_LABEL_WIDTH,
				Dimensions.START_WINDOW_LOADING_LABEL_HEIGHT);
		panel.add(lblEditviewAnexisting);

		JLabel lblLoadGame = new JLabel("Load Game");
		lblLoadGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoadGame.setBounds(Dimensions.START_WINDOW_LOAD_LABEL_X, Dimensions.START_WINDOW_LOAD_LABEL_Y,
				Dimensions.START_WINDOW_LOAD_LABEL_WIDTH, Dimensions.START_WINDOW_LOAD_LABEL_HEIGHT);
		panel.add(lblLoadGame);

		savedGameList = new JComboBox();
		savedGameList.setModel(new DefaultComboBoxModel(getListOfGames()));
		savedGameList.setBounds(Dimensions.START_WINDOW_LOAD_BOX_X, Dimensions.START_WINDOW_LOAD_BOX_Y,
				Dimensions.START_WINDOW_LOAD_BOX_WIDTH, Dimensions.START_WINDOW_LOAD_BOX_HEIGHT);
		savedGameList.addActionListener(new SelectSavedGamesListener());
		savedGameList.setSelectedIndex(-1);
		panel.add(savedGameList);

		btnGo = new JButton("Go");
		btnGo.setBounds(Dimensions.START_WINDOW_GO_BUTTON_X, Dimensions.START_WINDOW_GO_BUTTON_Y,
				Dimensions.START_WINDOW_GO_BUTTON_WIDTH, Dimensions.START_WINDOW_GO_BUTTON_HEIGHT);
		btnGo.addActionListener(new GoBtnListener());
		panel.add(btnGo);

		btnPlay = new JButton("Play");
		btnPlay.setBounds(Dimensions.START_WINDOW_PLAY_BUTTON_X, Dimensions.START_WINDOW_PLAY_BUTTON_Y,
				Dimensions.START_WINDOW_GO_BUTTON_WIDTH, Dimensions.START_WINDOW_GO_BUTTON_HEIGHT);
		btnPlay.addActionListener(new PlayBtnListener());
		panel.add(btnPlay);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(561, 0, 652, 400);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHelp = new JLabel("User Guide");
		lblHelp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHelp.setBounds(310, 11, 100, 32);
		panel_1.add(lblHelp);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 48, 632, 341);
		panel_1.add(tabbedPane);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("New Game", null, panel_4, null);
		panel_4.setLayout(null);
		
		lblEnterA = new JLabel("1. Enter a game name in the \"New Game\" field, and select a background");
		lblEnterA.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEnterA.setBounds(10, 11, 607, 24);
		panel_4.add(lblEnterA);
		
		lblClickOn = new JLabel("2. Click on GO");
		lblClickOn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblClickOn.setBounds(10, 33, 607, 29);
		panel_4.add(lblClickOn);
		
		lblEnterThe = new JLabel("3. Enter the sprite name in the \"Game Maker\"");
		lblEnterThe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEnterThe.setBounds(10, 59, 607, 24);
		panel_4.add(lblEnterThe);
		
		lblDragAnd = new JLabel("4. Drag and drop the respective image(s) onto the panel on the right");
		lblDragAnd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDragAnd.setBounds(10, 83, 607, 24);
		panel_4.add(lblDragAnd);
		
		lblSelectAn = new JLabel("5. Select an \"Event Type\" if you want to associate any events with the sprite");
		lblSelectAn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectAn.setBounds(10, 109, 607, 24);
		panel_4.add(lblSelectAn);
		
		lblSelectAn_1 = new JLabel("6. Select an \"Action Type\" if you want to associate any actions with the selected event");
		lblSelectAn_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectAn_1.setBounds(10, 133, 607, 29);
		panel_4.add(lblSelectAn_1);
		
		lblAssociateA = new JLabel("7. Associate a  \"Game Condition\" with the Event-Action pair, default is Game Continue");
		lblAssociateA.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAssociateA.setBounds(10, 162, 607, 24);
		panel_4.add(lblAssociateA);
		
		lblAssociateThe = new JLabel("8. Associate the Action-Game Condition with a previously saved sprite in the game");
		lblAssociateThe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAssociateThe.setBounds(10, 188, 607, 24);
		panel_4.add(lblAssociateThe);
		
		lblClickThe = new JLabel("9. Click the Associate button");
		lblClickThe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblClickThe.setBounds(10, 216, 607, 24);
		panel_4.add(lblClickThe);
		
		lblRepeatStep = new JLabel("10. Repeat Step 5 to Step 9 for multiple Event-Action-Game Condition pairs for a sprite ");
		lblRepeatStep.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRepeatStep.setBounds(10, 239, 607, 24);
		panel_4.add(lblRepeatStep);
		
		lblClickOn_1 = new JLabel("11. Click on SAVE, repeat for new sprites");
		lblClickOn_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblClickOn_1.setBounds(10, 268, 607, 24);
		panel_4.add(lblClickOn_1);
		
		lblClickOn_4 = new JLabel("12. Click on Test to test the game");
		lblClickOn_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblClickOn_4.setBounds(10, 293, 607, 14);
		panel_4.add(lblClickOn_4);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Load and Edit Game", null, panel_3, null);
		panel_3.setLayout(null);
		
		lblSelectGame = new JLabel("1. Select game from drop down");
		lblSelectGame.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectGame.setBounds(10, 11, 607, 24);
		panel_3.add(lblSelectGame);
		
		lblClickOn_2 = new JLabel("2. Click on Go");
		lblClickOn_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblClickOn_2.setBounds(10, 34, 607, 24);
		panel_3.add(lblClickOn_2);
		
		lblSelectPreviously = new JLabel("3. Select previously saved sprites and click Load Sprite to edit");
		lblSelectPreviously.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectPreviously.setBounds(10, 61, 607, 24);
		panel_3.add(lblSelectPreviously);
		
		lblCreateNew = new JLabel("4. Create new sprites and follow steps from New Game tab");
		lblCreateNew.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCreateNew.setBounds(10, 86, 607, 24);
		panel_3.add(lblCreateNew);
		
		lblClickOn_3 = new JLabel("5. Click on Test to test the game");
		lblClickOn_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblClickOn_3.setBounds(10, 111, 607, 24);
		panel_3.add(lblClickOn_3);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Play Game", null, panel_2, null);
		panel_2.setLayout(null);
		
		lblSelectGame_1 = new JLabel("1. Select Game from the drop down");
		lblSelectGame_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectGame_1.setBounds(10, 11, 607, 25);
		panel_2.add(lblSelectGame_1);
		
		lblClickOn_5 = new JLabel("2. Click on Play");
		lblClickOn_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblClickOn_5.setBounds(10, 38, 607, 25);
		panel_2.add(lblClickOn_5);
		
		lblOnThe = new JLabel("3. On the play window hit play to start playing");
		lblOnThe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblOnThe.setBounds(10, 66, 607, 25);
		panel_2.add(lblOnThe);

		setVisible(true);
		setSize(1239, 458);
	}

	public static String[] fileList(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String[] lis = new String[listOfFiles.length];
		for (int i = 0; i < listOfFiles.length; i++) {
			lis[i] = listOfFiles[i].toString();
		}
		return lis;
	}

	public class GoBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			 if(TimerSelect.isSelected())
			 {	
				 
				 makerController.getGameModel().setTimer(2);
			 }
			 else
			 {	
				 makerController.getGameModel().setTimer(3);
			 }
			if (savedGameList.getSelectedIndex() == -1) {
				makerController.getGameModel().setBkgURL(selectedIcon);
				makerController.gameMakerWindowIsRequested(txtNewGameField.getText(), false);
				makerController.getGameModel().setName(txtNewGameField.getText());
			} else {
				makerController.gameMakerWindowIsRequested(savedGameList.getSelectedItem().toString(), true);
				String parts[] = savedGameList.getSelectedItem().toString().split("_");
				String baseGameName = parts[0];
				int version = GameMaker.databaseReader.getVersionNo(GameMaker.loggedInUser.getUserId(), baseGameName);
				makerController.getGameModel().setName(baseGameName+"_"+version++);
			}
		}

	}

	public class PlayBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			makerController.gamePlayWindowIsRequested(savedGameList.getSelectedItem().toString());
		}

	}

	private String[] getListOfGames() {
				return makerController.getGamesList();
	}

	public class SelectSavedGamesListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		}
	}

	// selection helper for fetching image url from the button containing
	// the image in the jscrollpane
	public class SelectAdapter implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			JButton button = (JButton) e.getSource();
			String desc = ((ImageIcon) button.getIcon()).getDescription();
			selectedIcon = desc;

		}

	}
}
