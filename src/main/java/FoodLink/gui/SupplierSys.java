package FoodLink.gui;


import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;

import FoodLink.database;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Choice;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JToolBar;

import java.awt.Button;
import java.awt.Panel;

import javax.swing.JSplitPane;

import com.sun.java.swing.plaf.windows.resources.windows;

public class SupplierSys {

	public JFrame frame;
	private String [] supplier;
	private JTable table;
	private database connect = new database ();
	private String selectedRow= null;
	private String selectedUser= null;
	private int row;
	private boolean manager = true;
	private JTable table_1;
	private JTable inventoryTable;
	private JTable userTable;
	private final JScrollPane scrollPane_2 = new JScrollPane();	
	private JTable commentsTable;
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//hard code parameter to switch suppliers here (1-5)
					SupplierSys window = new SupplierSys(1, true);
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
	public SupplierSys(int supplier_id, boolean manager) {
		
		initialize(supplier_id, manager);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int supplier_id, boolean manager) {
		
		supplier = connect.getSpecSupplier(supplier_id);
		System.out.println("this is .." + supplier_id);
		frame = new JFrame();
		LookAndFeel lookAndFeel = new LookAndFeel(frame);
		//frame.setSize(new Dimension(1000,1000));
		frame.setTitle("FoodLink");
		//frame.setBounds(100, 100, 608, 300);
		frame.setBounds(100, 100, 640, 420);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		frame.setSize(new Dimension(1000,700));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
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
				}		}
		});
		
		JLabel lblNewLabel_1 = new JLabel(supplier[0]);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 17;
		gbc_btnNewButton.gridy = 0;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JTabbedPane mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_mainTabbedPane = new GridBagConstraints();
		gbc_mainTabbedPane.gridheight = 2;
		gbc_mainTabbedPane.gridwidth = 20;
		gbc_mainTabbedPane.fill = GridBagConstraints.BOTH;
		gbc_mainTabbedPane.gridx = 0;
		gbc_mainTabbedPane.gridy = 1;
		frame.getContentPane().add(mainTabbedPane, gbc_mainTabbedPane);
		/*
		gbc_mainTabbedPane.gridheight = 2;
		gbc_mainTabbedPane.gridwidth = 17;
		gbc_mainTabbedPane.insets = new Insets(0, 0, 0, 5);
		gbc_mainTabbedPane.fill = GridBagConstraints.BOTH;
		gbc_mainTabbedPane.gridx = 3;
		gbc_mainTabbedPane.gridy = 1;
		frame.getContentPane().add(mainTabbedPane, gbc_mainTabbedPane);
		*/
		JPanel orderTab = new JPanel();
		mainTabbedPane.addTab("Order", null, orderTab, null);
		GridBagLayout gbl_orderTab = new GridBagLayout();
		gbl_orderTab.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_orderTab.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_orderTab.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_orderTab.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		orderTab.setLayout(gbl_orderTab);
		
		GradientButton btnNewButton_1 = new GradientButton("Refresh Orders");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel lblNewLabel = new JLabel("Current Orders");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		orderTab.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		orderTab.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel inventoryTab = new JPanel();
		mainTabbedPane.addTab("Inventory", null, inventoryTab, null);
		GridBagLayout gbl_inventoryTab = new GridBagLayout();
		gbl_inventoryTab.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_inventoryTab.rowHeights = new int[]{0, 0, 0};
		gbl_inventoryTab.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0};
		gbl_inventoryTab.columnWidths = new int[]{0, 0, 0, 0};
		gbl_inventoryTab.rowHeights = new int[]{0, 0, 0};
		gbl_inventoryTab.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_inventoryTab.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		inventoryTab.setLayout(gbl_inventoryTab);
		
		
		
		final String[] columnNames = {"Item Number", "Item name", "Type", "Quantity", "Unit Price", "Units"};
		
		//@Production <--Nice tag to search for when swapping this code before running code
		//this one will access data from the the database but will cause the code not to work in design mode
		//use this one when testing
		final Object [][] data = connect.getInventory(supplier_id);
		
		//use this one when building
		//final Object [][] data = {{"1","papples", "fruits", "5000", "2000", "100 lb"},{"2","apples", "fruits", "5000", "2000", "200 lb"},{"3","grapes", "fruits", "5000", "2000", "40 lb"},{"4","pears", "fruits", "5000", "2000", "lb"} };
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		inventoryTab.add(scrollPane_1, gbc_scrollPane_1);
		
		inventoryTable = new JTable(data, columnNames);
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
		
		JButton btnDeleteCurrentRow = new JButton("Delete Current Row");
		btnDeleteCurrentRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedRow!=null)
				{
					System.out.println("About to delete this row " + selectedRow);
					connect.manageItems(null, Integer.parseInt(selectedRow), false);
					//getting data from the database
					Object [] [] data2 = connect.getInventory(supplier_id);
					//creating new table with the new data from the database
					inventoryTable = new JTable(data2, columnNames);
					//adding action listener on the new table 
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
					
				}
				else {System.out.println("selctedRow is null");}
				
			}
		});
		GridBagConstraints gbc_btnDeleteCurrentRow = new GridBagConstraints();
		gbc_btnDeleteCurrentRow.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteCurrentRow.gridx = 2;
		gbc_btnDeleteCurrentRow.gridy = 0;
		inventoryTab.add(btnDeleteCurrentRow, gbc_btnDeleteCurrentRow);
		
		GradientButton btnNewButton_3 = new GradientButton("Add New Item");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					{connect.manageItems(item, supplier_id, true);
					Object [] [] data2 = connect.getInventory(supplier_id);
					//creating a new table with new information
					inventoryTable = new JTable(data2, columnNames);
					//adding an action listener
					inventoryTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent mevt) {
							java.awt.Point point = mevt.getPoint();
							row =inventoryTable.rowAtPoint(point);
							selectedRow=(String) inventoryTable.getValueAt(row, 0);
							System.out.println(selectedRow);
							
							
						}
					});
					scrollPane_1.setViewportView(inventoryTable);}
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 0;
		inventoryTab.add(btnNewButton_3, gbc_btnNewButton_3);
		
		
		GradientButton btnSaveChanges = new GradientButton("Save Changes on Selected Row");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedRow!=null){
					System.out.println("About to save changes to this row " + selectedRow);
					System.out.println("Is this the anser?? " + inventoryTable.getValueAt(row, 1));
					String [] item={(String) inventoryTable.getValueAt(row, 1),(String) inventoryTable.getValueAt(row, 2), (String) inventoryTable.getValueAt(row, 3), (String) inventoryTable.getValueAt(row, 4)};
	
					
					if(item!=null)	
						{connect.modifyItem(item, Integer.parseInt(selectedRow));
						//getting information from the database
						Object [] [] data2 = connect.getInventory(supplier_id);
						//adding the information into a new table
						inventoryTable = new JTable(data2, columnNames);
						//adding an action listener to the table
						inventoryTable.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent mevt) {
								java.awt.Point point = mevt.getPoint();
								row =inventoryTable.rowAtPoint(point);
								selectedRow=(String) inventoryTable.getValueAt(row, 0);
								System.out.println(selectedRow);
								
								
							}
						});
						scrollPane_1.setViewportView(inventoryTable);}
					
					
					
				}
			}
		});
		GridBagConstraints gbc_btnSaveChanges = new GridBagConstraints();
		gbc_btnSaveChanges.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveChanges.gridx = 3;
		gbc_btnSaveChanges.gridy = 0;
		inventoryTab.add(btnSaveChanges, gbc_btnSaveChanges);
		 
		//enforcing roles
		if(!manager){
			//cant add items
			btnNewButton_3.setVisible(false);		
			//cant edit items
			btnSaveChanges.setVisible(false);
			//cant delete stuff..
			 btnDeleteCurrentRow.setVisible(false);
		 }
		
		JPanel supermarketTab = new JPanel();
		mainTabbedPane.addTab("Supermarket", null, supermarketTab, null);
		GridBagLayout gbl_supermarketTab = new GridBagLayout();
		gbl_supermarketTab.columnWidths = new int[]{0, 0};
		gbl_supermarketTab.rowHeights = new int[]{0, 0};
		gbl_supermarketTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_supermarketTab.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		supermarketTab.setLayout(gbl_supermarketTab);
		
		JScrollPane supermarketScroller = new JScrollPane();
		GridBagConstraints gbc_supermarketScroller = new GridBagConstraints();
		gbc_supermarketScroller.fill = GridBagConstraints.BOTH;
		gbc_supermarketScroller.gridx = 0;
		gbc_supermarketScroller.gridy = 0;
		supermarketTab.add(supermarketScroller, gbc_supermarketScroller);
		
		String[] commentColumnNames = {"name", "comment"};
		
		//@Production <--Nice tag to search for when swapping this code before running code
		//When building the gui
		Object[][] commentData = {{"This", "Is a comment"}};
		//When running the program
		//Object[][] commentData = connect.getSuplierComments(supplier_id);
		
		commentsTable = new JTable(commentData, commentColumnNames);
		supermarketScroller.setViewportView(commentsTable);
		
		JPanel accountTab = new JPanel();
		mainTabbedPane.addTab("Account", null, accountTab, null);
		
		
		/*Invoice tab*/	
		JPanel jpInvoices = new JPanel(new BorderLayout());
		mainTabbedPane.addTab("Invoices",null, jpInvoices, null);
		
		final ArrayList<Object[][]> listOrders = new ArrayList<Object[][]>();
		
		final Object[][] order1 = new Object[][]{
				{data[0][0], data[0][1], 2, data[0][4], 2 * Double.parseDouble((String) data[0][4])},
				{data[1][0], data[1][1], 2, data[1][4], 2 * Double.parseDouble((String) data[1][4])}
		};
		
		
		final Object[][] order2 = new Object[][]{
				{data[0][0], data[0][1], 20, data[0][4], 20 * Double.parseDouble((String) data[0][4])},
				{data[1][0], data[1][1], 12, data[1][4], 12 * Double.parseDouble((String) data[1][4])},
				{data[3][0], data[3][1], 4, data[3][4], 4 * Double.parseDouble((String) data[3][4])}
		};
		
		listOrders.add(order1);
		listOrders.add(order2);
		
		final String[] title = new String[] {"Item ID", "Item", "quantity", "Price($)", "Total($)"};
		final JTable jtInvoice = new JTable();

		jtInvoice.getTableHeader().setReorderingAllowed(false);
		final JComboBox jcbSupermarkets = new JComboBox(new String[] {"supermarket1","supermarket2"});
		jcbSupermarkets.setSelectedIndex(-1);

		//final JComboBox jcbSupermarkets = new JComboBox(new String[] {"supermarket1","supermarket2"});

		jcbSupermarkets.addActionListener(new ActionListener() {
			
			@SuppressWarnings("serial")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = jcbSupermarkets.getSelectedIndex();
				jtInvoice.setModel(new DefaultTableModel(listOrders.get(index),title) {
					boolean[] columnEditables = new boolean[] { false, false, false,
							false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			}
		});
		
		GradientButton jbPrint = new GradientButton("Save invoice and Open");
		jbPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(jcbSupermarkets.getSelectedIndex() >= 0){
						File file = new File("invoice_" + jcbSupermarkets.getSelectedItem()+ ".xls");
						TableModel model = jtInvoice.getModel();
						FileWriter out = new FileWriter(file);
						SimpleDateFormat sdf = new SimpleDateFormat("MMM. d, yyyy", java.util.Locale.ENGLISH);
						Double totalPrice = 0.0;
						
						out.write(supplier[0] + "\t\t\t" + jcbSupermarkets.getSelectedItem() + "\n");
						out.write(supplier[1] + "\t\t\t" + "SUPERMARKET ADDRESS" + "\n");
						out.write(supplier[2] + "\t\t\t" + "SUPERMARKET PHONE#" + "\n");
						out.write(supplier[3] + "\t\t\t" + sdf.format(java.util.Calendar.getInstance().getTime()) + "\n");
						out.write("\n\n");
						
						for(int i = 0; i < model.getColumnCount(); i++){
							out.write(model.getColumnName(i) + "\t");
						}
						
						out.write("\n");
						for(int i=0; i < model.getRowCount();i++){
							for(int j=0;j < model.getColumnCount();j++){
								if (j == 4){
									totalPrice += (Double) model.getValueAt(i, j);
								}
								out.write(model.getValueAt(i,j).toString() + "\t");
							}
								out.write("\n");
						}
						
						out.write("\n");
						out.write("\t\t\tTOTAL:\t" + totalPrice + "\n");
						out.close();
						
						String osname = System.getProperty("os.name").toLowerCase();
						Runtime r = Runtime.getRuntime();
						if(osname.contains("win")){
							r.exec("cmd.exe /c start " + file);
						} else if (osname.contains("mac") || osname.contains("linux")){
							r.exec("open "+ file);
						} else{
							JOptionPane.showMessageDialog(frame, "Operating System not supported for printing");
						}						
						/*THE FOLLOWING LINE PRINTS OFF THE TABLE DIRECTLY*/
						//jtInvoice.print(JTable.PrintMode.NORMAL);
					}
					else{
						JOptionPane.showMessageDialog(frame, "Please select an invoice to print");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JScrollPane jspInvoice = new JScrollPane(jtInvoice);
		jpInvoices.add(jcbSupermarkets, BorderLayout.NORTH);
		jpInvoices.add(jspInvoice, BorderLayout.CENTER);
		JPanel jpSouth = new JPanel(new FlowLayout());
		jpSouth.add(jbPrint);
		jpInvoices.add(jpSouth,BorderLayout.SOUTH);
		
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
		
			setTable(users, supplier_id);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String [] user = null;
					try {
						AddUser window = new AddUser(supplier_id);
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
						connect.manageSupplierUsers(user, supplier_id, true);
						window.setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					
					if(user[0]!=null)	
						{//resetting the table
							setTable(users, supplier_id);
						}
					
					}
				});
				
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("about to delete this row.."+ selectedUser);
						String [] user = {selectedUser};
						//passing the user name, the supplier_id and false to trigger the delete query
						connect.manageSupplierUsers(user, supplier_id, false);
						setTable(users, supplier_id);
					}
		
				
				});	
		
		}
	}


	protected void setTable(String [] users, int supplier_id) {
		
		//@Production <--Nice tag to search for when swapping this code before running code
		//use this one when testing
		final Object[][] userData = connect.getUser(supplier_id, false);
				
		//use this one when building
		//final Object [][] userData = {{"Josh", "true" },{"Tom", "false" },{"Jayceon", "true" },{"J-Mello", "false" } };
				
		userTable = new JTable(userData, users);
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mevt) {
				java.awt.Point point = mevt.getPoint();
				row =userTable.rowAtPoint(point);
				selecedUser=(String)userTable.getValueAt(row, 0);
				System.out.println(selectedUser);
				
				
			}
		});
		scrollPane_2.setViewportView(userTable);
		
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
