package FoodLink.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel lblInvalidPassword = new JLabel("Invalid password");
		lblInvalidPassword.setBounds(231, 133, 110, 14);
		frame.getContentPane().add(lblInvalidPassword);
		lblInvalidPassword.setVisible(false);
		
		final JLabel lblPleaseEnterA = new JLabel("Please enter a valid user name");
		lblPleaseEnterA.setBounds(233, 89, 175, 14);
		frame.getContentPane().add(lblPleaseEnterA);
		lblPleaseEnterA.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()>0){
					String username = textField.getText();
					System.out.println(username);
				}
				else
					{
					lblPleaseEnterA.setVisible(true);
					}
			
				if(textField_1.getText().length()>0){
					String password = textField_1.getText();
					System.out.println(password);
				}
				else
					{
					lblInvalidPassword.setVisible(true);
					}
			
			}
		});
		btnLogin.setBounds(99, 161, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnForgot = new JButton("Forgot");
		btnForgot.setBounds(99, 196, 89, 23);
		frame.getContentPane().add(btnForgot);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(99, 70, 89, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(99, 117, 89, 14);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(99, 86, 117, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(99, 130, 117, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFoodlink = new JLabel("FoodLink Login");
		lblFoodlink.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblFoodlink.setBounds(99, 24, 216, 41);
		frame.getContentPane().add(lblFoodlink);
		
		
		
		
		
	}
}
