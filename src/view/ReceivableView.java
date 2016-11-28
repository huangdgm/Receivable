package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;

import model.ListTableModel;

/**
 * The FilmDatabaseView class includes all the JComponent that are required by
 * the film database search application. It defines the look and feel of all the
 * JComponent, from JMenuBar, JTable to JComboBox and JTextArea. Also, it design
 * two update() methods, where method overloading occurs. One update method is
 * used to refresh the view of the JTable, while the other is used to refresh
 * the view of the JTextArea. Apart from the update methods, all other
 * JComponent are accompanied with the getter and the setter methods.
 * 
 * @author Dong Huang 15920066
 */
public class ReceivableView extends JPanel {
	private static final long serialVersionUID = 1L;
	private ListTableModel listTableModel;
	private JTable listTable;
	private JScrollPane listTableScrollPane;
	private JButton buttonAddRecord;
	private JButton buttonUpdateDatabase;
	private JTextField textFieldPurchaser;
	private JTextField textFieldConsignee;
	private JTextField textFieldOrderNO;

	public ReceivableView() {
		setLayout(null);

		listTable = new JTable();
//		listTable.setFillsViewportHeight(true);
		listTableScrollPane = new JScrollPane(listTable);
		listTableScrollPane.setLocation(10, 120);
		listTableScrollPane.setSize(1020, 700);
//		listTable.setPreferredScrollableViewportSize(new Dimension(500, 80));
		// enable row sorting functionality
		listTable.setAutoCreateRowSorter(true);
		// Stops the JTable from recreating the TableColumnModel, which in turn
		// preserve the attributes of the TableColumn, such as the preferred
		// width of the columns.
//		listTable.setAutoCreateColumnsFromModel(false);
		add(listTableScrollPane);
		
		buttonAddRecord = new JButton("Add Record");
		buttonAddRecord.setLocation(910, 40);
		buttonAddRecord.setSize(80, 40);
		add(buttonAddRecord);
		
		buttonUpdateDatabase = new JButton("Update Database");
		buttonUpdateDatabase.setLocation(800, 40);
		buttonUpdateDatabase.setSize(80, 40);
		add(buttonUpdateDatabase);
		
		textFieldPurchaser = new JTextField("purchaser");
		textFieldPurchaser.setLocation(100,40);
		textFieldPurchaser.setSize(80,40);
		add(textFieldPurchaser);
		
		textFieldConsignee = new JTextField("consignee");
		textFieldConsignee.setLocation(200,40);
		textFieldConsignee.setSize(80,40);
		add(textFieldConsignee);
		
		textFieldOrderNO = new JTextField("order NO");
		textFieldOrderNO.setLocation(300,40);
		textFieldOrderNO.setSize(80,40);
		add(textFieldOrderNO);
		
		setSize(1100, 680);
	}
	public JTextField getTextFieldPurchaser() {
		return textFieldPurchaser;
	}

	public void setTextFieldPurchaser(JTextField textFieldPurchaser) {
		this.textFieldPurchaser = textFieldPurchaser;
	}

	public JTextField getTextFieldConsignee() {
		return textFieldConsignee;
	}

	public void setTextFieldConsignee(JTextField textFieldConsignee) {
		this.textFieldConsignee = textFieldConsignee;
	}

	public JTextField getTextFieldOrderNO() {
		return textFieldOrderNO;
	}

	public void setTextFieldOrderNO(JTextField textFieldOrderNO) {
		this.textFieldOrderNO = textFieldOrderNO;
	}
	
	public JScrollPane getListTableScrollPane() {
		return listTableScrollPane;
	}

	public void setListTableScrollPane(JScrollPane listTableScrollPane) {
		this.listTableScrollPane = listTableScrollPane;
	}

	public JButton getButtonAddRecord() {
		return buttonAddRecord;
	}

	public void setButtonAddRecord(JButton buttonAddRecord) {
		this.buttonAddRecord = buttonAddRecord;
	}

	public JButton getButtonUpdateDatabase() {
		return buttonUpdateDatabase;
	}

	public void setButtonUpdateDatabase(JButton buttonUpdateDatabase) {
		this.buttonUpdateDatabase = buttonUpdateDatabase;
	}

	public JTable getListTable() {
		return listTable;
	}

	public void setListTable(JTable listTable) {
		this.listTable = listTable;
	}

	public ListTableModel getListTableModel() {
		return listTableModel;
	}

	public void setListTableModel(ListTableModel listTableModel) {
		this.listTableModel = listTableModel;
	}

	public void update() {
		listTable.setModel(listTableModel);
	}
}
