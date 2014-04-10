package FoodLink.gui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.border.MatteBorder;

import FoodLink.database;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ViewInvoice extends JFrame {

	private JPanel contentPane;
	private JTable reviewOrderTable;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private int invoiceNum;
	private String supplierName;
	private String dateTime;
	private String status;
	private database connect = new database();

	/**
	 * Create the frame.
	 * 
	 * @param grandTotal1
	 */
	public ViewInvoice(int invoiceNum, final String supermarketName, String dateTime,
			String status, String grandTotal1, final int selectedRow,
			final JTable orderItem) {

		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setBackground(new Color(51, 204, 102));
		setTitle("Review Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 662, 527);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 111, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Supermarket Name:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setText(supermarketName);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Invoice Number:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 8;
		gbc_lblNewLabel_2.gridy = 0;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText(Integer.toString(invoiceNum));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 9;
		gbc_textField_1.gridy = 0;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Date and Time Submitted:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText(dateTime);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 7;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 1;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Status:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 8;
		gbc_lblNewLabel_4.gridy = 1;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setText(status);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 9;
		gbc_textField_4.gridy = 1;
		contentPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("Shipped Order");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object frame = null;
				int n = JOptionPane.showConfirmDialog((Component) frame,
						"Are you sure you want to mark this order as SHIPPED?",
						"Shipped Order", JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					System.out.println("MARK ORDER AS SHIPPED");
					System.out.println("selectedRow: " + selectedRow);
					orderItem.setValueAt("Shipped", selectedRow, 4);
					textField_4.setText("Shipped");
				}

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 10;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 11;
		gbc_scrollPane.gridheight = 12;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		reviewOrderTable = new JTable();
		reviewOrderTable.setFillsViewportHeight(true);
		reviewOrderTable.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		// populate the table
		Object[][] orderItemsList = connect.getOrderItems(invoiceNum);

		reviewOrderTable.setModel(new DefaultTableModel(orderItemsList,
				new String[] { "Name", "Item Type", "Quantity",
						"Unit Price ($)", "Unit", "Total" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(reviewOrderTable);

		JLabel lblNewLabel_1 = new JLabel("Grand Total:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 9;
		gbc_lblNewLabel_1.gridy = 14;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText(grandTotal1);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 10;
		gbc_textField_2.gridy = 14;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		GradientButton jbPrint = new GradientButton("Save and open invoice");
		/*jbPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
						File file = new File("invoice_"	+ supermarketName + ".xls");
						TableModel model = orderItem.getModel();
						FileWriter out = new FileWriter(file);
						SimpleDateFormat sdf = new SimpleDateFormat(
								"MMM. d, yyyy", java.util.Locale.ENGLISH);
						Double totalPrice = 0.0;

						out.write(supplier[0] + "\t\t\t"
								+ jcbSupermarkets.getSelectedItem() + "\n");
						out.write(supplier[1] + "\t\t\t"
								+ "SUPERMARKET ADDRESS" + "\n");
						out.write(supplier[2] + "\t\t\t" + "SUPERMARKET PHONE#"
								+ "\n");
						out.write(supplier[3]
								+ "\t\t\t"
								+ sdf.format(java.util.Calendar.getInstance()
										.getTime()) + "\n");
						out.write("\n\n");

						for (int i = 0; i < model.getColumnCount(); i++) {
							out.write(model.getColumnName(i) + "\t");
						}

						out.write("\n");
						for (int i = 0; i < model.getRowCount(); i++) {
							for (int j = 0; j < model.getColumnCount(); j++) {
								if (j == 4) {
									totalPrice += (Double) model.getValueAt(i,
											j);
								}
								out.write(model.getValueAt(i, j).toString()
										+ "\t");
							}
							out.write("\n");
						}

						out.write("\n");
						out.write("\t\t\tTOTAL:\t" + totalPrice + "\n");
						out.close();

						String osname = System.getProperty("os.name")
								.toLowerCase();
						Runtime r = Runtime.getRuntime();
						if (osname.contains("win")) {
							r.exec("cmd.exe /c start " + file);
						} else if (osname.contains("mac")
								|| osname.contains("linux")) {
							r.exec("open " + file);
						} else {
							JOptionPane
									.showMessageDialog(frame,
											"Operating System not supported for printing");
						}*/
						/* THE FOLLOWING LINE PRINTS OFF THE TABLE DIRECTLY */
						// jtInvoice.print(JTable.PrintMode.NORMAL);

				/*	} else {
						JOptionPane.showMessageDialog(frame,
								"Please select an invoice to print");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		JScrollPane jspInvoice = new JScrollPane(orderItem);
		jpInvoices.add(jcbSupermarkets, BorderLayout.NORTH);
		jpInvoices.add(jspInvoice, BorderLayout.CENTER);
		JPanel jpSouth = new JPanel(new FlowLayout());
		jpSouth.add(jbPrint);
		jpInvoices.add(jpSouth, BorderLayout.SOUTH);*/

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
