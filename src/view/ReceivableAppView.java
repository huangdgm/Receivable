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
	private JButton addRecordButton;
	private JButton updateDBButton;
	private JTextField purchaserTextField;
	private JTextField consigneeTextField;
	private JTextField orderNOTextField;

	public ReceivableAppView(ListTableModel listTableModel) {
		setLayout(null);
		
		this.listTableModel = listTableModel;

		listTable = new JTable();
		listTableScrollPane = new JScrollPane(listTable);
		listTableScrollPane.setLocation(10, 120);
		listTableScrollPane.setSize(1020, 700);
		listTable.setAutoCreateRowSorter(true);
		add(listTableScrollPane);
		
		addRecordButton = new JButton("ADD RECORD");
		addRecordButton.setLocation(910, 40);
		addRecordButton.setSize(120, 40);
		add(addRecordButton);
		
		updateDBButton = new JButton("UPDATE DB");
		updateDBButton.setLocation(600, 40);
		updateDBButton.setSize(120, 40);
		add(updateDBButton);
		
		purchaserTextField = new JTextField("PURCHASER");
		purchaserTextField.setLocation(100,40);
		purchaserTextField.setSize(80,40);
		add(purchaserTextField);
		
		consigneeTextField = new JTextField("CONSIGNEE");
		consigneeTextField.setLocation(200,40);
		consigneeTextField.setSize(80,40);
		add(consigneeTextField);
		
		orderNOTextField = new JTextField("ORDER NO.");
		orderNOTextField.setLocation(300,40);
		orderNOTextField.setSize(80,40);
		add(orderNOTextField);
		
		setSize(1100, 680);
	}

	public void update() {
		listTable.setModel(listTableModel);
	}


	public ListTableModel getListTableModel() {
		return listTableModel;
	}


	public JTable getListTable() {
		return listTable;
	}


	public JScrollPane getListTableScrollPane() {
		return listTableScrollPane;
	}


	public JButton getAddRecordButton() {
		return addRecordButton;
	}


	public JButton getUpdateDBButton() {
		return updateDBButton;
	}


	public JTextField getPurchaserTextField() {
		return purchaserTextField;
	}


	public JTextField getConsigneeTextField() {
		return consigneeTextField;
	}


	public JTextField getOrderNOTextField() {
		return orderNOTextField;
	}


	public void setListTableModel(ListTableModel listTableModel) {
		this.listTableModel = listTableModel;
	}


	public void setListTable(JTable listTable) {
		this.listTable = listTable;
	}


	public void setListTableScrollPane(JScrollPane listTableScrollPane) {
		this.listTableScrollPane = listTableScrollPane;
	}


	public void setAddRecordButton(JButton addRecordButton) {
		this.addRecordButton = addRecordButton;
	}


	public void setUpdateDBButton(JButton updateDBButton) {
		this.updateDBButton = updateDBButton;
	}


	public void setPurchaserTextField(JTextField purchaserTextField) {
		this.purchaserTextField = purchaserTextField;
	}


	public void setConsigneeTextField(JTextField consigneeTextField) {
		this.consigneeTextField = consigneeTextField;
	}


	public void setOrderNOTextField(JTextField orderNOTextField) {
		this.orderNOTextField = orderNOTextField;
	}
}
