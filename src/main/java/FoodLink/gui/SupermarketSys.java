package FoodLink.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;

import java.awt.List;
import java.awt.Choice;

import javax.swing.DefaultComboBoxModel;

import FoodLink.database;
import javax.swing.table.DefaultTableModel;

import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.SystemColor;

public class SupermarketSys {

	public JFrame frame;

	private JTable table_3;
	private JTable table_2;
	private JTable table_1;
	private int store_id;
	private JTable table1;
	private JTable table;
	private JComboBox comboBox;
<<<<<<< HEAD


	private database connect = new database();
	private String[] itemsListForSupplier;
	private JList supplierItemsList;
	private DefaultListModel itemsListModel1;
=======
<<<<<<< HEAD
	private database connect = new database ();
	private String[] itemsListForSupplier;
	private JList supplierItemsList;
	private DefaultListModel itemsListModel1;

=======
	private database connect = new database();
>>>>>>> origin/master
>>>>>>> FETCH_HEAD

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupermarketSys window = new SupermarketSys();
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
	public SupermarketSys() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("FoodLink");
		frame.setBounds(100, 100, 640, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);
<<<<<<< HEAD
		
=======


		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

>>>>>>> origin/master
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 0;
		frame.getContentPane().add(label, gbc_label);
		
<<<<<<< HEAD
		JButton btnNewButton1 = new JButton("Log Out");
		btnNewButton1.addActionListener(new ActionListener() {
=======
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
>>>>>>> FETCH_HEAD
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 19;
		gbc_btnNewButton.gridy = 0;
		frame.getContentPane().add(btnNewButton1, gbc_btnNewButton);

		final JTabbedPane mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_mainTabbedPane = new GridBagConstraints();
		gbc_mainTabbedPane.gridheight = 2;
		gbc_mainTabbedPane.gridwidth = 20;
		gbc_mainTabbedPane.fill = GridBagConstraints.BOTH;
		gbc_mainTabbedPane.gridx = 0;
		gbc_mainTabbedPane.gridy = 1;
		frame.getContentPane().add(mainTabbedPane, gbc_mainTabbedPane);

		JPanel orderTab = new JPanel();
		mainTabbedPane.addTab("Order", null, orderTab, null);
		GridBagLayout gbl_orderTab = new GridBagLayout();
		gbl_orderTab.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_orderTab.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_orderTab.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_orderTab.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 1.0, 1.0,
				1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		orderTab.setLayout(gbl_orderTab);

		String[] columnNameInvoice = { "Invoice Number", "Total Cost($)",
				"Created (MM/DD/YYYY)", "Status" };

		Object[][] dataOrdering = {
				{ "012345", "500.99", "03/10/2014", "Submitted" },
				{ "123456", "567.33", "02/28/2014", "Shipped" },
				{ "234567", "730.98", "02/16/2014", "Completed" } };

		JScrollPane scrollPane1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane1 = new GridBagConstraints();
		gbc_scrollPane1.gridwidth = 2;
		gbc_scrollPane1.gridheight = 6;
		gbc_scrollPane1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane1.gridx = 0;
		gbc_scrollPane1.gridy = 2;
		orderTab.add(scrollPane1, gbc_scrollPane1);

		table1 = new JTable(dataOrdering, columnNameInvoice);
		table1.setModel(new DefaultTableModel(new Object[][] {
				{ "012345", "500.99", "03/10/2014", "Submitted" },
				{ "123456", "567.33", "02/28/2014", "Shipped" },
				{ "234567", "730.98", "02/16/2014", "Completed" }, },
				new String[] { "Invoice Number", "Total Cost($)",
						"Created (MM/DD/YYYY)", "Status" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane1.setViewportView(table1);

		JButton btnNewButton_1 = new JButton("Create Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD

=======
<<<<<<< HEAD
				//When Create Order button is clicked the following codes will execute
				//This codes will create a new tab caled NEW ORDER
				
				
=======
>>>>>>> FETCH_HEAD
				// When Create Order button is clicked the following codes will
				// execute
				// This codes will create a new tab caled NEW ORDER
				JPanel panel = new JPanel();
				mainTabbedPane.addTab("NEW ORDER", null, panel, null);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
				gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
				gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0,
						Double.MIN_VALUE };
				gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
						0.0, 1.0, Double.MIN_VALUE };
				panel.setLayout(gbl_panel);

				JLabel lblSupplier = new JLabel("Supplier: ");
				GridBagConstraints gbc_lblSupplier = new GridBagConstraints();
				gbc_lblSupplier.anchor = GridBagConstraints.EAST;
				gbc_lblSupplier.insets = new Insets(0, 0, 5, 5);
				gbc_lblSupplier.gridx = 0;
				gbc_lblSupplier.gridy = 0;
				panel.add(lblSupplier, gbc_lblSupplier);

				String[] supplierNames = connect.getSupplierNames();

				comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(supplierNames));
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 0;
				panel.add(comboBox, gbc_comboBox);

				String[] columnNamesOrdering = { "Item name", "Quantity",
						"Barcode" };

				Object[][] dataOrdering = { { "Banana", "0", "1234567890" },
						{ "Apple", "0", "0987654321" },
						{ "Bread", "0", "1592634870" }

				};
				table_2 = new JTable(dataOrdering, columnNamesOrdering);
				GridBagConstraints gbc_table_2 = new GridBagConstraints();
				gbc_table_2.insets = new Insets(5, 5, 50, 50);
				gbc_table_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_table_2.gridx = 1;
				gbc_table_2.gridy = 3;
				gbc_table_2.weightx = 15;

				panel.add(table_2, gbc_table_2);

<<<<<<< HEAD

				//When Create Order button is clicked the following codes will execute
				//This codes will create a new tab caled NEW ORDER
				
				

=======
>>>>>>> origin/master
>>>>>>> FETCH_HEAD
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 0;
		orderTab.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Automated Ordering");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 0;
		orderTab.add(btnNewButton_2, gbc_btnNewButton_2);

		JLabel lblNewLabel = new JLabel("Order Status");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		orderTab.add(lblNewLabel, gbc_lblNewLabel);

		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.anchor = GridBagConstraints.NORTH;
		gbc_list.gridheight = 4;
		gbc_list.gridwidth = 5;
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.gridx = 0;
		gbc_list.gridy = 5;
		orderTab.add(list, gbc_list);

		String[] columnNames = { "Item name", "Quantity", "Barcode" };

		Object[][] data = { { "Banana", "1020", "1234567890" },
				{ "Apple", "1122", "0987654321" } };

		JPanel inventoryTab = new JPanel();
		mainTabbedPane.addTab("Inventory", null, inventoryTab, null);
		GridBagLayout gbl_inventoryTab = new GridBagLayout();
		gbl_inventoryTab.columnWidths = new int[] { 0, 0 };
		gbl_inventoryTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_inventoryTab.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_inventoryTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		inventoryTab.setLayout(gbl_inventoryTab);

		JButton btnAutomatedOrdering = new JButton("Automated Ordering");
		GridBagConstraints gbc_btnAutomatedOrdering = new GridBagConstraints();
		gbc_btnAutomatedOrdering.anchor = GridBagConstraints.WEST;
		gbc_btnAutomatedOrdering.insets = new Insets(0, 0, 5, 0);
		gbc_btnAutomatedOrdering.gridx = 0;
		gbc_btnAutomatedOrdering.gridy = 0;
		inventoryTab.add(btnAutomatedOrdering, gbc_btnAutomatedOrdering);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		inventoryTab.add(scrollPane, gbc_scrollPane);

		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);

		JPanel supermarketTab = new JPanel();
		mainTabbedPane.addTab("Supplier", null, supermarketTab, null);

		JPanel accountTab = new JPanel();
		mainTabbedPane.addTab("Account", null, accountTab, null);
<<<<<<< HEAD

=======
<<<<<<< HEAD
>>>>>>> FETCH_HEAD
		
		final JPanel panel = new JPanel();
		mainTabbedPane.addTab("NEW ORDER", null, panel, null);
		mainTabbedPane.setBackgroundAt(4, new Color(0, 102, 0));
		mainTabbedPane.setForegroundAt(4, Color.BLACK);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 28, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		String [] supplierNames = connect.getSupplierNames();
		
		JLabel lblNewLabel_2 = new JLabel("Supplier:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(supplierNames));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridwidth = 7;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 6;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
<<<<<<< HEAD

=======
>>>>>>> FETCH_HEAD
		
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) 
		      {
		    	  System.out.println();
		    	  System.out.println("Selected: " + comboBox.getSelectedItem());
		    	  System.out.println(", Position: " + comboBox.getSelectedIndex());
		    	  
		    	  //update the JList to reflect the items of a specific supplier
		    	  itemsListForSupplier = connect.getSupplierItemsList(comboBox.getSelectedIndex());
		    	  
		    	  itemsListModel1.removeAllElements();
		    	  int counter = 0; 
		    	  while (counter < itemsListForSupplier.length)
		    	  {
		    		  	itemsListModel1.addElement(itemsListForSupplier[counter]);
		    	        counter++;
		    	  }
		    	  
		      }
		};
		
		comboBox.addActionListener(actionListener);
		
		
		JLabel lblNewLabel_1 = new JLabel("Select items to order:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 6;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		itemsListModel1 = new DefaultListModel();
		supplierItemsList = new JList(itemsListModel1);
		supplierItemsList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_supplierItemsList = new GridBagConstraints();
		gbc_supplierItemsList.gridwidth = 8;
		gbc_supplierItemsList.gridheight = 5;
		gbc_supplierItemsList.insets = new Insets(0, 0, 5, 5);
		gbc_supplierItemsList.fill = GridBagConstraints.BOTH;
		gbc_supplierItemsList.gridx = 3;
		gbc_supplierItemsList.gridy = 2;
		panel.add(supplierItemsList, gbc_supplierItemsList);
		
		
		
		JList orderList = new JList();
		orderList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_orderList = new GridBagConstraints();
		gbc_orderList.gridheight = 5;
		gbc_orderList.gridwidth = 8;
		gbc_orderList.insets = new Insets(0, 0, 5, 5);
		gbc_orderList.fill = GridBagConstraints.BOTH;
		gbc_orderList.gridx = 12;
		gbc_orderList.gridy = 2;
		panel.add(orderList, gbc_orderList);
		
		JButton btnNewButton_3 = new JButton(">>");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 11;
		gbc_btnNewButton_3.gridy = 4;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("<<");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 11;
		gbc_btnNewButton_4.gridy = 5;
		panel.add(btnNewButton_4, gbc_btnNewButton_4);
<<<<<<< HEAD
=======
=======

>>>>>>> origin/master
>>>>>>> FETCH_HEAD
	}
	
	

	public JComboBox getComboBox() {
		return comboBox;
	}
}
