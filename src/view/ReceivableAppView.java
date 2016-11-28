package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.ListTableModel;

public class ReceivableAppView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ListTableModel listTableModel;
	
	private JTable listTable;
	private JScrollPane listTableScrollPane;
	private JButton button_ADD_RECORD;
	private JButton button_UPDATE_DB;
	private JTextField textField_PURCHASER;
	private JTextField textField_CONSIGNEE;
	private JTextField textField_ORDER_NO;

	public ReceivableAppView(ListTableModel listTableModel) {
		setLayout(null);
		
		this.listTableModel = listTableModel;

		listTable = new JTable();
		listTableScrollPane = new JScrollPane(listTable);
		listTableScrollPane.setLocation(10, 120);
		listTableScrollPane.setSize(1020, 700);
		listTable.setAutoCreateRowSorter(true);
		add(listTableScrollPane);
		
		button_ADD_RECORD = new JButton("ADD RECORD");
		button_ADD_RECORD.setLocation(910, 40);
		button_ADD_RECORD.setSize(120, 40);
		add(button_ADD_RECORD);
		
		button_UPDATE_DB = new JButton("UPDATE DB");
		button_UPDATE_DB.setLocation(600, 40);
		button_UPDATE_DB.setSize(120, 40);
		add(button_UPDATE_DB);
		
		textField_PURCHASER = new JTextField("PURCHASER");
		textField_PURCHASER.setLocation(100,40);
		textField_PURCHASER.setSize(80,40);
		add(textField_PURCHASER);
		
		textField_CONSIGNEE = new JTextField("CONSIGNEE");
		textField_CONSIGNEE.setLocation(200,40);
		textField_CONSIGNEE.setSize(80,40);
		add(textField_CONSIGNEE);
		
		textField_ORDER_NO = new JTextField("ORDER NO.");
		textField_ORDER_NO.setLocation(300,40);
		textField_ORDER_NO.setSize(80,40);
		add(textField_ORDER_NO);
		
		setSize(1100, 680);
	}
	public JTextField getTextFieldPurchaser() {
		return textField_PURCHASER;
	}

	public void setTextFieldPurchaser(JTextField textFieldPurchaser) {
		this.textField_PURCHASER = textFieldPurchaser;
	}

	public JTextField getTextFieldConsignee() {
		return textField_CONSIGNEE;
	}

	public void setTextFieldConsignee(JTextField textFieldConsignee) {
		this.textField_CONSIGNEE = textFieldConsignee;
	}

	public JTextField getTextFieldOrderNO() {
		return textField_ORDER_NO;
	}

	public void setTextFieldOrderNO(JTextField textFieldOrderNO) {
		this.textField_ORDER_NO = textFieldOrderNO;
	}
	
	public JButton getButtonAddRecord() {
		return button_ADD_RECORD;
	}

	public void setButtonAddRecord(JButton buttonAddRecord) {
		this.button_ADD_RECORD = buttonAddRecord;
	}

	public JButton getButtonUpdateDatabase() {
		return button_UPDATE_DB;
	}

	public void setButtonUpdateDatabase(JButton buttonUpdateDatabase) {
		this.button_UPDATE_DB = buttonUpdateDatabase;
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
