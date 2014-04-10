package FoodLink.gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingUtilities;

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

public class SupplierSys {

	public JFrame frame;
	private String[] supplier;
	private JTable table;
	private database connect = new database();
	private String selectedRow = null;
	private String selectedUser = null;
	private int row;
	private boolean manager = true;

	private JTable table2;
	private JTable table_1;
	private JTable inventoryTable;
	private JTable userTable;
	private final JScrollPane scrollPane_2 = new JScrollPane();
	private JTable table_3;
	private JTable orderStatusTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// hard code parameter to switch suppliers here (1-5)
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
		frame.setTitle("FoodLink");
		// frame.setBounds(100, 100, 608, 300);
		frame.setBounds(100, 100, 640, 420);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Color green = new Color(182, 215, 168);
		frame.getContentPane().setBackground(green);
		BufferedImage Logo = null;
		JPanel banner = new JPanel();
		try {
			Logo = ImageIO
					.read(new File("src/main/resources/images/Logo17.JPG")); // put
																				// icon
																				// image
																				// here
			JLabel LogoPanel = new JLabel(new ImageIcon(Logo));
			banner.add(LogoPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		banner.setBackground(green);
		frame.getContentPane().add(banner);
		frame.setBackground(green);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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

		JLabel lblNewLabel_1 = new JLabel(supplier[0]);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD
				| Font.ITALIC, 40));
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
		 * gbc_mainTabbedPane.gridheight = 2; gbc_mainTabbedPane.gridwidth = 17;
		 * gbc_mainTabbedPane.insets = new Insets(0, 0, 0, 5);
		 * gbc_mainTabbedPane.fill = GridBagConstraints.BOTH;
		 * gbc_mainTabbedPane.gridx = 3; gbc_mainTabbedPane.gridy = 1;
		 * frame.getContentPane().add(mainTabbedPane, gbc_mainTabbedPane);
		 */
		JPanel orderTab = new JPanel();
		mainTabbedPane.addTab("Order", null, orderTab, null);
		GridBagLayout gbl_orderTab = new GridBagLayout();
		gbl_orderTab.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_orderTab.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_orderTab.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_orderTab.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
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
		gbl_inventoryTab.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_inventoryTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_inventoryTab.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0 };
		gbl_inventoryTab.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_inventoryTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_inventoryTab.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_inventoryTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		inventoryTab.setLayout(gbl_inventoryTab);

		final String[] columnNames = { "Item Number", "Item name", "Type",
				"Quantity", "Unit Price", "Units" };

		// this one will access data from the the database but will cause the
		// code not to work in design mode
		// use this one when testing
		// final Object[][] data = connect.getInventory(supplier_id);

		// use this one when building
		final Object[][] data = {
				{ "1", "papples", "fruits", "5000", "2000", "100 lb" },
				{ "2", "apples", "fruits", "5000", "2000", "200 lb" },
				{ "3", "grapes", "fruits", "5000", "2000", "40 lb" },
				{ "4", "pears", "fruits", "5000", "2000", "lb" } };

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
				row = inventoryTable.rowAtPoint(point);
				selectedRow = (String) inventoryTable.getValueAt(row, 0);
				System.out.println(selectedRow);

			}
		});
		scrollPane_1.setViewportView(inventoryTable);

		JButton btnDeleteCurrentRow = new JButton("Delete Current Row");
		btnDeleteCurrentRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedRow != null) {
					System.out.println("About to delete this row "
							+ selectedRow);
					connect.manageItems(null, Integer.parseInt(selectedRow),
							false);
					// getting data from the database
					Object[][] data2 = connect.getInventory(supplier_id);
					// creating new table with the new data from the database
					inventoryTable = new JTable(data2, columnNames);
					// adding action listener on the new table
					inventoryTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent mevt) {
							java.awt.Point point = mevt.getPoint();
							row = inventoryTable.rowAtPoint(point);
							selectedRow = (String) inventoryTable.getValueAt(
									row, 0);
							System.out.println(selectedRow);

						}
					});
					scrollPane_1.setViewportView(inventoryTable);

				} else {
					System.out.println("selctedRow is null");
				}

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
				String[] item = null;
				try {
					AddItem window = new AddItem();
					window.setModalityType(ModalityType.APPLICATION_MODAL);
					window.setVisible(true);

					item = window.getResult();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (item != null) {
					connect.manageItems(item, supplier_id, true);
					Object[][] data2 = connect.getInventory(supplier_id);
					// creating a new table with new information
					inventoryTable = new JTable(data2, columnNames);
					// adding an action listener
					inventoryTable.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent mevt) {
							java.awt.Point point = mevt.getPoint();
							row = inventoryTable.rowAtPoint(point);
							selectedRow = (String) inventoryTable.getValueAt(
									row, 0);
							System.out.println(selectedRow);

						}
					});
					scrollPane_1.setViewportView(inventoryTable);
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 0;
		inventoryTab.add(btnNewButton_3, gbc_btnNewButton_3);

		GradientButton btnSaveChanges = new GradientButton(
				"Save Changes on Selected Row");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedRow != null) {
					System.out.println("About to save changes to this row "
							+ selectedRow);
					System.out.println("Is this the anser?? "
							+ inventoryTable.getValueAt(row, 1));
					String[] item = {
							(String) inventoryTable.getValueAt(row, 1),
							(String) inventoryTable.getValueAt(row, 2),
							(String) inventoryTable.getValueAt(row, 3),
							(String) inventoryTable.getValueAt(row, 4) };

					if (item != null) {
						connect.modifyItem(item, Integer.parseInt(selectedRow));
						// getting information from the database
						Object[][] data2 = connect.getInventory(supplier_id);
						// adding the information into a new table
						inventoryTable = new JTable(data2, columnNames);
						// adding an action listener to the table
						inventoryTable.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent mevt) {
								java.awt.Point point = mevt.getPoint();
								row = inventoryTable.rowAtPoint(point);
								selectedRow = (String) inventoryTable
										.getValueAt(row, 0);
								System.out.println(selectedRow);

							}
						});
						scrollPane_1.setViewportView(inventoryTable);
					}

				}
			}
		});
		GridBagConstraints gbc_btnSaveChanges = new GridBagConstraints();
		gbc_btnSaveChanges.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveChanges.gridx = 3;
		gbc_btnSaveChanges.gridy = 0;
		inventoryTab.add(btnSaveChanges, gbc_btnSaveChanges);

		// enforcing roles
		if (!manager) {
			// cant add items
			btnNewButton_3.setVisible(false);
			// cant edit items
			btnSaveChanges.setVisible(false);
			// cant delete stuff..
			btnDeleteCurrentRow.setVisible(false);
		}

		JPanel supermarketTab = new JPanel();
		mainTabbedPane.addTab("Supermarket", null, supermarketTab, null);

		JPanel accountTab = new JPanel();
		mainTabbedPane.addTab("Account", null, accountTab, null);

		/* Invoice tab */
		
		final Object[][] orderList = connect.getOrderListSupplier(supplier_id);
		

		JPanel jpInvoices = new JPanel();
		mainTabbedPane.addTab("Invoices", null, jpInvoices, null);
		GridBagLayout gbl_jpInvoices = new GridBagLayout();
		gbl_jpInvoices.columnWidths = new int[] { 0, 0 };
		gbl_jpInvoices.rowHeights = new int[] { 0, 0, 0 };
		gbl_jpInvoices.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jpInvoices.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		jpInvoices.setLayout(gbl_jpInvoices);

		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 1;
		jpInvoices.add(scrollPane_3, gbc_scrollPane_3);

		final GradientButton btnViewInvoice = new GradientButton("View Order");
		btnViewInvoice.setEnabled(false);
		btnViewInvoice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked the View Order Button");

				int row = orderStatusTable.getSelectedRow();

				int invoiceNum = Integer.parseInt((String) orderStatusTable
						.getValueAt(row, 0));
				System.out.println("The invoice number passed is: "
						+ invoiceNum);

				String supermarketName = (String) orderStatusTable.getValueAt(row, 1);
				String dateTime = (String) orderStatusTable.getValueAt(row, 3);
				String grandTotal1 = (String) orderStatusTable.getValueAt(row,
						2);
				String status = (String) orderStatusTable.getValueAt(row, 4);

				String store_id = (String)orderList[orderStatusTable.getSelectedRow()][5];
				
				ViewInvoice viewInvoice = new ViewInvoice(invoiceNum,
						supermarketName, store_id,supplier_id,dateTime, status, grandTotal1, row,
						orderStatusTable);
				viewInvoice.setVisible(true);
			}

		});

		JButton btnNewButton_4 = new JButton("View Invoice");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 0;
		jpInvoices.add(btnViewInvoice, gbc_btnNewButton_4);

		
		final String[] columnTitle = new String[] { "Invoice Number",
				"Supermarket", "Total Cost($)", "Date/Time Created", "Status"};

		DefaultTableModel orderModel = new DefaultTableModel(orderList,
				columnTitle) {
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
				System.out.println("Clicking Table");
				btnViewInvoice.setEnabled(true);
			}
		});

		scrollPane_3.setViewportView(orderStatusTable);

	}

	private static final class GradientButton extends JButton {
		private GradientButton() {
			this.setText("");
			setContentAreaFilled(false);
		}

		private GradientButton(String str) {
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
