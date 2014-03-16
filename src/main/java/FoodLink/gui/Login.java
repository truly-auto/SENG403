package FoodLink.gui;

import java.awt.EventQueue;

import FoodLink.gui.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;

import FoodLink.database;

public class Login extends JFrame {

	private database connect = new database();
	private JTextField userField, passwordField;
	private JPasswordField passwordField_1;
	private Object[] credentials = null;

	public Login() {
		// Creating the window
		this.setMinimumSize(new Dimension(450, 300));
		this.setLocation(100, 100);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		// creating the label for invalid password
		final JLabel lblInvalidPassword = new JLabel("Invalid password");
		lblInvalidPassword.setBounds(231, 133, 110, 14);
		this.getContentPane().add(lblInvalidPassword);
		lblInvalidPassword.setVisible(false);
		// creating the label for invalid usernames
		final JLabel lblPleaseEnterA = new JLabel(
				"Please enter a valid user name");
		lblPleaseEnterA.setBounds(233, 89, 175, 14);
		this.getContentPane().add(lblPleaseEnterA);
		lblPleaseEnterA.setVisible(false);

		// adding forgot button
		JButton btnForgot = new JButton("Forgot");
		btnForgot.setBounds(99, 196, 89, 23);
		this.getContentPane().add(btnForgot);

		// creating username label
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(99, 70, 89, 14);
		this.getContentPane().add(lblUsername);

		// creating password label
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(99, 117, 89, 14);
		this.getContentPane().add(lblPassword);

		// adding username text field
		userField = new JTextField();
		userField.setBounds(99, 86, 117, 20);
		this.getContentPane().add(userField);
		userField.setColumns(10);
		// adding a password field
		passwordField = new JTextField();
		passwordField.setBounds(99, 136, 122, 23);
		this.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		/*
		 * passwordField_1 = new JPasswordField(); passwordField_1.setBounds(99,
		 * 136, 122, 23); getContentPane().add(passwordField_1); final
		 * JPasswordField passwordField = new JPasswordField(10);
		 */
		// creating foodlink login
		JLabel lblFoodlink = new JLabel("FoodLink Login");
		lblFoodlink.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblFoodlink.setBounds(99, 24, 216, 41);
		this.getContentPane().add(lblFoodlink);

		// adding login button when clicked retrieves the text from the user and
		// password fields

		JButton btnLogin = new JButton("Login");
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
										(Integer) credentials[1]);

								window.frame.setVisible(true);
								close();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						// login store user
						else {
							try {
								SupermarketSys window = new SupermarketSys();
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
		btnLogin.setBounds(99, 161, 89, 23);
		this.getContentPane().add(btnLogin);

		pack();
	}

	protected void close() {
		this.dispose();

	}
}
