package FoodLink.gui;

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

public class AutomatedOrdering extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField thresholdField;
	private JTextField quantityField;
	private String result[];

	
	/**
	 * Create the dialog.
	 */
	public AutomatedOrdering(String itemName, String units) {
		setTitle("Order an Item Automatically");
		setBounds(100, 100, 397, 212);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 141, 380, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean valid=true;
						
						if (thresholdField.getText().equals("") || quantityField.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Please do not leave any fields blank.");
						}
						else
						{	int quantity; int threshold;
							try{
								quantity = Integer.parseInt(quantityField.getText().trim());
								threshold = Integer.parseInt(thresholdField.getText().trim());
								if (quantity < 0 || threshold < 0){
									valid=false;
								}
							}
							catch(NumberFormatException e){
			
								valid=false;
								JOptionPane.showMessageDialog(null, "Please enter valid integers for the threshold and quantity");
							}
							
							
						}
						
						if (valid){
							String r[] = {thresholdField.getText(), quantityField.getText()};
							result = r;
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
		
		thresholdField = new JTextField();
		thresholdField.setBounds(159, 42, 114, 20);
		getContentPane().add(thresholdField);
		thresholdField.setColumns(10);
		
		quantityField = new JTextField();
		quantityField.setBounds(159, 73, 114, 20);
		getContentPane().add(quantityField);
		quantityField.setColumns(10);
		
		JLabel lblItemType = new JLabel(itemName);
		lblItemType.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemType.setBounds(12, 0, 369, 20);
		getContentPane().add(lblItemType);
		
		JLabel thresholdLabel = new JLabel("Threshold Amount");
		thresholdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		thresholdLabel.setBounds(12, 43, 154, 17);
		getContentPane().add(thresholdLabel);
		
		JLabel lblQuantity = new JLabel("Quantity to Order");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setBounds(22, 74, 139, 17);
		getContentPane().add(lblQuantity);
		
		JLabel lblErrorMessage = new JLabel("errorPlaceHolder");
		lblErrorMessage.setVisible(false);
		lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(56, 128, 271, 14);
		getContentPane().add(lblErrorMessage);
		
		JLabel unitsLabel = new JLabel(units);
		unitsLabel.setBounds(291, 75, 70, 15);
		getContentPane().add(unitsLabel);
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
		return result;
	}
	
	public String[] getResult()
	{
		return this.result;
	}
}
