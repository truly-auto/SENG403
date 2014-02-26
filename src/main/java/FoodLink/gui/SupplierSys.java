package FoodLink.gui;


import java.awt.EventQueue;
import java.lang.System.*;

import javax.crypto.spec.IvParameterSpec;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SupplierSys {

	private JFrame frame;
	private JTable table;
	private int supplier_id;
	private database connect = new database ();
	private String selectedRow= null;
	private int row;
	private String[] result;

	private JTable jtbInventory;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierSys window = new SupplierSys();
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
	public SupplierSys() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		supplier_id = 1;
		String [] supplier = connect.getSpecSupplier(supplier_id);
		
		frame = new JFrame();
		frame.setTitle("FoodLink");
		frame.setBounds(100, 100, 608, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//logout
			}
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
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		orderTab.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel inventoryTab = new JPanel();
		mainTabbedPane.addTab("Inventory", null, inventoryTab, null);
		
		inventoryTab.setLayout(new BorderLayout());
		
		final String[] columnNames = {"Item Number", "Item name", "Type", "Quantity", "Price"};
		
		//this one will access data from the the database but will cause the code not to work in design mode
		//use this one when testing
		final Object[][] data = connect.getInventory(supplier_id);
		
		//use this one when building
		//final Object [][] data = {{"1","papples", "fruits", "5000", "$2000"},{"2","apples", "fruits", "5000", "$2000"},{"3","grapes", "fruits", "5000", "$2000"},{"4","pears", "fruits", "5000", "$2000"} };
		
		
		final JScrollPane jspInvTable = new JScrollPane();
		
		inventoryTab.add(jspInvTable, BorderLayout.CENTER);
		
		jtbInventory = new JTable(data, columnNames);
		jtbInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mevt) {
				java.awt.Point point = mevt.getPoint();
				row =jtbInventory.rowAtPoint(point);
				selectedRow=(String) jtbInventory.getValueAt(row, 0);
				System.out.println(selectedRow);
				
			}
		});
		jspInvTable.setViewportView(jtbInventory);
		
		JPanel jpAddPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 4));
		JPanel jpSavePane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 4));
		JButton btnAddNewItem = new JButton("Add New Item");

		btnAddNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] item=null;
				try {
					AddItem window = new AddItem();
					window.setModalityType(ModalityType.APPLICATION_MODAL);
					window.setVisible(true);
					
					item =window.getResult();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Invalid inputs.");
					e1.printStackTrace();
				}		
				
				if(item!=null)	
					{connect.addItem(item, supplier_id);
					Object [] [] data2 = connect.getInventory(supplier_id);
					jtbInventory = new JTable(data2, columnNames);
					jspInvTable.setViewportView(jtbInventory);}
			}
		});
		
		
		JButton btnSaveChanges = new JButton("Save Changes on Selected Row");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedRow!=null){
					System.out.println("About to save changes to this row " + selectedRow);
					System.out.println("Is this the anser?? " + jtbInventory.getValueAt(row, 1));
					String [] item={(String) jtbInventory.getValueAt(row, 1),(String) jtbInventory.getValueAt(row, 2), (String) jtbInventory.getValueAt(row, 3), (String) jtbInventory.getValueAt(row, 4)};
	
					if(item!=null){
						connect.modifyItem(item, Integer.parseInt(selectedRow));
						Object [] [] data2 = connect.getInventory(supplier_id);
						jtbInventory = new JTable(data2, columnNames);
						jspInvTable.setViewportView(jtbInventory);
					}
				}
			}
		});
	
		jpAddPane.add(btnAddNewItem);
		jpSavePane.add(btnSaveChanges);
		
		JPanel jpBtnPane = new JPanel(new GridLayout(1,0,1,1));
		jpBtnPane.add(jpAddPane);
		jpBtnPane.add(jpSavePane);
		inventoryTab.add(jpBtnPane, BorderLayout.NORTH);
		
		JPanel supermarketTab = new JPanel();
		mainTabbedPane.addTab("Supermarket", null, supermarketTab, null);
		
		JPanel accountTab = new JPanel();
		mainTabbedPane.addTab("Account", null, accountTab, null);
	}

}
