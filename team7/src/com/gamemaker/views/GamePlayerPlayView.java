package com.gamemaker.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gamemaker.controllers.MakerController;

/**
 * This class is the game player's game-play view. 
 * GameMaker's view is different from gameplayer's view
 *
 */
public class GamePlayerPlayView extends JFrame {

	private JPanel gameSelectPanel;
	private JLabel lblGame;
	private JComboBox gameComboBox;
	private JButton btnPlay;
	private MakerController makerController;

	public GamePlayerPlayView(MakerController makerController) {
		this.makerController = makerController;
		setTitle("Select Game");
		setBounds(0, 0, 570, 230);
		setVisible(true);

		getContentPane().setLayout(null);

		gameSelectPanel = new JPanel();
		gameSelectPanel.setBounds(10, 11, 532, 174);
		getContentPane().add(gameSelectPanel);
		gameSelectPanel.setLayout(null);

		lblGame = new JLabel("Game:");
		lblGame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGame.setBounds(99, 49, 67, 23);
		gameSelectPanel.add(lblGame);

		gameComboBox = new JComboBox();
		gameComboBox.setModel(new DefaultComboBoxModel(getListOfGames()));
		gameComboBox.setBounds(220, 49, 210, 23);
		gameSelectPanel.add(gameComboBox);

		btnPlay = new JButton("Play");
		btnPlay.setBounds(220, 99, 89, 23);
		btnPlay.addActionListener(new PlayGameActionListener());
		gameSelectPanel.add(btnPlay);
	}

	/*
	 * returns a list of games for the user to play
	 */
	private String[] getListOfGames() {
		return makerController.getGamesList();
	}

	/*
	 * 
	 * PlayGameActionListener is the event handler for the play button it loads
	 * the play view with the requested game
	 */
	public class PlayGameActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			makerController.gamePlayWindowIsRequested(gameComboBox
					.getSelectedItem().toString());
		}

	}
}
