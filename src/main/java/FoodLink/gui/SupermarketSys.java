package FoodLink.gui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Dialog.ModalityType;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
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
import javax.swing.SwingUtilities;

import FoodLink.Driver;
import FoodLink.Inventory;

import FoodLink.Driver;
import FoodLink.Inventory;

import FoodLink.Order;

import FoodLink.database;

import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Font;

public class SupermarketSys {

	public JFrame frame;

	private JTable orderStatusTable;
	private JTable table1;
	private JTable table;
	private JComboBox comboBox;
	private JComboBox supplierSelector;
	DefaultListModel orderListModel;
	private Object[][] itemsList;
	private String supplierName;
	private int supplier_id;
	private int numClick = 0;
	private int rowSelected = -1;
	private int prevRowSelected = -1;

	private String[] itemsColumnNames = { "Item Number", "Name", "Item Type",
			"Quantity", "Unit Price ($)", "Unit", "Total" };

	DefaultTableModel itemsListModel;

	private database connect = new database();
	DefaultListModel itemsListModel1;
	private JTable table_4;
	private JTextField grandTotalField;
	private JTextField textField;
	private int tabNumber = 0;
	private JTable inventoryTable;
	
	private double grandTotal = 0;
	
	private Order currentOrder;
	
	
	
	private String selectedRow= null;
	private int row;
	private JTable orderInformationTable;

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
	@SuppressWarnings("serial")
	private void initialize(final int supermarket_id, final boolean manager) {
		frame = new JFrame();
		LookAndFeel lookAndFeel = new LookAndFeel(frame);
		frame.setTitle("FoodLink");
		frame.setBounds(100, 100, 640, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color grey = new Color(153, 153, 153);
		Color green = new Color(182, 215, 168);
		frame.getContentPane().setBackground(green);
		Dimension d = new Dimension(1000,700);
		frame.setSize(d);
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

		GradientButton btnNewButton = new GradientButton("Log Out");
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

		GradientButton btnNewButton1 = new GradientButton("Log Out");
		
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

		final String[] columnNameInvoice = { "Invoice Number", "Supplier",
				"Total Cost($)", "Date/Time Created", "Status" };

		JLabel lblNewLabel = new JLabel("Order Status");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		orderTab.add(lblNewLabel, gbc_lblNewLabel);

		final JScrollPane scrollPane1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane1 = new GridBagConstraints();
		gbc_scrollPane1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane1.gridwidth = 2;
		gbc_scrollPane1.gridheight = 7;
		gbc_scrollPane1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane1.gridx = 0;
		gbc_scrollPane1.gridy = 2;
		orderTab.add(scrollPane1, gbc_scrollPane1);

		Object[][] orderList = connect.getOrderList(supermarket_id);

		final DefaultTableModel orderModel = new DefaultTableModel(orderList,
				columnNameInvoice) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		;
		orderStatusTable = new JTable(orderModel);
		orderStatusTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("CLICKING THE TABLE");
			}
		});

		scrollPane1.setViewportView(orderStatusTable);

		final GradientButton btnNewButton_1 = new GradientButton("Create Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When Create Order button is clicked the following codes will
				// execute
				
				// CODES FOR NEW ORDER PAGE
				// This codes will create a new tab called NEW ORDER
				final JPanel newOrder = new JPanel();
				// tabNumber++;
				// String tabName = "NEW ORDER " + tabNumber;
				btnNewButton_1.setEnabled(false);
				mainTabbedPane.addTab("NEW ORDER", null, newOrder, null);
				mainTabbedPane.setBackgroundAt(4, new Color(0, 0, 0));
				mainTabbedPane.setForegroundAt(4, new Color(255, 255, 255));
				GridBagLayout gbl_newOrder = new GridBagLayout();
				gbl_newOrder.columnWidths = new int[] { 0, 90, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 28, 0, 0, 0, 0, 0, 0, 0 };
				gbl_newOrder.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0 };
				gbl_newOrder.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0, 0.0,
						0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
						1.0, Double.MIN_VALUE };
				gbl_newOrder.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 0.0,
						0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
				newOrder.setLayout(gbl_newOrder);

				String[] supplierNames1 = connect.getSupplierNames();

				JLabel lblNewLabel_2 = new JLabel("Select Supplier:");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.gridwidth = 2;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 0;
				gbc_lblNewLabel_2.gridy = 0;
				newOrder.add(lblNewLabel_2, gbc_lblNewLabel_2);

				comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(supplierNames1));
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.anchor = GridBagConstraints.WEST;
				gbc_comboBox.gridwidth = 7;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.gridx = 2;
				gbc_comboBox.gridy = 0;
				newOrder.add(comboBox, gbc_comboBox);

				ActionListener actionListener1 = new ActionListener() {

					public void actionPerformed(ActionEvent actionEvent) {
						int index = mainTabbedPane.getSelectedIndex();
						mainTabbedPane.setTitleAt(index,
								(String) comboBox.getSelectedItem());

						supplierName = (String) comboBox.getSelectedItem();

						
						System.out.println("SUPPLIER INDEX: "
								+ comboBox.getSelectedIndex());
						itemsList = connect.getItemListForSupplier((comboBox
								.getSelectedIndex()+1));
						supplier_id = comboBox.getSelectedIndex()+1;
						itemsListModel = new DefaultTableModel(itemsList,
								itemsColumnNames) {
							Class[] columnTypes = new Class[] { String.class,
									String.class, String.class, BigDecimal.class,
									String.class, String.class, BigDecimal.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}

							boolean[] columnEditables = new boolean[] { false, false,
									false, true, false, false, false };

							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						};
						table_4.setModel(itemsListModel);
						table_4.getModel().addTableModelListener(
								new TableModelListener() {
									/**
							   * 
							   */
									public void tableChanged(TableModelEvent e) {
										// create order if customer has tried changing
										// order UI
										if (currentOrder == null) {
											currentOrder = new Order();
										}

										// ensures that only when updates to quantity
										// warrant a change to order
										if (e.getColumn() == 3) {
											if (currentOrder.updateOrder(Integer
													.parseInt(table_4.getValueAt(
															e.getFirstRow(), 3)
															.toString()), comboBox
													.getSelectedIndex(), e
													.getFirstRow()) == -1) {
												JOptionPane
														.showMessageDialog(
																frame,
																comboBox.getSelectedItem()
																		+ " does not have enough units of "
																		+ table_4
																				.getValueAt(
																						e.getFirstRow(),
																						1)
																		+ " to fulfil this order.");
												table_4.setValueAt(0, e.getFirstRow(),
														3); // override user's input and
															// reset value too 0
											} else {

												// creates big decimals with updated
												// values in order to multiply and set
												// total which is of big decimal type
												BigDecimal b = new BigDecimal(table_4
														.getValueAt(e.getFirstRow(), 3)
														.toString());
												b.abs();
												BigDecimal c = new BigDecimal(table_4
														.getValueAt(e.getFirstRow(), 4)
														.toString());
												c.abs();

												table_4.setValueAt(b.multiply(c),
														e.getFirstRow(), 6);
												grandTotal = 0;
												for (int i = 0; i < table_4
														.getRowCount(); i++) {
													if (table_4.getValueAt(i, 6) != "") {

														grandTotal += Double
																.valueOf(table_4
																		.getValueAt(i,
																				6)
																		.toString());
														System.out.println("gtotal: "
																+ grandTotal);
													}//
												}

												grandTotalField.setText(Double
														.toString(grandTotal));
											}
										}
									}
								});
					}
				};

				comboBox.addActionListener(actionListener1);

				JScrollPane newOrderTabScrollPane = new JScrollPane();
				GridBagConstraints scrollPaneGridBagLayout = new GridBagConstraints();
				scrollPaneGridBagLayout.gridheight = 8;
				scrollPaneGridBagLayout.gridwidth = 18;
				scrollPaneGridBagLayout.insets = new Insets(0, 0, 5, 0);
				scrollPaneGridBagLayout.fill = GridBagConstraints.BOTH;
				scrollPaneGridBagLayout.gridx = 0;
				scrollPaneGridBagLayout.gridy = 1;

				newOrder.add(newOrderTabScrollPane, scrollPaneGridBagLayout);

				itemsListModel = new DefaultTableModel(itemsList, itemsColumnNames);

				table_4 = new JTable(new DefaultTableModel());

				newOrder.add(newOrderTabScrollPane, scrollPaneGridBagLayout);

				itemsListModel = new DefaultTableModel(itemsList, itemsColumnNames);
				// {
				// boolean[] columnEditables = new boolean[] { false,
				// false, false, false};
				//
				// public boolean isCellEditable(int row, int column) {
				// return columnEditables[column];
				// }};

				table_4 = new JTable(new DefaultTableModel());
				table_4.setRowSelectionAllowed(false);
				newOrderTabScrollPane.setViewportView(table_4);

				JButton btnNewButton_4 = new JButton("Cancel Order");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						int n = JOptionPane.showConfirmDialog(frame,
								"Are you sure you want to cancel order?",
								"Cancel Order", JOptionPane.YES_NO_OPTION);

						System.out.println("ANSWER: " + n);

						int index = mainTabbedPane.indexOfTab((String) comboBox
								.getSelectedItem());
						System.out.println("INDEX OF TAB: " + index);

						if (index >= 0 && n == 0) {
							mainTabbedPane.remove(index);
							btnNewButton_1.setEnabled(true);
							currentOrder = null; // destroy order object
						}
					}
				});

				JLabel lblNewLabel_1 = new JLabel("GRAND TOTAL:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 14;
				gbc_lblNewLabel_1.gridy = 9;
				newOrder.add(lblNewLabel_1, gbc_lblNewLabel_1);

				grandTotalField = new JTextField();
				grandTotalField.setEditable(false);
				GridBagConstraints gbc_grandTotalField = new GridBagConstraints();
				gbc_grandTotalField.insets = new Insets(0, 0, 5, 5);
				gbc_grandTotalField.fill = GridBagConstraints.HORIZONTAL;
				gbc_grandTotalField.gridx = 16;
				gbc_grandTotalField.gridy = 9;
				newOrder.add(grandTotalField, gbc_grandTotalField);
				grandTotalField.setColumns(10);

				GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
				gbc_btnNewButton_4.anchor = GridBagConstraints.EAST;
				gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton_4.gridx = 14;
				gbc_btnNewButton_4.gridy = 11;
				newOrder.add(btnNewButton_4, gbc_btnNewButton_4);

				GradientButton submitNewOrderButton = new GradientButton("Submit Order");
				GridBagConstraints gbc_submitNewOrderButton = new GridBagConstraints();
				gbc_submitNewOrderButton.anchor = GridBagConstraints.EAST;
				gbc_submitNewOrderButton.insets = new Insets(0, 0, 0, 5);
				gbc_submitNewOrderButton.gridx = 16;
				gbc_submitNewOrderButton.gridy = 11;
				newOrder.add(submitNewOrderButton, gbc_submitNewOrderButton);
				
				
				submitNewOrderButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						int n = JOptionPane.showConfirmDialog(frame,
								"Are you sure you want to submit order?",
								"Cancel Order", JOptionPane.YES_NO_OPTION);

						System.out.println("ANSWER: " + n);

						int index = mainTabbedPane.indexOfTab((String) comboBox
								.getSelectedItem());
						System.out.println("INDEX OF TAB: " + index);

						// if answer is yes
						if (index >= 0 && n == 0) {
							mainTabbedPane.remove(index);
							btnNewButton_1.setEnabled(true);
							// add the new order in the database
							connect.addToOrderHistory(supplierName, grandTotal,
									"Submitted", supermarket_id, supplier_id);

							// refresh the orderListTable
							Object[][] orderList = connect.getOrderList(supermarket_id);

							final DefaultTableModel orderModel = new DefaultTableModel(
									orderList, columnNameInvoice) {
								boolean[] columnEditables = new boolean[] { false,
										false, false, false };

								public boolean isCellEditable(int row, int column) {
									return columnEditables[column];
								}
							};
							;
							orderStatusTable = new JTable(orderModel);
							//always makes the table clickable 
							
							orderStatusTable.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e)
								{
									numClick++; 
									System.out.println("CLICKING THE TABLE: " + numClick);
									
									rowSelected = orderStatusTable.getSelectedRow();
									System.out.println("ROW NUMBER SELECTED: " + rowSelected);
									
									if(numClick == 1)
									{
										prevRowSelected = rowSelected;
									}
									
									if((numClick == 2) && (rowSelected == prevRowSelected))
									{
										System.out.println("DOUBLE CLICK");
										numClick = 0;
									}
									
									if(numClick == 2)
									{
										System.out.println("Resetting the counter");
										numClick = 0;
									}
									
									
								}
							});


							scrollPane1.setViewportView(orderStatusTable);
							
							
							
							//link the items to the order number in the database
							int totalCol = table_4.getColumnCount();
							int totalRow = table_4.getRowCount();
							
							System.out.println("COL: " + totalCol + " ROW: " + totalRow);
							
							for (int row = 0; row < totalRow; row++)
							{
								//COLUMN
//								  invoice_number int NOT NULL generated always as identity, = lastIndex 
//								  name varchar(32), = itemName | 2
//								  item_type varchar(32), = itemType | 3
//								  quantity int, = quantity | 4
//								  unit_price decimal, = unitPrice | 5
//								  unit varchar(32), = unit | 6
//								  total decimal, = total | 7
//								  grandTotal decimal, = grandTotal
								String itemName = (String) table_4.getValueAt(row, 1);
								System.out.println("itemName: " + itemName);
								String item_type = (String) table_4.getValueAt(row, 2);
								int quantity;
								try
								{
									BigDecimal quants= new BigDecimal("" + table_4.getValueAt(row, 3));
									String quantityString = quants.toString();
									
									if(quantityString.equals(""))
									{
										quantity = 0;
									}
									
									else
										quantity = Integer.parseInt(quantityString);
								}
								
								catch (Exception e)
								{
									System.out.println("Setting quantity to 0");
									quantity = 0;
								}
									
								
								
								
								double unit_price = Double.parseDouble((String) table_4.getValueAt(row, 4));
								String unit = (String) table_4.getValueAt(row, 5);
								double total;
								try
								{
									BigDecimal totalCopy = new BigDecimal("" + table_4.getValueAt(row, 6));
									String totalString = totalCopy.toString();
									total = Double.parseDouble(totalString);
								}
								catch (Exception e)
								{
									System.out.println("Setting total to 0");
									total = 0;
								}
								
								//grandTotal;
								int lastIndex = connect.getLastElementInOrderHistory();
								connect.addOrderInformation(lastIndex, itemName, item_type, quantity, unit_price, unit, total, grandTotal);
								
								
							}
						}
					}
				}); //end of the new order code

			}// end of the new Order Tab code
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 0;
		orderTab.add(btnNewButton_1, gbc_btnNewButton_1);

		final GradientButton btnNewButton_2 = new GradientButton("Automated Ordering");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
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
		//final Object[][] data = connect.getSupermarketInventory(supermarket_id);
		
		//use this one when building
		final Object [][] data = {{"1","papples", "fruits", "5000", "2000", "lb"},{"2","apples", "fruits", "5000", "2000", "lb"},{"3","grapes", "fruits", "5000", "2000", "lb"},{"4","pears", "fruits", "5000", "2000", "lb"} };
		

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
		
		GradientButton btnAutomatedOrdering = new GradientButton("Automated Ordering");
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
				else
				{
					JOptionPane.showMessageDialog(null, "Please select an item in the table to automatically order.");
				}
			}
		});
		GridBagConstraints gbc_btnAutomatedOrdering = new GridBagConstraints();
		gbc_btnAutomatedOrdering.anchor = GridBagConstraints.WEST;
		gbc_btnAutomatedOrdering.insets = new Insets(0, 0, 5, 5);
		gbc_btnAutomatedOrdering.gridx = 0;
		gbc_btnAutomatedOrdering.gridy = 0;
		inventoryTab.add(btnAutomatedOrdering, gbc_btnAutomatedOrdering);
		
		JButton refreshInventoryButton = new JButton("Refresh");
		refreshInventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object [] [] data2 = connect.getSupermarketInventory(supermarket_id);
				table = new JTable(data2, columnNames);
				scrollPane.setViewportView(table);
			}
		});
		GridBagConstraints gbc_refreshInventoryButton = new GridBagConstraints();
		gbc_refreshInventoryButton.insets = new Insets(0, 0, 5, 5);
		gbc_refreshInventoryButton.gridx = 1;
		gbc_refreshInventoryButton.gridy = 0;
		inventoryTab.add(refreshInventoryButton, gbc_refreshInventoryButton);
		
		GradientButton saveChanges = new GradientButton("Save Current Row");
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
		
		final JComboBox supplierSelector = new JComboBox();
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
		
		GradientButton addToInventory = new GradientButton("Add Item to Inventory");
		addToInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow!=null){
					System.out.println("About to auto-order item on this row " + selectedRow);
					System.out.println("Is this the anser?? " + inventoryTable.getValueAt(row, 1));
					String [] item={(String) inventoryTable.getValueAt(row, 1),(String) inventoryTable.getValueAt(row, 2), "0", 
							(String) inventoryTable.getValueAt(row, 4), (String) inventoryTable.getValueAt(row, 5), (String) inventoryTable.getValueAt(row, 0)};
	
					
					if(item!=null) {
						connect.addSupermarketItem(item, supermarket_id);
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

		GradientButton btnComment = new GradientButton("Send Comment");
		GridBagConstraints gbc_btnComment = new GridBagConstraints();
		gbc_btnComment.anchor = GridBagConstraints.WEST;
		gbc_btnComment.insets = new Insets(0, 0, 0, 0);
		gbc_btnComment.gridx = 0;
		gbc_btnComment.gridy = 6;
		supermarketTab.add(btnComment, gbc_btnComment);
		
//		btnComment.addActionListener(new ActionListener() {
//			@Override
//			//role enforcement
//			if(!manager){
//				//cant create orders
//				btnNewButton_1.setVisible(false);
//				//cant automate orders
//				btnNewButton_2.setVisible(false);
//			}

	}

	public JComboBox getComboBox() {
		return comboBox;
	}
	
	private static final class GradientButton extends JButton{
        private GradientButton(){
            this.setText("");
            setContentAreaFilled(false);
        }
        private GradientButton(String str){
            this.setText(str);;
            setContentAreaFilled(false);
            
        }

        @Override
        protected void paintComponent(Graphics g){
            Graphics2D G2D = (Graphics2D)g.create();
            Color grey = new Color(153, 153, 153);
            G2D.setPaint(new GradientPaint(
                    new Point(0, 0), 
                    Color.white, 
                    new Point(0, getHeight()), 
                    grey));
            G2D.fillRect(0, 0, getWidth(), getHeight());
            G2D.dispose();

            super.paintComponent(g);
        }
	}
}
