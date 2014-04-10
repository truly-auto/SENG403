package FoodLink.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Insets;
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

public class ViewInvoice {

	private JFrame frame;
	private JTable table;
	private database connect = new database();
	private JTable orderTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// change invoice number
					ViewInvoice window = new ViewInvoice(1);
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
	public ViewInvoice(int invoiceNumber) {
		initialize(invoiceNumber);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int invoiceNumber) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);

		Object[][] orderList = connect.getOrderInformation(invoiceNumber);
		final String[] columnTitle = new String[] { "Item Number", "Name",
				"Item Type", "Quantity", "Unit Price ($)", "Unit", "Total" };

		DefaultTableModel orderModel = new DefaultTableModel(orderList,
				columnTitle) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
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

	}

}
