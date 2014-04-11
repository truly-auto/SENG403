package FoodLink.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class AddItem extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField itemTypeField;
	private JTextField itemField;
	private JTextField quantityField;
	private JTextField priceField;
	private String result[];
	private JTextField unitField;

	/**
	 * Create the dialog to adding a new item to inventory. User will input the
	 * name of the item, type of item, quantity, price, shipment by units
	 */
	public AddItem() {
		setTitle("Add an item");
		setBounds(100, 100, 397, 264);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(
					"src/main/resources/images/foodlinkIcon.png")); // put icon
																	// image
																	// here
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIconImage(img);

		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 181, 380, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				GradientButton okButton = new GradientButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean valid = true; // valid unless otherwise caught
												// by following code
						// ensure that user has filled in everything
						if (itemTypeField.getText().equals("")
								|| itemField.getText().equals("")
								|| quantityField.getText().equals("")
								|| priceField.getText().equals("")
								|| unitField.getText().equals("")) {
							JOptionPane.showMessageDialog(null,
									"Please do not leave any fields blank.");
						} else {
							int quantity;
							double price;
							try {
								quantity = Integer.parseInt(quantityField
										.getText().trim());
								price = Double.parseDouble(priceField.getText()
										.trim());
								if (quantity < 0 || price < 0) {
									valid = false;
								}
							} catch (NumberFormatException e) {

								valid = false;
								JOptionPane
										.showMessageDialog(null,
												"Please enter valid integers for price and quantity");
							}

						}

						if (valid) {
							String r[] = { itemTypeField.getText(),
									itemField.getText(),
									quantityField.getText(),
									priceField.getText(), unitField.getText() };
							result = r; // set AddItem's result, Supermarket
										// will be able to use getResult()
							closeThisDialog(); // work is done

						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				GradientButton cancelButton = new GradientButton("Cancel");
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
		itemTypeField.setBounds(159, 18, 168, 20);
		getContentPane().add(itemTypeField);
		itemTypeField.setColumns(10);

		itemField = new JTextField();
		itemField.setBounds(159, 49, 168, 20);
		getContentPane().add(itemField);
		itemField.setColumns(10);

		quantityField = new JTextField();
		quantityField.setBounds(159, 80, 168, 20);
		getContentPane().add(quantityField);
		quantityField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(159, 111, 168, 20);
		getContentPane().add(priceField);
		priceField.setColumns(10);

		JLabel lblItemType = new JLabel("Item Name");
		lblItemType.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemType.setBounds(43, 21, 66, 14);
		getContentPane().add(lblItemType);

		JLabel lblItem = new JLabel("Item Type");
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem.setBounds(43, 52, 66, 14);
		getContentPane().add(lblItem);

		JLabel lblQuanitty = new JLabel("Quantity");
		lblQuanitty.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuanitty.setBounds(43, 83, 66, 14);
		getContentPane().add(lblQuanitty);

		JLabel lblPrice = new JLabel("Price ($)");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(43, 114, 66, 14);
		getContentPane().add(lblPrice);

		JLabel lblErrorMessage = new JLabel("errorPlaceHolder");
		lblErrorMessage.setVisible(false);
		lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(56, 168, 271, 14);
		getContentPane().add(lblErrorMessage);

		JLabel lblUnit = new JLabel("Units");
		lblUnit.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnit.setBounds(43, 145, 66, 14);
		getContentPane().add(lblUnit);

		unitField = new JTextField();
		unitField.setBounds(158, 142, 169, 20);
		getContentPane().add(unitField);
		unitField.setColumns(10);
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

	public String[] getResult() {
		return this.result;
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
