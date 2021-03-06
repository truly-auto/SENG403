package FoodLink.gui;

import java.awt.EventQueue;

import FoodLink.gui.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import FoodLink.database;

public class Login extends JFrame {

	private database connect = new database();
	private JTextField userField, passwordField;
	private JPasswordField passwordField_1;
	private Object[] credentials = null;

	/**
	 * Creates a login window for user to log in. User must enter a username and
	 * password. If the field is missing the window will prompt the user for
	 * data.
	 * 
	 * Supermarket and suppliers log in with the same window. System will check
	 * for the users in both supermarket and supplier and open the corresponding
	 * UI.
	 * 
	 * Forgot password button does not work, just there as placeholder.
	 */
	public Login() {
		// Creating the window
		this.setMinimumSize(new Dimension(640, 420));
		this.setLocation(100, 100);
		this.setResizable(false);
		LookAndFeel lookAndFeel = new LookAndFeel(this);

		Color green = new Color(182, 215, 168);
		Color grey = new Color(153, 153, 153);
		this.setForeground(green);
		BufferedImage Logo = null;
		JPanel banner = new JPanel();
		try {
			Logo = ImageIO
					.read(new File("src/main/resources/images/Logo17.JPG")); // put
																				// icon
																				// image
																				// here
			JLabel LogoPanel = new JLabel(new ImageIcon(Logo));
			banner.add(LogoPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		banner.setBackground(green);
		this.getContentPane().add(banner, "North");

		JPanel buttonsPanel = new JPanel();

		this.getContentPane().setBackground(green);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.getContentPane().setLayout(null);

		// ~~~~~~~~~~~~~DIVISION~~~~~~~~~~~~~~~~~~~~//
		// adding login button when clicked retrieves the text from the user and
		// password fields
		GradientButton btnLogin = new GradientButton("Login");
		btnLogin.setBackground(grey);
		btnLogin.setBounds(400, 295, 89, 23);
		this.getContentPane().add(btnLogin);

		// adding forgot button
		GradientButton btnForgot = new GradientButton("Forgot");
		btnForgot.setBackground(grey);
		btnForgot.setBounds(400, 330, 89, 23);
		this.getContentPane().add(btnForgot);

		// creating username label
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(140, 290, 89, 14);
		this.getContentPane().add(lblUsername);

		// adding username text field
		userField = new JTextField();
		userField.setBounds(220, 288, 122, 23);
		this.getContentPane().add(userField);
		userField.setColumns(10);

		// creating the label for invalid usernames
		final JLabel lblPleaseEnterA = new JLabel(
				"Please enter a valid username");
		lblPleaseEnterA.setBounds(195, 313, 175, 14);
		this.getContentPane().add(lblPleaseEnterA);
		lblPleaseEnterA.setVisible(false);

		// creating password label
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(140, 340, 89, 14);
		this.getContentPane().add(lblPassword);

		// adding a password field
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 338, 122, 23);
		this.getContentPane().add(passwordField);
		passwordField.setColumns(10);

		// creating the label for invalid password
		final JLabel lblInvalidPassword = new JLabel("Invalid password");
		lblInvalidPassword.setBounds(233, 363, 110, 14);
		this.getContentPane().add(lblInvalidPassword);
		lblInvalidPassword.setVisible(false);

		// MT:Sets the order of focus when you press Tab
		userField.setNextFocusableComponent(passwordField);
		passwordField.setNextFocusableComponent(btnLogin);
		btnLogin.setNextFocusableComponent(btnForgot);
		btnForgot.setNextFocusableComponent(userField);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean supplier = true;
				// if the user enters text, get user from database and update
				// global user array with user information
				if (userField.getText().length() > 0) {
					String username = userField.getText();
					credentials = connect.getUser(username, supplier);
					// if the result is null for supplier user then check
					// supermarket
					if (credentials[0] == null) {
						supplier = false;
						credentials = connect.getUser(username, false);
					}
					// if the result is null for supermarket user then user does
					// not exists
					if (credentials[0] == null) {
						lblPleaseEnterA.setVisible(true);
					} else {
						lblPleaseEnterA.setVisible(false);
					}
				}
				// else if the field is empty then username is invalid
				else {
					lblPleaseEnterA.setVisible(true);
				}
				// checking the password
				if (passwordField.getText().length() > 0 && credentials != null) {
					// check if password matches to the one stored in user array
					String password = passwordField.getText();
					if (password.equals((String) credentials[0])) {
						System.out.println("Login Succesful");
						// login supplier
						if (supplier) {
							try {

								SupplierSys window = new SupplierSys(
										(Integer) credentials[1],
										Boolean.valueOf((String) credentials[2]));

								window.frame.setVisible(true);
								close();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						// login store user
						else {
							try {
								// SupermarketSys window = new SupermarketSys();
								SupermarketSys window = new SupermarketSys(
										(Integer) credentials[1],
										Boolean.valueOf((String) credentials[2]));

								window.frame.setVisible(true);
								close();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}

						lblInvalidPassword.setVisible(false);
					}
					// if not show error message
					else {
						lblInvalidPassword.setVisible(true);
					}
				}
				// if password field is blank enter show message
				else {
					lblInvalidPassword.setVisible(true);
				}

			}

		});

		this.getContentPane().add(buttonsPanel);

		pack();
	}

	protected void close() {
		this.dispose();

	}

	private static final class GradientButton extends JButton {
		private GradientButton() {
			this.setText("");
			setContentAreaFilled(false);
		}

		private GradientButton(String str) {
			this.setText(str);
			;
			setContentAreaFilled(false);

		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D G2D = (Graphics2D) g.create();
			Color grey = new Color(153, 153, 153);
			G2D.setPaint(new GradientPaint(new Point(0, 0), Color.white,
					new Point(0, getHeight()), grey));
			G2D.fillRect(0, 0, getWidth(), getHeight());
			G2D.dispose();

			super.paintComponent(g);
		}
	}
}