package FoodLink.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
<<<<<<< HEAD
=======
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
>>>>>>> adf7e7b0c44ed78a1daa8c4a163e1df136aa7f49

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

<<<<<<< HEAD
import javax.swing.JOptionPane;
=======
>>>>>>> adf7e7b0c44ed78a1daa8c4a163e1df136aa7f49
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Insets;
<<<<<<< HEAD
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import FoodLink.database;
import java.awt.GradientPaint;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
=======

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
>>>>>>> adf7e7b0c44ed78a1daa8c4a163e1df136aa7f49

import java.awt.Color;

import javax.swing.border.MatteBorder;

import FoodLink.database;

import java.awt.Font;

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
	 * @param grandTotal 
	 */
	public ViewInvoice(int invoiceNum, String supplierName, String dateTime, String status, double grandTotal) {
		
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
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 111, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		textField.setText(supplierName);
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
		
		//populate the table
		Object [] [] orderItemsList = connect.getOrderInformation(invoiceNum);
		
		reviewOrderTable.setModel(new DefaultTableModel(
			orderItemsList,
			new String[] {
				"Name", "Item Type", "Quantity", "Unit Price ($)", "Unit", "Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
<<<<<<< HEAD
		};
		;

		table = new JTable(orderModel);
		scrollPane.setColumnHeaderView(table);

		JButton btnPrintInvoice = new JButton("Print Invoice");
		GridBagConstraints gbc_btnPrintInvoice = new GridBagConstraints();
		gbc_btnPrintInvoice.gridx = 0;
		gbc_btnPrintInvoice.gridy = 1;
		frame.getContentPane().add(btnPrintInvoice, gbc_btnPrintInvoice);
		/*
		 * jcbSupermarkets.addActionListener(new ActionListener() {
		 * 
		 * @SuppressWarnings("serial")
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { int index =
		 * jcbSupermarkets.getSelectedIndex(); jtInvoice.setModel(new
		 * DefaultTableModel(listOrders.get(index),title) { boolean[]
		 * columnEditables = new boolean[] { false, false, false, false };
		 * 
		 * public boolean isCellEditable(int row, int column) { return
		 * columnEditables[column]; } }); } });
		 * 
		 * GradientButton jbPrint = new GradientButton("Save invoice and Open");
		 * jbPrint.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { try {
		 * if(jcbSupermarkets.getSelectedIndex() >= 0){ File file = new
		 * File("invoice_" + jcbSupermarkets.getSelectedItem()+ ".xls");
		 * TableModel model = jtInvoice.getModel(); FileWriter out = new
		 * FileWriter(file); SimpleDateFormat sdf = new
		 * SimpleDateFormat("MMM. d, yyyy", java.util.Locale.ENGLISH); Double
		 * totalPrice = 0.0;
		 * 
		 * out.write(supplier[0] + "\t\t\t" + jcbSupermarkets.getSelectedItem()
		 * + "\n"); out.write(supplier[1] + "\t\t\t" + "SUPERMARKET ADDRESS" +
		 * "\n"); out.write(supplier[2] + "\t\t\t" + "SUPERMARKET PHONE#" +
		 * "\n"); out.write(supplier[3] + "\t\t\t" +
		 * sdf.format(java.util.Calendar.getInstance().getTime()) + "\n");
		 * out.write("\n\n");
		 * 
		 * for(int i = 0; i < model.getColumnCount(); i++){
		 * out.write(model.getColumnName(i) + "\t"); }
		 * 
		 * out.write("\n"); for(int i=0; i < model.getRowCount();i++){ for(int
		 * j=0;j < model.getColumnCount();j++){ if (j == 4){ totalPrice +=
		 * (Double) model.getValueAt(i, j); }
		 * out.write(model.getValueAt(i,j).toString() + "\t"); }
		 * out.write("\n"); }
		 * 
		 * out.write("\n"); out.write("\t\t\tTOTAL:\t" + totalPrice + "\n");
		 * out.close();
		 * 
		 * String osname = System.getProperty("os.name").toLowerCase(); Runtime
		 * r = Runtime.getRuntime(); if(osname.contains("win")){
		 * r.exec("cmd.exe /c start " + file); } else if (osname.contains("mac")
		 * || osname.contains("linux")){ r.exec("open "+ file); } else{
		 * JOptionPane.showMessageDialog(frame,
		 * "Operating System not supported for printing"); }
		 */
		/* THE FOLLOWING LINE PRINTS OFF THE TABLE DIRECTLY */
		// jtInvoice.print(JTable.PrintMode.NORMAL);
		/*
		 * } else{ JOptionPane.showMessageDialog(frame,
		 * "Please select an invoice to print"); } } catch (IOException e1) {
		 * e1.printStackTrace(); }
		 * 
		 * } });
		 */
=======
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
		textField_2.setText(Double.toString(grandTotal));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 10;
		gbc_textField_2.gridy = 14;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Complete Order");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 10;
		gbc_btnNewButton.gridy = 15;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
>>>>>>> adf7e7b0c44ed78a1daa8c4a163e1df136aa7f49

	}

}

	//		Object[][] orderList = connect.getOrderInformation(invoiceNumber);
//	final String[] columnTitle= new String[]{"Item Number", "Name", "Item Type", "Quantity", "Unit Price ($)", "Unit", "Total"};

