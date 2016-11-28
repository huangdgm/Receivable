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

public class ReceivableFrame extends JFrame implements RowSetListener {
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

	public ReceivableFrame(String title) throws SQLException {
		super(title);
		
		listTableModel = new ListTableModel(getContentsOfListTable());
		listTableModel.addEventHandlersToRowSet(this);

		receivableAppView = new ReceivableAppView(listTableModel);
		receivableAppView.update();
		
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

		receivableAppView.getAddRecordButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(ReceivableFrame.this, new String[] { "\n","Adding the following row:", "purchaser name: [" + receivableAppView.getPurchaserTextField().getText() + "]",
						"consignee name: [" + receivableAppView.getConsigneeTextField().getText() + "]", "order NO: [" + receivableAppView.getOrderNOTextField().getText() + "]", });

				String purchaser;
				String consignee;
				String orderNO;

				purchaser = receivableAppView.getPurchaserTextField().getText().trim();
				consignee = receivableAppView.getConsigneeTextField().getText().trim();
				orderNO = receivableAppView.getOrderNOTextField().getText().trim();

				listTableModel.insertRow(purchaser, consignee, orderNO);
			}
		});

		receivableAppView.getUpdateDBButton().addActionListener(new ActionListener() {

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
		this.listTableModel = new ListTableModel(getContentsOfListTable());
//		this.listTableModel.addEventHandlersToRowSet(this);

		this.receivableAppView.setListTableModel(this.listTableModel);
		this.receivableAppView.update();
	}

	private CachedRowSet getContentsOfListTable() {
		CachedRowSet cachedRowSet = null;

		try {
			// The CachedRowSetImpl class may not be found. Try remove jt.jar
			// and add it back.
			cachedRowSet = new CachedRowSetImpl();
			cachedRowSet.setUsername("administrator");
			cachedRowSet.setPassword("passw0rd");
			cachedRowSet.setUrl("jdbc:mysql://localhost/record?relaxAutoCommit=true");	// Disable auto-commit.
			cachedRowSet.setCommand("select * from list");
			cachedRowSet.execute();
		} catch (SQLException e) {
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
			
			this.listTableModel = new ListTableModel(cachedRowSet);
			
			this.receivableAppView.setListTableModel(this.listTableModel);
			this.receivableAppView.update();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ReceivableFrame.this, new String[] { e.getClass().getName() + ": ", e.getMessage() });
		}
	}

	@Override
	public void cursorMoved(RowSetEvent event) {
	}

	public static void main(String[] args) {

		try {
			ReceivableFrame receivableApp = new ReceivableFrame("Receivable App");
			receivableApp.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
