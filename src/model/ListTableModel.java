package model;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * 
 */

/**
 * @author xfn
 *
 */
public class ListTableModel implements TableModel {
	private CachedRowSet cachedRowSet;
	private ResultSetMetaData metadata;
	private int numOfCols = 0;
	private int numOfRows = 0;

	public ListTableModel(CachedRowSet cachedRowSet) throws SQLException {
		this.cachedRowSet = cachedRowSet;
		this.metadata = this.cachedRowSet.getMetaData();
		this.numOfCols = this.metadata.getColumnCount();

		this.cachedRowSet.beforeFirst();

		while (this.cachedRowSet.next()) {
			this.numOfRows++;
		}

		this.cachedRowSet.beforeFirst();
	}

	public void addEventHandlersToRowSet(RowSetListener listener) {
		this.cachedRowSet.addRowSetListener(listener);
	}

	public void insertRow(String purchaser, String consignee, String orderNO) {
		try {
			this.cachedRowSet.moveToInsertRow();

			this.cachedRowSet.updateString("purchaser", purchaser);
			this.cachedRowSet.updateString("consignee", consignee);
			this.cachedRowSet.updateString("orderNO", orderNO);

			this.cachedRowSet.insertRow();

			this.cachedRowSet.moveToCurrentRow();
			// We leave the acceptChanges() in the controller.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.cachedRowSet.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void finalize() {
		close();
	} 

	@Override
	public int getRowCount() {
		return numOfRows;
	}

	@Override
	public int getColumnCount() {
		return numOfCols;
	}

	@Override
	public String getColumnName(int columnIndex) {
		try {
			return this.metadata.getColumnLabel(columnIndex + 1);
		} catch (SQLException e) {
			return e.toString();
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0) {
			return Integer.class;
		} else {
			return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			this.cachedRowSet.absolute(rowIndex + 1);
			Object o = this.cachedRowSet.getObject(columnIndex + 1);

			if (o == null) {
				return null;
			} else {
				return o.toString();
			}
		} catch (SQLException e) {
			return e.toString();
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	public CachedRowSet getCachedRowSet() {
		return cachedRowSet;
	}

	public void setCachedRowSet(CachedRowSet cachedRowSet) {
		this.cachedRowSet = cachedRowSet;
	}
}