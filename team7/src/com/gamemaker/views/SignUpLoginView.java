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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.exception.ConstraintViolationException;

import com.gamemaker.controllers.MakerController;
import com.gamemaker.main.GameMaker;
import com.gamemaker.models.UserDetails;

/**
 * This class displays the initial login and sign-up screens. 
 */

public class SignUpLoginView extends JFrame {
	private JTextField txtUserID;
	private JTextField txtUserName;
	private JTextField txtPassword;
	
	private JTextField txtConfirmPassword;
	private JTextField txtEmail;
	private JPanel panel;
	private JLabel lblSignUp;
	private JLabel lblName;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	private JLabel lblEmail;
	private JButton btnSignUp;
	private JButton btnLogin;
	private JLabel lblUserType;
	private JComboBox comboBox;
	private JLabel msg;
	private JLabel msgLogin;
	private JTextField loginUserNameTextField;
	private JPasswordField loginPasswordTextField;
	private JPanel loginPanel;
	private JLabel lblLogin;
	private JLabel lblUserId;
	private JLabel lblLoginPassword;
	private JButton btnLoginView;
	private JButton btnSignUpLoginView;

	private MakerController makerController;

	public SignUpLoginView(MakerController makerController) {
		setBounds(0, 0, 570, 400);
		this.makerController = makerController;
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLoginView();
		setVisible(true);
		setTitle("Login/Register");
	}

	public void setLoginView() {

		loginPanel = new JPanel();
		loginPanel.setBounds(10, 11, 532, 339);
		loginPanel.setLayout(null);
		getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblLogin.setBounds(235, 11, 74, 27);
		loginPanel.add(lblLogin);

		lblUserId = new JLabel("User ID:");
		lblUserId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUserId.setBounds(76, 116, 74, 27);
		loginPanel.add(lblUserId);

		lblLoginPassword = new JLabel("Password:");
		lblLoginPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLoginPassword.setBounds(76, 154, 84, 27);
		loginPanel.add(lblLoginPassword);

		loginUserNameTextField = new JTextField();
		loginUserNameTextField.setBounds(223, 120, 214, 25);
		loginPanel.add(loginUserNameTextField);
		loginUserNameTextField.setColumns(10);

		loginPasswordTextField = new JPasswordField();
		loginPasswordTextField.setBounds(223, 154, 214, 25);
		loginPanel.add(loginPasswordTextField);
		loginPasswordTextField.setColumns(10);

		btnLoginView = new JButton("Login");
		btnLoginView.setBounds(223, 207, 89, 23);
		loginPanel.add(btnLoginView);

		msgLogin = new JLabel();
		msgLogin.setBounds(190,250,300,30);
		loginPanel.add(msgLogin);

		btnLoginView.addActionListener(new LoginActionListener());
		btnSignUpLoginView = new JButton("Sign Up");
		btnSignUpLoginView.setBounds(343, 207, 89, 23);
		btnSignUpLoginView.addActionListener(new SignUpLoginViewActionListener());
		loginPanel.add(btnSignUpLoginView);
	}

	public void setRegistrationView() {
		panel = new JPanel();
		panel.setBounds(10, 11, 532, 336);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSignUp.setBounds(233, 11, 98, 34);
		panel.add(lblSignUp);

		msg = new JLabel();

		lblName = new JLabel("User ID:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(32, 65, 66, 24);
		panel.add(lblName);

		lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUserName.setBounds(32, 100, 76, 24);
		panel.add(lblUserName);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPassword.setBounds(32, 135, 76, 24);
		panel.add(lblPassword);

		lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(32, 170, 118, 24);
		panel.add(lblConfirmPassword);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmail.setBounds(32, 205, 98, 24);
		panel.add(lblEmail);

		txtUserID = new JTextField();
		txtUserID.setBounds(202, 64, 188, 25);
		panel.add(txtUserID);
		txtUserID.setColumns(10);

		txtUserName = new JTextField();
		txtUserName.setBounds(202, 100, 188, 25);
		panel.add(txtUserName);
		txtUserName.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(202, 138, 188, 25);
		panel.add(txtPassword);
		txtPassword.setColumns(10);

		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setBounds(202, 173, 188, 25);
		panel.add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(202, 208, 188, 25);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(202, 296, 89, 23);
		panel.add(btnSignUp);

		btnSignUp.addActionListener(new SignUpActionHandler());

		btnLogin = new JButton("Login");
		btnLogin.setBounds(301, 295, 89, 23);
		btnLogin.addActionListener(new LoginViewActionListener());
		panel.add(btnLogin);

		lblUserType = new JLabel("User Type:");
		lblUserType.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUserType.setBounds(32, 240, 98, 23);
		panel.add(lblUserType);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Select User Type", "Game Maker", "Game Player" }));
		comboBox.setBounds(202, 242, 188, 25);
		panel.add(comboBox);
		panel.revalidate();

		panel.add(msg);
		msg.setBounds(190, 315, 200, 23);
	}

	private class SignUpActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
						
			if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
				if (!comboBox.getSelectedItem().equals("Select User Type")) {
					UserDetails userDetails = new UserDetails();
					userDetails.setUserId(txtUserID.getText());
					userDetails.setUserName(txtUserName.getText());
					userDetails.setPassword(txtPassword.getText());
					userDetails.setConfirmPassword(txtConfirmPassword.getText());
					userDetails.setEmailID(txtEmail.getText());

					if (comboBox.getSelectedItem().equals("Game Maker")) {
						userDetails.setIsGameMaker(true);
						userDetails.setIsGamePlayer(true);
						
					} else {
						userDetails.setIsGamePlayer(true);
					}

					try {
						GameMaker.databaseWriter.write(userDetails);
						
						GameMaker.loggedInUser = userDetails;
						
						if (userDetails.isIsGameMaker() == true && userDetails.isIsGamePlayer() == true) {
							StartView startView = new StartView(makerController);
							setVisible(false);
						} 
						else if (userDetails.isIsGameMaker() == false && userDetails.isIsGamePlayer() == true) {
							GamePlayerPlayView gamePlayerObj = new GamePlayerPlayView(makerController);
							setVisible(false);
						}
												
					} 
					catch (ConstraintViolationException exception) {
						msg.setText("User ID already exists!");
					}
				} else {
					msg.setText("");
				}
			}else {
				msg.setText("Passwords don't match");
			}
		}
	}

	/*
	 * Signup btn login view on click event handler switches the view to the
	 * sign up form from the login view clear the panel and redraw
	 */
	
	private class SignUpLoginViewActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setTitle("Sign-up Window");
			getContentPane().remove(loginPanel);
			repaint();
			setRegistrationView();
			btnLogin.setVisible(false);
		}

	}

	private class LoginActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			loginPanel.setEnabled(false);
			if (loginUserNameTextField.getText().equals("") || loginPasswordTextField.getText().equals("")) {
				msgLogin.setText("Please enter both username and password!");
			} else {
				UserDetails userDetails = GameMaker.databaseReader.getRegisteredUserDetails(loginUserNameTextField.getText());
				if (userDetails == null) {
					msgLogin.setText("You are not registered!");
				} else {
					if (!userDetails.getPassword().equals(loginPasswordTextField.getText())) {
						msgLogin.setText("Invalid Password!");
					} else {
						GameMaker.loggedInUser = userDetails;
						if (userDetails.isIsGameMaker() == true && userDetails.isIsGamePlayer() == true) {
							StartView startView = new StartView(makerController);
							setVisible(false);
						} else if (userDetails.isIsGameMaker() == false && userDetails.isIsGamePlayer() == true) {
							GamePlayerPlayView gamePlayerObj = new GamePlayerPlayView(makerController);
							setVisible(false);
						}
					}
				}
			}
			loginPanel.setEnabled(true);
		}

	}

	/*
	 * loginviewactionlistener handles the login button click event it redirects
	 * the user to the login view
	 */
	
	private class LoginViewActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			setTitle("Login/Register");
			getContentPane().remove(panel);
			repaint();
			setLoginView();
		
		}

	}

}
