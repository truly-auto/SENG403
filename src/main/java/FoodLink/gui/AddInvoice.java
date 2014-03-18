package FoodLink.gui;

import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JButton;

public class AddInvoice {

	private JFrame frame;

	private String result[];
	private Integer invoiceNum = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddInvoice window = new AddInvoice(2);
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
	public AddInvoice(int supermarket_id) {
		initialize(supermarket_id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int supermarket_id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextPane textPane = new JTextPane();
		frame.getContentPane().add(textPane, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("New button");
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);

		Integer newNum = getInvoiceNumber();
		setInvoiceNumber(newNum++);
		String r[] = { newNum.toString() };
		result = r;
	}

	private String[] getResult() {
		return result;
	}

	private Integer getInvoiceNumber() {
		return invoiceNum;
	}

	private void setInvoiceNumber(Integer newNum) {
		invoiceNum = newNum;
	}
}
