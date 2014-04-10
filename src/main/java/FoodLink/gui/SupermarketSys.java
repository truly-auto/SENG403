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
import javax.swing.JToolBar;
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

	private JTable table1;
	private JTable table;
	private JComboBox<String> comboBox;
	private JComboBox<String> supplierSelector;
	DefaultListModel<String> orderListModel;
	private Object[][] itemsList;
	private String supplierName;

	private String[] itemsColumnNames = { "Item Number", "Name", "Item Type",
			"Quantity", "Unit Price ($)", "Unit", "Total" };

	DefaultTableModel itemsListModel;

	private database connect = new database();
	DefaultListModel<String> itemsListModel1;
	private JTable table_4;
	private JTextField textField;
	private JTable inventoryTable;
	
	private double grandTotal = 0;
	
	private Order currentOrder;
	private final JScrollPane scrollPane = new JScrollPane();
	
	//variables for managers to add and remove users
	private String selectedUser= null;
	private JTable userTable;
	private final JScrollPane scrollPane_2 = new JScrollPane();	
	//--

	
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
		
		
		final GradientButton btnNewButton_1 = new GradientButton("Create Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When Create Order button is clicked the following codes will
				// execute
				
				/**
				 * HEREE!!!!!!!!!!!!!!
				 */
				//currentOrder = new Order();
				//currentOrder.addItem();
				
				
				
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
						table_4.getModel().addTableModelListener(new TableModelListener() {
							  /**
							   * 
							   */
						      public void tableChanged(TableModelEvent e) {
						         // create order if customer has tried changing order UI
						    	  if (currentOrder == null)
						    	  {
						    		  currentOrder = new Order();						    		  
						    	  }
						    	  
						    	  // ensures that only when updates to quantity warrant a change to order
						    	  if (e.getColumn() == 3)
						    	  {
						    		  if (currentOrder.updateOrder(Integer.parseInt(table_4.getValueAt(e.getFirstRow(), 3).toString()), comboBox.getSelectedIndex(), e.getFirstRow()) == -1)
						    		  {
						    			  JOptionPane.showMessageDialog(frame, comboBox.getSelectedItem() + " does not have enough units of " + table_4.getValueAt(e.getFirstRow(), 1) + " to fulfil this order.");
						    			  table_4.setValueAt(0, e.getFirstRow(), 3);	// override user's input and reset value too 0
						    		  }
						    		  else {
						    			  
						    		  
							    		 // creates big decimals with updated values in order to multiply and set total which is of big decimal type
							    		  BigDecimal b = new BigDecimal(table_4.getValueAt(e.getFirstRow(), 3).toString());
							    		  b.abs();
							    		  BigDecimal c = new BigDecimal(table_4.getValueAt(e.getFirstRow(), 4).toString());
							    		  c.abs();
							    		  
							    		  table_4.setValueAt(b.multiply(c), e.getFirstRow(), 6);
							    		  grandTotal = 0;
							    		  for (int i = 0; i < table_4.getRowCount(); i++) {
								    		  if (table_4.getValueAt(i,6) != "")
								    		  {
								    			  
								    			  grandTotal += Double.valueOf(table_4.getValueAt(i, 6).toString());
								    			  System.out.println("gtotal: " + grandTotal);
								    		  }//
							    		  }
							    		  
							    		  textField.setText(Double.toString(grandTotal));
						    		  }
						    	  }
						      }
						});
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

						int index = mainTabbedPane.indexOfTab((String) comboBox.getSelectedItem());
						System.out.println("INDEX OF TAB: " + index);

						if (index >= 0 && n == 0) {
							mainTabbedPane.remove(index);
							btnNewButton_1.setEnabled(true);
							currentOrder = null;	// destroy order object
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

				GradientButton btnNewButton_3 = new GradientButton("Submit Order");
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

						int index = mainTabbedPane.indexOfTab((String) comboBox.getSelectedItem());
						System.out.println("INDEX OF TAB: " + index);
						
						if (index >= 0 && n == 0) {
							mainTabbedPane.remove(index);
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

		GradientButton btnNewButton_2 = new GradientButton("Automated Ordering");
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

		
		
		JPanel inventoryTab = new JPanel();
		mainTabbedPane.addTab("Inventory", null, inventoryTab, null);
		GridBagLayout gbl_inventoryTab = new GridBagLayout();
		gbl_inventoryTab.columnWidths = new int[] {0, 0, 0, 30, 0};
		gbl_inventoryTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_inventoryTab.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_inventoryTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		inventoryTab.setLayout(gbl_inventoryTab);
		
		

		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		inventoryTab.add(scrollPane, gbc_scrollPane);
		
		setInvetoryTable(supermarket_id);

		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("about to delete .. " + selectedRow);
				connect.deleteInvetory(Integer.parseInt(selectedRow));
				//reset table and add action listener
				setInvetoryTable(supermarket_id);

				
				
			}
		});
		GridBagConstraints gbc_btnDeleteItem = new GridBagConstraints();
		gbc_btnDeleteItem.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteItem.gridx = 1;
		gbc_btnDeleteItem.gridy = 0;
		inventoryTab.add(btnDeleteItem, gbc_btnDeleteItem);
		
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
				setInvetoryTable(supermarket_id);
			}
		});
		GridBagConstraints gbc_refreshInventoryButton = new GridBagConstraints();
		gbc_refreshInventoryButton.insets = new Insets(0, 0, 5, 5);
		gbc_refreshInventoryButton.gridx = 2;
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
					setInvetoryTable(supermarket_id);
				}
			}
		});
		GridBagConstraints gbc_saveChanges = new GridBagConstraints();
		gbc_saveChanges.insets = new Insets(0, 0, 5, 5);
		gbc_saveChanges.gridx = 3;
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
		
		btnComment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				SendCommentWindow commentWindow = new SendCommentWindow();
				commentWindow.setLocationRelativeTo((JFrame)SwingUtilities.getRoot((Component)arg0.getSource()));
				commentWindow.setModalityType(ModalityType.APPLICATION_MODAL);
				commentWindow.setVisible(true);
				
				if(commentWindow.valid()){
					connect.addComment(commentWindow.getComment(), comboBox.getSelectedIndex(), supermarket_id);
				}
				
			}
		});
		
		
		
		// role enforcement
		if (!manager) {
			// cant create orders
			btnNewButton_1.setVisible(false);
			//cant automate orders
			btnNewButton_2.setVisible(false);
			//can't delete
			btnDeleteItem.setVisible(false); 
			//cant make changes
			saveChanges.setVisible(false);
			//can't make automated orders
			btnNewButton_2.setVisible(false); 
			btnAutomatedOrdering.setVisible(false); 
		}
		//code to allow managers to add and remove users
		if(manager){	
			JToolBar toolBar = new JToolBar();
			mainTabbedPane.addTab("Users", null, toolBar, null);
			
			JPanel panel_1 = new JPanel();
			toolBar.add(panel_1);
			
			Button button_2 = new Button("Delete User");
			panel_1.add(button_2);
			
			Button button = new Button("Add User");
			panel_1.add(button);
			
			toolBar.add(scrollPane_2);
			
			final String[] users = {"User name", "Privileges"};
		
			setTable(users, supermarket_id);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String [] user = null;
					try {
						AddUser window = new AddUser(supermarket_id);
						/**
						 * Poorly coded to avoid having to refactor this whole class for now
						 * (Instead of turning into a JDialog, making JDialog inherit conentpane of frame)
						 */
						window.setContentPane(window.frame.getContentPane());
						window.setSize(new Dimension(500, 300));
						
						window.setModalityType(ModalityType.APPLICATION_MODAL);	
						//window.frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
						//window.frame.setVisible(true);
						window.setVisible(true);
						user = window.getResult();
						connect.manageSupermarketUsers(user, supermarket_id, true);
						window.setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					
					if(user[0]!=null)	
						{//resetting the table
							setTable(users, supermarket_id);
						}
					
					}
				});
				
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("about to delete this row.."+ selectedUser);
						String [] user = {selectedUser};
						//passing the user name, the supermarket_id and false to trigger the delete query
						connect.manageSupermarketUsers(user, supermarket_id, false);
						setTable(users, supermarket_id);
					}
		
				
				});	
		
		}
		// CODES FOR NEW ORDER PAGE

	}

	private void setInvetoryTable(int supermarket_id) {
		
		final String[] columnNames = {"Item Number", "Item name", "Type", "Quantity", "Unit Price ($)", "Unit"};
		
		//@Production <--Nice tag to search for when swapping this code before running code
		//this one will access data from the the database but will cause the code not to work in design mode
		//use this one when testing
		final Object[][] data = connect.getSupermarketInventory(supermarket_id);
				
		//use this one when building
		//final Object [][] data = {{"1","papples", "fruits", "5000", "2000", "lb"},{"2","apples", "fruits", "5000", "2000", "lb"},{"3","grapes", "fruits", "5000", "2000", "lb"},{"4","pears", "fruits", "5000", "2000", "lb"} };
		
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
		
	}

	protected void setTable(String[] users, int supermarket_id) {
		//use this one when testing
				//
				final Object[][] userData = connect.getUser(supermarket_id, true);
						
				//use this one when building
				//final Object [][] userData = {{"Josh", "true" },{"Tom", "false" },{"Jayceon", "true" },{"J-Mello", "false" } };
						
				userTable = new JTable(userData, users);
				userTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent mevt) {
						java.awt.Point point = mevt.getPoint();
						row =userTable.rowAtPoint(point);
						selectedUser=(String)userTable.getValueAt(row, 0);
						System.out.println(selectedUser);
						
						
					}
				});
				scrollPane_2.setViewportView(userTable);
				
		
	}

	public JComboBox<String> getComboBox() {
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
