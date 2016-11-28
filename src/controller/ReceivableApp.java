package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.rowset.CachedRowSetImpl;

import model.ListTableModel;
import view.ReceivableAppView;

public class ReceivableApp extends JFrame implements RowSetListener {
	private static final long serialVersionUID = 1L;
	
	private ListTableModel listTableModel;
	private ReceivableAppView receivableAppView;
	private Connection connection;
	// private Connection connection = null;
	// private Statement statement = null;
	// private PreparedStatement preparedStatement = null;
	// private ResultSet resultSet = null;
	//
	// public void readDataBase(){
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	//
	// connection =
	// DriverManager.getConnection("jdbc:mysql://localhost/record?user=administrator&password=passw0rd");
	// statement = connection.createStatement();
	// resultSet = statement.executeQuery("select * from record.list");
	//
	// while(resultSet.next()){
	// String id = resultSet.getString("id");
	// String purchaser = resultSet.getString("purchaser");
	// String consignee = resultSet.getString("consignee");
	// String orderNO = resultSet.getString("orderNO");
	//
	// System.out.println(id+purchaser+consignee+orderNO);
	// }
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	//
	// }
	//
	// /**
	// * @param args
	// */
	// public static void main(String[] args) {
	// new Receivable().readDataBase();
	// }

	public ReceivableApp(String title) throws SQLException {
		super(title);
		
		CachedRowSet cachedRowSet = getContentsOfListTable();
		listTableModel = new ListTableModel(cachedRowSet);
		listTableModel.addEventHandlersToRowSet(this);

		receivableAppView = new ReceivableAppView(listTableModel);

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/record?user=administrator&password=passw0rd");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					System.exit(0);
				}
			}
		});

		receivableAppView.getListTable().setModel(listTableModel);

		receivableAppView.getButtonAddRecord().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(ReceivableApp.this, new String[] { "Adding the following row:", "purchaser name: [" + receivableAppView.getTextFieldPurchaser().getText() + "]",
						"consignee name: [" + receivableAppView.getTextFieldConsignee().getText() + "]", "order NO: [" + receivableAppView.getTextFieldOrderNO().getText() + "]", });

				String purchaser;
				String consignee;
				String orderNO;

				purchaser = receivableAppView.getTextFieldPurchaser().getText().trim();
				consignee = receivableAppView.getTextFieldConsignee().getText().trim();
				orderNO = receivableAppView.getTextFieldOrderNO().getText().trim();

				listTableModel.insertRow(purchaser, consignee, orderNO);
			}
		});

		receivableAppView.getButtonUpdateDatabase().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listTableModel.getCachedRowSet().acceptChanges();
				} catch (SyncProviderException e1) {
					e1.printStackTrace();

					try {
						createNewTableModel();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		
		setSize(1460, 860);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		// set the initial location on the screen in the middle horizontally and
		// vertically
		this.setLocation(dimension.width / 2 - this.getSize().width / 2, dimension.height / 2 - this.getSize().height / 2);

		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		add(receivableAppView);
	}

	private void createNewTableModel() throws SQLException {
		listTableModel = new ListTableModel(getContentsOfListTable());
		listTableModel.addEventHandlersToRowSet(this);

		receivableAppView.getListTable().setModel(listTableModel);
	}

	private CachedRowSet getContentsOfListTable() {
		CachedRowSet cachedRowSet = null;

		try {
			// The CachedRowSetImpl class may not be found. Try remove jt.jar
			// and add it back.
			cachedRowSet = new CachedRowSetImpl();
			cachedRowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
			cachedRowSet.setConcurrency(ResultSet.CONCUR_UPDATABLE);
			cachedRowSet.setUsername("administrator");
			cachedRowSet.setPassword("passw0rd");
			cachedRowSet.setUrl("jdbc:mysql://localhost/record?relaxAutoCommit=true");
			cachedRowSet.setCommand("select * from list");
			
			cachedRowSet.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cachedRowSet;
	}

	@Override
	public void rowSetChanged(RowSetEvent event) {
	}

	@Override
	public void rowChanged(RowSetEvent event) {
		CachedRowSet cachedRowSet = this.listTableModel.getCachedRowSet();

		try {
			cachedRowSet.moveToCurrentRow();
			listTableModel = new ListTableModel(cachedRowSet);
			receivableAppView.getListTable().setModel(listTableModel);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ReceivableApp.this, new String[] { e.getClass().getName() + ": ", e.getMessage() });
		}
	}

	@Override
	public void cursorMoved(RowSetEvent event) {
	}

	public static void main(String[] args) {

		try {
			ReceivableApp receivableApp = new ReceivableApp("Receivable App");
			receivableApp.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
