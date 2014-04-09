package FoodLink.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Insets;
import FoodLink.database;
import javax.swing.table.DefaultTableModel;

public class ViewInvoice {

	private JFrame frame;
	private JTable table;
	private database connect = new database();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//change invoice number and supplier id
					ViewInvoice window = new ViewInvoice(1, 1);
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
	public ViewInvoice(int invoiceNumber, int supplier_id) {
		initialize(invoiceNumber, supplier_id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int invoiceNumber, int suppplier_id) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		Object[][] orderList = connect.getOrderListSupplier(invoiceNumber, supplier_id);
		final String[] columnTitle = new String[] { "Invoice Number", "Supermarket", "Total Cost($)",
				"Date/Time Created", "Status" };

		DefaultTableModel orderModel = new DefaultTableModel(orderList, columnTitle){
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};;
		
		table = new JTable(orderModel);
		scrollPane.setColumnHeaderView(table);
		
		JButton btnPrintInvoice = new JButton("Print Invoice");
		GridBagConstraints gbc_btnPrintInvoice = new GridBagConstraints();
		gbc_btnPrintInvoice.gridx = 0;
		gbc_btnPrintInvoice.gridy = 1;
		frame.getContentPane().add(btnPrintInvoice, gbc_btnPrintInvoice);
		
		
	}

}
