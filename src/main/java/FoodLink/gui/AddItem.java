package FoodLink.gui;
import FoodLink.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddItem extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField itemTypeField;
	private JTextField itemField;
	private JTextField quantityField;
	private JTextField priceField;
	private String result[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddItem dialog = new AddItem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddItem() {
		setTitle("Add/Edit an item");
		setBounds(100, 100, 397, 264);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 181, 380, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/**
						 * TODO: More case checking, this so far only checks to make sure user doesn't leave blanks
						 */
						if (itemTypeField.getText().equals("") || itemField.getText().equals("") || quantityField.getText().equals("") || priceField.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Please do not leave any fields blank.");
						}
						else
						{
							Item item = new Item();
							 
							
							item.setItemName(itemField.getText());
							if (Integer.parseInt(quantityField.getText()) > 0)
								item.setItemQuantity(Integer.parseInt(quantityField.getText()));
							else item.setItemQuantity(0);
							item.setItemPrice(Double.valueOf(priceField.getText()));
							// 
							String r[] = {itemTypeField.getText(), itemField.getText(), quantityField.getText(), priceField.getText()}; // please don't delete this.. it's only temporary but 
																																		// this specifically was requested by another group member
							result = r;			// this specifically gets returned because THIS dialog is set to APPLICATION MODAL
							
							closeThisDialog();
							
							
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						closeThisDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		itemTypeField = new JTextField();
		itemTypeField.setBounds(159, 40, 168, 20);
		getContentPane().add(itemTypeField);
		itemTypeField.setColumns(10);
		
		itemField = new JTextField();
		itemField.setBounds(159, 71, 168, 20);
		getContentPane().add(itemField);
		itemField.setColumns(10);
		
		quantityField = new JTextField();
		quantityField.setBounds(159, 102, 168, 20);
		getContentPane().add(quantityField);
		quantityField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setBounds(159, 133, 168, 20);
		getContentPane().add(priceField);
		priceField.setColumns(10);
		
		JLabel lblItemType = new JLabel("Item Name");
		lblItemType.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemType.setBounds(33, 43, 66, 14);
		getContentPane().add(lblItemType);
		
		JLabel lblItem = new JLabel("Item Type");
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem.setBounds(33, 74, 66, 14);
		getContentPane().add(lblItem);
		
		JLabel lblQuanitty = new JLabel("Quantity");
		lblQuanitty.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuanitty.setBounds(33, 105, 66, 14);
		getContentPane().add(lblQuanitty);
		
		JLabel lblPrice = new JLabel("Price ($)");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(33, 136, 66, 14);
		getContentPane().add(lblPrice);
		
		JLabel lblErrorMessage = new JLabel("errorPlaceHolder");
		lblErrorMessage.setVisible(false);
		lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(64, 162, 271, 14);
		getContentPane().add(lblErrorMessage);
	}
	/*
	 * makes invisible
	 */
	protected void closeThisDialog() {
		this.setVisible(false);
		this.dispose();
	}
	
	protected void hideThisDialog() {
		this.setVisible(false);
	}
	
	public String[] showDialog() {
		this.setVisible(true);
		return this.result;
	}
	
	public String[] getResult()
	{
		return this.result;
	}
}
