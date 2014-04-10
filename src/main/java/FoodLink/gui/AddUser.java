package FoodLink.gui;
import FoodLink.database;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private database connect = new database();
	public JFrame frame;
	private JTextField textField;
	private boolean valid=false;
	private JLabel lblCheckAvailability;
	private JLabel lblUsernameIsAlready;
	private String [] user = new String[3];
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser window = new AddUser(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("done");
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public AddUser(int id) {
		initialize(1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		
		
		
		textField = new JTextField();
		textField.setBounds(184, 44, 117, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Enter a UserName");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(30, 42, 144, 20);
		frame.getContentPane().add(lblUsername);
		
		lblCheckAvailability = new JLabel("UserName is valid");
		lblCheckAvailability.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCheckAvailability.setBounds(184, 92, 132, 20);
		frame.getContentPane().add(lblCheckAvailability);
		lblCheckAvailability.setVisible(false);
		
		lblUsernameIsAlready = new JLabel("UserName is already in use");
		lblUsernameIsAlready.setForeground(Color.RED);
		lblUsernameIsAlready.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsernameIsAlready.setBounds(184, 97, 204, 20);
		frame.getContentPane().add(lblUsernameIsAlready);
		lblUsernameIsAlready.setVisible(false);
		
		JButton btnCheckAvailability = new JButton("Check Availability");
		btnCheckAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//reset the labels
				lblCheckAvailability.setVisible(false);
				lblUsernameIsAlready.setVisible(false);
				 
				//ignore press unless thres text in the textfield
				if(!textField.getText().equals("")){
				//call method to return boolean corresponding to validity of user name	
				  validUsername(textField.getText());
					 	
				 }
			}
		});
		btnCheckAvailability.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCheckAvailability.setBounds(30, 93, 144, 23);
		frame.getContentPane().add(btnCheckAvailability);
		
		final JCheckBox chckbxManager = new JCheckBox("Manager");
		chckbxManager.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxManager.setBounds(30, 138, 97, 23);
		frame.getContentPane().add(chckbxManager);
		
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//reset the labels
				lblCheckAvailability.setVisible(false);
				lblUsernameIsAlready.setVisible(false);
				 
				
				//call method to return boolean corresponding to validity of user name, in case user didn't check for validity	
				validUsername(textField.getText());	
				
				if(valid){
					System.out.println("adding the user...");
					
					user[0]= textField.getText();
					user[1]= "password";
					
					if(chckbxManager.isSelected())
						user[2]="true";
					else
						user[2]="false";
					
					
					
					close();
				
				}
				
				
			}
		});
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddUser.setBounds(30, 185, 89, 23);
		frame.getContentPane().add(btnAddUser);
	}
	
	//method to close the window
	protected void close() {
		
		frame.dispose();
		this.setVisible(false);
	}
//method to check valididty of user names
	private boolean validUsername (String user){
		valid=false;
		//search suppermarket table database for user
		Object[] userArray =  connect.getUser(user, true);
		if (userArray[0]==null){
			userArray = connect.getUser(user, false);
			if(!valid){
				valid=true;
				System.out.println("User name is valid");
				}
			}	
			
		if(!valid){
				System.out.println("User name is not valid");
				lblUsernameIsAlready.setVisible(true);
				}
		else 
			lblCheckAvailability.setVisible(true);
		
		return valid;
	
	}

	public String[] getResult() {
		// TODO Auto-generated method stub
		return user;
	}
}
