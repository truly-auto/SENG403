package FoodLink.gui;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.TableModel;
import javax.swing.JTextField;

import FoodLink.database;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.SimpleDateFormat;

public class ViewInvoice extends JFrame {

	private JPanel contentPane;
	private JTable reviewOrderTable;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField jtfTotal;
	private JTextField textField_3;
	private JTextField textField_4;
	private int invoiceNum;
	private String supplierName;
	private String marketName;
	private String total;
	private String dateTime;
	private String status;
	private int storeId;
	private database connect = new database();

	/**
	 * Creates the window to view items in the invoice in Supplier. User is able
	 * to view order items, confirm the order has been shipped, print the
	 * invoice into an excel spreadsheet
	 * 
	 * @param invoiceNumber
	 *            , the invoice reference number
	 * @param supermarketName
	 *            , the string of the supermarket that is ordering
	 * @param store_id
	 *            , the supermarket store number
	 * @param supplier_id
	 *            , the supplier store number
	 * @param dateTime
	 *            , the time the order was created
	 * @param status
	 *            , the status of the order(submitted, shipped, completed)
	 * @param grandTotal1
	 *            , the total cost of the order
	 * @param selectedRow
	 *            , getting the row of the status
	 * @param orderItem
	 *            , the JTable where the invoice information is stored
	 * 
	 */
	public ViewInvoice(int invoiceNumber, String supermarketName,
			String store_id, final int supplier_id, String dateTime,
			String status, String grandTotal1, final int selectedRow,
			final JTable orderItem) {

		invoiceNum = invoiceNumber;
		marketName = supermarketName;
		storeId = Integer.parseInt(store_id);
		total = grandTotal1;

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

		/* Marking order confirmation */
		JButton btnNewButton = new JButton("Shipped Order");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object frame = null;
				int n = JOptionPane.showConfirmDialog((Component) frame,
						"Are you sure you want to mark this order as SHIPPED?",
						"Shipped Order", JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					String status = (String) orderItem.getValueAt(selectedRow,
							4);
					System.out.println("I am: " + status);
					if (status != "Complete") {
						System.out.println("MARK ORDER AS SHIPPED");
						System.out.println("selectedRow: " + selectedRow);
						orderItem.setValueAt("Shipped", selectedRow, 4);
						textField_4.setText("Shipped");
						connect.updateOrderStatus("Shipped", invoiceNum);
						Object[][] orderList = connect
								.getOrderListSupplier(supplier_id);
						final String[] columnNameInvoice = { "Invoice Number",
								"Supplier", "Total Cost($)",
								"Date/Time Created", "Status" };

						final DefaultTableModel orderModel = new DefaultTableModel(
								orderList, columnNameInvoice) {
							boolean[] columnEditables = new boolean[] { false,
									false, false, false };

							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						};
					}

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
		reviewOrderTable.getTableHeader().setReorderingAllowed(false);

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

		jtfTotal = new JTextField();
		jtfTotal.setEditable(false);
		jtfTotal.setText(grandTotal1);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 10;
		gbc_textField_2.gridy = 14;
		contentPane.add(jtfTotal, gbc_textField_2);
		jtfTotal.setColumns(10);

		/* Get supplier/supermarket info */
		final String[] supplierInfo = connect.getSpecSupplier(supplier_id);
		final String[] supermarketInfo = connect.getSpecSupermarket(storeId);

		GradientButton jbPrint = new GradientButton("Save invoice and Open");
		GridBagConstraints gbc_jbPrint = new GridBagConstraints();
		gbc_jbPrint.insets = new Insets(0, 5, 5, 5);
		gbc_jbPrint.fill = GridBagConstraints.HORIZONTAL;
		gbc_jbPrint.gridx = 2;
		gbc_jbPrint.gridy = 15;
		contentPane.add(jbPrint, gbc_jbPrint);

		jbPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					File file = new File("invoice_" + invoiceNum + ".xls");
					TableModel model = reviewOrderTable.getModel();
					FileWriter out = new FileWriter(file);
					SimpleDateFormat sdf = new SimpleDateFormat("MMM. d, yyyy",
							java.util.Locale.ENGLISH);

					// Display supplier info and supermarket info
					out.write(supplierInfo[0] + "\t\t\t\t\t"
							+ supermarketInfo[0] + "\n");
					out.write(supplierInfo[1] + "\t\t\t\t\t"
							+ supermarketInfo[1] + "\n");
					out.write(supplierInfo[2] + "\t\t\t\t\t"
							+ supermarketInfo[2] + "\n");
					out.write(supplierInfo[3]
							+ "\t\t\t\t\t"
							+ supermarketInfo[3]
							+ "\n\t\t\t\t\t"
							+ sdf.format(java.util.Calendar.getInstance()
									.getTime()) + "\n");
					out.write("\n\n");

					// Display header
					for (int i = 0; i < model.getColumnCount(); i++) {
						if (i != 1 && i != 4)
							out.write(model.getColumnName(i) + "\t\t");
					}

					// Display values
					out.write("\n");
					for (int i = 0; i < model.getRowCount(); i++) {
						for (int j = 0; j < model.getColumnCount(); j++) {
							if (j != 1 && j != 4)
								out.write((String) model.getValueAt(i, j)
										+ "\t\t");
						}
						out.write("\n");
					}

					out.write("\n");
					out.write("\t\t\t\t\tTOTAL:\t" + total + "\n");
					out.close();

					String osname = System.getProperty("os.name").toLowerCase();
					Runtime r = Runtime.getRuntime();
					if (osname.contains("win")) {
						r.exec("cmd.exe /c start " + file);
					} else if (osname.contains("mac")
							|| osname.contains("linux")) {
						r.exec("open " + file);
					} else {
						JOptionPane.showMessageDialog(
								(Component) e.getSource(),
								"Operating System not supported for printing");
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	final class GradientButton extends JButton {
		private GradientButton() {
			this.setText("");
			setContentAreaFilled(false);
		}

		GradientButton(String str) {
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
