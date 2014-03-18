package FoodLink.gui;

import java.awt.EventQueue;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Dialog.ModalityType;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;

import FoodLink.Inventory;


import FoodLink.database;

import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JTextField;

import java.awt.GridLayout;

public class SupermarketSys {

	public JFrame frame;

	private JTable table1;
	private JTable table;
	private JComboBox comboBox;
	private JComboBox supplierSelector;
	DefaultListModel orderListModel;
	private Object[][] itemsList;

	private String[] itemsColumnNames = {"Item Number", "Name", "Item Type", "Quantity", "Unit Price ($)", "Unit", "Total"};

	DefaultTableModel itemsListModel;

	private database connect = new database();
	DefaultListModel itemsListModel1;
	private JTable table_4;
	private JTextField textField;
	private int tabNumber = 0;
	private JTable inventoryTable;
	
	private String selectedRow= null;
	private int row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//hard code parameter to swicth suppliers here (1-5) same as SupplierSys
					SupermarketSys window = new SupermarketSys(1, true);
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
	public SupermarketSys(int supermarket_id, boolean manager) {
		initialize(supermarket_id, manager);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int supermarket_id, final boolean manager) {
		frame = new JFrame();
		//LookAndFeel lookAndFeel = new LookAndFeel(frame);
		frame.setTitle("FoodLink");
		frame.setBounds(100, 100, 640, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color grey = new Color(153, 153, 153);
		Color green = new Color(182, 215, 168);
		frame.getContentPane().setBackground(green);
		BufferedImage Logo = null;
		JPanel banner = new JPanel();
		try 
		{
		    Logo = ImageIO.read(new File("src/main/resources/images/Logo17.JPG")); // put icon image here
		    JLabel LogoPanel = new JLabel(new ImageIcon( Logo ));
			banner.add(LogoPanel);
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		banner.setBackground(green);
		frame.getContentPane().add(banner);
		frame.setBackground(green);
		
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

		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				try {
					Login window = new Login();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}		
				 }
		});

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 0;
		frame.getContentPane().add(label, gbc_label);

		JButton btnNewButton1 = new JButton("Log Out");
		btnNewButton1.setBackground(grey);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frame.dispose();
				try {
					Login window = new Login();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}		}
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
		gbl_orderTab.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_orderTab.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_orderTab.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0,
				1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		orderTab.setLayout(gbl_orderTab);

		String[] columnNameInvoice = { "Invoice Number", "Total Cost($)",
				"Date/Time Created", "Status" };

		Object[][] dataOrdering = {
				{ "012345", "500.99", "03/10/2014", "Submitted" },
				{ "123456", "567.33", "02/28/2014", "Shipped" },
				{ "234567", "730.98", "02/16/2014", "Completed" } };
		
				JLabel lblNewLabel = new JLabel("Order Status");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 1;
				orderTab.add(lblNewLabel, gbc_lblNewLabel);

		JScrollPane scrollPane1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane1 = new GridBagConstraints();
		gbc_scrollPane1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane1.gridwidth = 2;
		gbc_scrollPane1.gridheight = 7;
		gbc_scrollPane1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane1.gridx = 0;
		gbc_scrollPane1.gridy = 2;
		orderTab.add(scrollPane1, gbc_scrollPane1);

		Object[][] orderList = connect.getOrderList();

		DefaultTableModel orderModel = new DefaultTableModel(orderList, columnNameInvoice){
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};;
		table1 = new JTable(orderModel);

		scrollPane1.setViewportView(table1);

		final JButton btnNewButton_1 = new JButton("Create Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When Create Order button is clicked the following codes will
				// execute

				// This codes will create a new tab called NEW ORDER
				final JPanel newOrder = new JPanel();
				// tabNumber++;
				// String tabName = "NEW ORDER " + tabNumber;
				btnNewButton_1.setEnabled(false);
				mainTabbedPane.addTab("NEW ORDER", null, newOrder, null);
				mainTabbedPane.setBackgroundAt(4, new Color(0, 0, 0));
				mainTabbedPane.setForegroundAt(4, new Color(255, 255, 255));
				GridBagLayout gbl_newOrder = new GridBagLayout();
				gbl_newOrder.columnWidths = new int[] { 0, 90, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 28, 0, 0, 0, 0, 0, 0, 0 };
				gbl_newOrder.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0 };
				gbl_newOrder.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0,
						0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
						1.0, 1.0, 1.0, Double.MIN_VALUE };
				gbl_newOrder.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0,
						0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0,
						Double.MIN_VALUE };
				newOrder.setLayout(gbl_newOrder);

				String[] supplierNames = connect.getSupplierNames();

				JLabel lblNewLabel_2 = new JLabel("Select Supplier:");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.gridwidth = 2;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 0;
				gbc_lblNewLabel_2.gridy = 0;
				newOrder.add(lblNewLabel_2, gbc_lblNewLabel_2);

				comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(supplierNames));
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.anchor = GridBagConstraints.WEST;
				gbc_comboBox.gridwidth = 7;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.gridx = 2;
				gbc_comboBox.gridy = 0;
				newOrder.add(comboBox, gbc_comboBox);

				ActionListener actionListener = new ActionListener() {

					public void actionPerformed(ActionEvent actionEvent) {
						int index = mainTabbedPane.getSelectedIndex();
						mainTabbedPane.setTitleAt(index, (String) comboBox.getSelectedItem());
		            
						System.out.println("SUPPLIER INDEX: "
								+ comboBox.getSelectedIndex());
						itemsList = connect.getItemListForSupplier(comboBox
								.getSelectedIndex());
						itemsListModel = new DefaultTableModel(itemsList,
								itemsColumnNames) {
							Class[] columnTypes = new Class[] { String.class,
									String.class, String.class, BigDecimal.class,
									String.class, String.class, BigDecimal.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] columnEditables = new boolean[] { false,
									false, false, true, false, false, false };

							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						};
						table_4.setModel(itemsListModel);
					}
				};

				comboBox.addActionListener(actionListener);

				JScrollPane scrollPane_1 = new JScrollPane();
				GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
				gbc_scrollPane_1.gridheight = 8;
				gbc_scrollPane_1.gridwidth = 18;
				gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_1.gridx = 0;
				gbc_scrollPane_1.gridy = 1;

				newOrder.add(scrollPane_1, gbc_scrollPane_1);

				itemsListModel = new DefaultTableModel(itemsList,
						itemsColumnNames);

				table_4 = new JTable(new DefaultTableModel());


				newOrder.add(scrollPane_1, gbc_scrollPane_1);	

				itemsListModel = new DefaultTableModel(itemsList, itemsColumnNames);
//				{
//					boolean[] columnEditables = new boolean[] { false,
//							false, false, false};
//
//					public boolean isCellEditable(int row, int column) {
//						return columnEditables[column];
//				}};

				table_4 = new JTable(new DefaultTableModel());
				table_4.setRowSelectionAllowed(false);
				scrollPane_1.setViewportView(table_4);

				JButton btnNewButton_4 = new JButton("Cancel Order");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						int n = JOptionPane.showConfirmDialog(frame,
								"Are you sure you want to cancel order?",
								"Cancel Order", JOptionPane.YES_NO_OPTION);

						System.out.println("ANSWER: " + n);

						int index = mainTabbedPane.indexOfTab("NEW ORDER");

						if (index >= 0 && n == 0) {
							mainTabbedPane.remove(newOrder);
							btnNewButton_1.setEnabled(true);
						}
					}
				});

				JLabel lblNewLabel_1 = new JLabel("GRAND TOTAL:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 14;
				gbc_lblNewLabel_1.gridy = 9;
				newOrder.add(lblNewLabel_1, gbc_lblNewLabel_1);

				textField = new JTextField();
				textField.setEditable(false);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 5, 5);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 16;
				gbc_textField.gridy = 9;
				newOrder.add(textField, gbc_textField);
				textField.setColumns(10);
				GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
				gbc_btnNewButton_4.anchor = GridBagConstraints.EAST;
				gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton_4.gridx = 14;
				gbc_btnNewButton_4.gridy = 11;
				newOrder.add(btnNewButton_4, gbc_btnNewButton_4);

				JButton btnNewButton_3 = new JButton("Submit Order");
				GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
				gbc_btnNewButton_3.anchor = GridBagConstraints.EAST;
				gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton_3.gridx = 16;
				gbc_btnNewButton_3.gridy = 11;
				newOrder.add(btnNewButton_3, gbc_btnNewButton_3);
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						int n = JOptionPane.showConfirmDialog(frame,
								"Are you sure you want to submit order?",
								"Cancel Order", JOptionPane.YES_NO_OPTION);

						System.out.println("ANSWER: " + n);

						int index = mainTabbedPane.indexOfTab("NEW ORDER");

						if (index >= 0 && n == 0) {
							mainTabbedPane.remove(newOrder);
							btnNewButton_1.setEnabled(true);
						}
					}
				});
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

		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.anchor = GridBagConstraints.NORTH;
		gbc_list.gridheight = 4;
		gbc_list.gridwidth = 5;
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.gridx = 0;
		gbc_list.gridy = 6;
		orderTab.add(list, gbc_list);

		final String[] columnNames = {"Item Number", "Item name", "Type", "Quantity", "Unit Price ($)", "Unit"};
		
		//this one will access data from the the database but will cause the code not to work in design mode
		//use this one when testing
		final Object[][] data = connect.getSupermarketInventory(supermarket_id);
		
		//use this one when building
		//final Object [][] data = {{"1","papples", "fruits", "5000", "2000", "lb"},{"2","apples", "fruits", "5000", "2000", "lb"},{"3","grapes", "fruits", "5000", "2000", "lb"},{"4","pears", "fruits", "5000", "2000", "lb"} };
		

		JPanel inventoryTab = new JPanel();
		mainTabbedPane.addTab("Inventory", null, inventoryTab, null);
		GridBagLayout gbl_inventoryTab = new GridBagLayout();
		gbl_inventoryTab.columnWidths = new int[] {0, 0, 30, 0};
		gbl_inventoryTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_inventoryTab.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_inventoryTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		inventoryTab.setLayout(gbl_inventoryTab);

		final JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		inventoryTab.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(data, columnNames);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mevt) {
				java.awt.Point point = mevt.getPoint();
				row =table.rowAtPoint(point);
				selectedRow=(String) table.getValueAt(row, 0);
				System.out.println(selectedRow);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAutomatedOrdering = new JButton("Automated Ordering");
		btnAutomatedOrdering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow!=null){
					System.out.println("About to auto-order item on this row " + selectedRow);
					System.out.println("Is this the anser?? " + table.getValueAt(row, 1));
	
					String [] item=null;
					try {
						AutomatedOrdering window = new AutomatedOrdering((String) table.getValueAt(row, 1), (String) table.getValueAt(row, 5));
						window.setModalityType(ModalityType.APPLICATION_MODAL);
						window.setVisible(true);
						
						item =window.getResult();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					if(item!=null) {
						connect.addAutomaticOrder(item, Integer.parseInt(selectedRow));
					}
					
					scrollPane.setViewportView(table);
				}
			}
		});
		GridBagConstraints gbc_btnAutomatedOrdering = new GridBagConstraints();
		gbc_btnAutomatedOrdering.anchor = GridBagConstraints.WEST;
		gbc_btnAutomatedOrdering.insets = new Insets(0, 0, 5, 5);
		gbc_btnAutomatedOrdering.gridx = 0;
		gbc_btnAutomatedOrdering.gridy = 0;
		inventoryTab.add(btnAutomatedOrdering, gbc_btnAutomatedOrdering);
		
		JButton newInventoryItem = new JButton("New Custom Item");
		newInventoryItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String [] item=null;
				try {
					AddItem window = new AddItem();
					window.setModalityType(ModalityType.APPLICATION_MODAL);
					window.setVisible(true);
					
					item =window.getResult();
				} catch (Exception e1) {
					e1.printStackTrace();
				}		
				
				if(item!=null)	
					{connect.addSupermarketItem(item, supermarket_id, true);
					Object [] [] data2 = connect.getSupermarketInventory(supermarket_id);
					table = new JTable(data2, columnNames);
					scrollPane.setViewportView(table);}
			}
		});
		GridBagConstraints gbc_newInventoryItem = new GridBagConstraints();
		gbc_newInventoryItem.insets = new Insets(0, 0, 5, 5);
		gbc_newInventoryItem.gridx = 1;
		gbc_newInventoryItem.gridy = 0;
		inventoryTab.add(newInventoryItem, gbc_newInventoryItem);
		
		JButton saveChanges = new JButton("Save Current Row");
		saveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedRow!=null){
					System.out.println("About to save changes to this row " + selectedRow);
					System.out.println("Is this the anser?? " + table.getValueAt(row, 1));
					String [] item={(String) table.getValueAt(row, 1),(String) table.getValueAt(row, 2), (String) table.getValueAt(row, 3), (String) table.getValueAt(row, 4), (String) table.getValueAt(row, 5)};
	
					
					if(item!=null) {
						Inventory inventory = new Inventory();
						inventory.editItem(item, Integer.parseInt(selectedRow));
						//connect.modifySupermarketItem(item, Integer.parseInt(selectedRow));
					}
					Object [] [] data2 = connect.getSupermarketInventory(supermarket_id);
					table = new JTable(data2, columnNames);
					scrollPane.setViewportView(table);
				}
			}
		});
		GridBagConstraints gbc_saveChanges = new GridBagConstraints();
		gbc_saveChanges.insets = new Insets(0, 0, 5, 5);
		gbc_saveChanges.gridx = 2;
		gbc_saveChanges.gridy = 0;
		inventoryTab.add(saveChanges, gbc_saveChanges);

		JPanel accountTab = new JPanel();
		mainTabbedPane.addTab("Account", null, accountTab, null);
		
		JPanel supermarketTab = new JPanel();
		mainTabbedPane.addTab("Supplier", null, supermarketTab, null);
		GridBagLayout gbl_supermarketTab = new GridBagLayout();
		gbl_supermarketTab.columnWidths = new int[] {0, 30, 30, 0};
		gbl_supermarketTab.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_supermarketTab.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_supermarketTab.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		supermarketTab.setLayout(gbl_supermarketTab);
		
		final String[] supplierTableColumnNames = {"Item Number", "Item name", "Type", "Quantity", "Unit Price ($)", "Units"};
		String [] supplierNames = connect.getSupplierNames();
		
		supplierSelector = new JComboBox();
		supplierSelector.setModel(new DefaultComboBoxModel(supplierNames));
		GridBagConstraints gbc_supplierSelector = new GridBagConstraints();
		gbc_supplierSelector.insets = new Insets(0, 0, 5, 5);
		gbc_supplierSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_supplierSelector.gridx = 0;
		gbc_supplierSelector.gridy = 0;
		supermarketTab.add(supplierSelector, gbc_supplierSelector);
		
		ActionListener actionListener = new ActionListener() {
			 public void actionPerformed(ActionEvent actionEvent)
			 {
				 System.out.println("SUPPLIER INDEX: " + (supplierSelector.getSelectedIndex() + 1));
				 itemsList = connect.getSupplierInventory(supplierSelector.getSelectedIndex()+1);
				 itemsListModel = new DefaultTableModel(itemsList, supplierTableColumnNames){
						Class[] columnTypes = new Class[] {
								String.class, String.class, String.class, String.class, String.class, String.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							boolean[] columnEditables = new boolean[] {
								false, false, false, false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						};
				 inventoryTable.setModel(itemsListModel);
			 }
		};
		supplierSelector.addActionListener(actionListener);
		
		JButton addToInventory = new JButton("Add Item to Inventory");
		addToInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow!=null){
					System.out.println("About to auto-order item on this row " + selectedRow);
					System.out.println("Is this the anser?? " + inventoryTable.getValueAt(row, 1));
					String [] item={(String) inventoryTable.getValueAt(row, 1),(String) inventoryTable.getValueAt(row, 2), "0", 
							(String) inventoryTable.getValueAt(row, 4), (String) inventoryTable.getValueAt(row, 5), (String) inventoryTable.getValueAt(row, 0)};
	
					
					if(item!=null) {
						connect.addSupermarketItem(item, supermarket_id, false);
					}
				}
			}
		});
		GridBagConstraints gbc_addToInventory = new GridBagConstraints();
		gbc_addToInventory.insets = new Insets(0, 0, 5, 0);
		gbc_addToInventory.gridx = 2;
		gbc_addToInventory.gridy = 0;
		supermarketTab.add(addToInventory, gbc_addToInventory);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 5;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 2;
		supermarketTab.add(scrollPane_1, gbc_scrollPane_1);
			
		inventoryTable = new JTable();
		inventoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mevt) {
				java.awt.Point point = mevt.getPoint();
				row =inventoryTable.rowAtPoint(point);
				selectedRow=(String) inventoryTable.getValueAt(row, 0);
				System.out.println(selectedRow);
			}
		});
		scrollPane_1.setViewportView(inventoryTable);

		//role enforcement
		if(!manager){
			//cant create orders
			btnNewButton_1.setVisible(false);
			//cant automate orders
			btnNewButton_2.setVisible(false);
		}
		// CODES FOR NEW ORDER PAGE

	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}
