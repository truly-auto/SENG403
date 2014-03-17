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

public class SupermarketSys {

	public JFrame frame;

	private JTable table_2;
	private JTable table_1;
	private int store_id;
	private JTable table;
	private JComboBox comboBox;
	private database connect = new database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// hard code parameter to swicth suppliers here (1-5)
					SupermarketSys window = new SupermarketSys(2);
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
	public SupermarketSys(int supermarket_id) {
		initialize(supermarket_id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int supermarket_id) {
		frame = new JFrame();
		frame.setTitle("FoodLink");
		frame.setBounds(100, 100, 450, 300);
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

		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 0;
		frame.getContentPane().add(label, gbc_label);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 16;
		gbc_btnNewButton.gridy = 0;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);

		final JTabbedPane mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_mainTabbedPane = new GridBagConstraints();
		gbc_mainTabbedPane.gridheight = 2;
		gbc_mainTabbedPane.gridwidth = 14;
		gbc_mainTabbedPane.insets = new Insets(0, 0, 0, 5);
		gbc_mainTabbedPane.fill = GridBagConstraints.BOTH;
		gbc_mainTabbedPane.gridx = 3;
		gbc_mainTabbedPane.gridy = 1;
		frame.getContentPane().add(mainTabbedPane, gbc_mainTabbedPane);

		JPanel orderTab = new JPanel();
		mainTabbedPane.addTab("Order", null, orderTab, null);
		GridBagLayout gbl_orderTab = new GridBagLayout();
		gbl_orderTab.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_orderTab.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_orderTab.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_orderTab.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 1.0, 1.0,
				0.0, 0.0, 1.0, Double.MIN_VALUE };
		orderTab.setLayout(gbl_orderTab);

		JButton btnNewButton_1 = new JButton("Create Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.gridx = 0;
		gbc_list.gridy = 4;
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

	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}
