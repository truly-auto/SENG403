package FoodLink.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SupplierSys {

	public JFrame frame;
	private JTable table;
	private database connect = new database ();
	private String selectedRow= null;
	private int row;
	

private JTable table_1;
private JTable table_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//hard code parameter to swicth suppliers here (1-5)
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
		
		String [] supplier = connect.getSpecSupplier(supplier_id);
		System.out.println("this is .." + supplier_id);
		frame = new JFrame();
		frame.setTitle("FoodLink");
		frame.setBounds(100, 100, 608, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
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
				}		}
		});
		
		JLabel lblNewLabel_1 = new JLabel(supplier[0]);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 17;
		gbc_btnNewButton.gridy = 0;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JTabbedPane mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_mainTabbedPane = new GridBagConstraints();
		gbc_mainTabbedPane.gridheight = 2;
		gbc_mainTabbedPane.gridwidth = 17;
		gbc_mainTabbedPane.insets = new Insets(0, 0, 0, 5);
		gbc_mainTabbedPane.fill = GridBagConstraints.BOTH;
		gbc_mainTabbedPane.gridx = 3;
		gbc_mainTabbedPane.gridy = 1;
		frame.getContentPane().add(mainTabbedPane, gbc_mainTabbedPane);
		
		JPanel orderTab = new JPanel();
		mainTabbedPane.addTab("Order", null, orderTab, null);
		GridBagLayout gbl_orderTab = new GridBagLayout();
		gbl_orderTab.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_orderTab.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_orderTab.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_orderTab.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		orderTab.setLayout(gbl_orderTab);
		
		JButton btnNewButton_1 = new JButton("Create Order");
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
		
		JLabel lblNewLabel = new JLabel("Order Status");
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
		
		
		
		final String[] columnNames = {"Item Number", "Item name", "Type", "Quantity", "Price", "Units"};
		
		//this one will access data from the the database but will cause the code not to work in design mode
		//use this one when testing
		final Object[][] data = connect.getInventory(supplier_id);
		
		//use this one when building
		//final Object [][] data = {{"1","papples", "fruits", "5000", "$2000"},{"2","apples", "fruits", "5000", "$2000"},{"3","grapes", "fruits", "5000", "$2000"},{"4","pears", "fruits", "5000", "$2000"} };
		
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		inventoryTab.add(scrollPane_1, gbc_scrollPane_1);
		
		table_2 = new JTable(data, columnNames);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mevt) {
				java.awt.Point point = mevt.getPoint();
				row =table_2.rowAtPoint(point);
				selectedRow=(String) table_2.getValueAt(row, 0);
				System.out.println(selectedRow);
				
				
			}
		});
		scrollPane_1.setViewportView(table_2);
		
		
		JButton btnNewButton_3 = new JButton("Add New Item");
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
					{connect.addItem(item, supplier_id);
					Object [] [] data2 = connect.getInventory(supplier_id);
					table_2 = new JTable(data2, columnNames);
					scrollPane_1.setViewportView(table_2);}
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 0;
		inventoryTab.add(btnNewButton_3, gbc_btnNewButton_3);
		if(!manager){
			btnNewButton_3.setVisible(false);
			}
		
		JButton btnSaveChanges = new JButton("Save Changes on Selected Row");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedRow!=null){
					System.out.println("About to save changes to this row " + selectedRow);
					System.out.println("Is this the anser?? " + table_2.getValueAt(row, 1));
					String [] item={(String) table_2.getValueAt(row, 1),(String) table_2.getValueAt(row, 2), (String) table_2.getValueAt(row, 3), (String) table_2.getValueAt(row, 4)};
	
					
					if(item!=null)	
						{connect.modifyItem(item, Integer.parseInt(selectedRow));
						Object [] [] data2 = connect.getInventory(supplier_id);
						table_2 = new JTable(data2, columnNames);
						scrollPane_1.setViewportView(table_2);}
					
					
					
				}
			}
		});
		GridBagConstraints gbc_btnSaveChanges = new GridBagConstraints();
		gbc_btnSaveChanges.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveChanges.gridx = 2;
		gbc_btnSaveChanges.gridy = 0;
		inventoryTab.add(btnSaveChanges, gbc_btnSaveChanges);
		 if(!manager){
			 btnSaveChanges.setVisible(false); 
		 }
		
		JPanel supermarketTab = new JPanel();
		mainTabbedPane.addTab("Supermarket", null, supermarketTab, null);
		
		JPanel accountTab = new JPanel();
		mainTabbedPane.addTab("Account", null, accountTab, null);
		
		JPanel jpInvoices = new JPanel(new BorderLayout());
		mainTabbedPane.addTab("Invoices",null, jpInvoices, null);
		
		
		/*Invoice tab*/
		
		
		final Object[][] order1 = new Object[][]{
				{data[0][0], data[0][1], 2, data[0][4], 2 * Double.parseDouble((String) data[0][4])},
				{data[1][0], data[1][1], 2, data[1][4], 2 * Double.parseDouble((String) data[1][4])}
		};
		
		
		String[] title = new String[] {"Item Number", "Item", "quantity", "Price($)", "Total($)"};
		final JTable jtInvoice = new JTable();
		final JComboBox<String> jcbSupermarkets = new JComboBox<>(new String[] {"supermarket1","supermarket2","supermarket3"});
		jcbSupermarkets.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = jcbSupermarkets.getSelectedIndex();
				if(index >= 0){

				}
			}
		});
		
		jtInvoice.setModel(new DefaultTableModel(order1,title) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton jbPrint = new JButton("Save and Open");
		jbPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("invoice_" + jcbSupermarkets.getSelectedItem()+ ".xls");
					TableModel model = jtInvoice.getModel();
					FileWriter out = new FileWriter(file);
					
					for(int i = 0; i < model.getColumnCount(); i++){
						out.write(model.getColumnName(i)+"\t");
					}
					out.write("\n");
					for(int i=0; i < model.getRowCount();i++){
						for(int j=0;j < model.getColumnCount();j++){
		
							out.write(model.getValueAt(i,j).toString() + "\t");
							
						}
							out.write("\n");
					}
					out.close();
					
					Runtime r = Runtime.getRuntime();
					r.exec("cmd.exe /c start " + file);
					
					
					//Print off table directly
					//jtInvoice.print(JTable.PrintMode.NORMAL);
					
				} catch (/*PrinterException |*/ IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JScrollPane jspInvoice = new JScrollPane(jtInvoice);
		jpInvoices.add(jcbSupermarkets, BorderLayout.NORTH);
		jpInvoices.add(jspInvoice, BorderLayout.CENTER);
		jpInvoices.add(jbPrint,BorderLayout.SOUTH);
	}


	


}
