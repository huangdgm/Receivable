import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
	private CachedRowSet recordRowSet;
	private ResultSetMetaData metadata;
	private int numOfCols;
	private int numOfRows;

	public ListTableModel(CachedRowSet recordRowSet) throws SQLException {
		this.recordRowSet = recordRowSet;
		this.metadata = this.recordRowSet.getMetaData();
		this.numOfCols = this.metadata.getColumnCount();

		this.recordRowSet.beforeFirst();

		this.numOfRows = 0;
		while (this.recordRowSet.next()) {
			this.numOfRows++;
		}

		this.recordRowSet.beforeFirst();
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
			e.printStackTrace();
			return e.toString();
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			this.recordRowSet.absolute(rowIndex + 1);
			Object o = this.recordRowSet.getObject(columnIndex + 1);

			if (o == null) {
				return null;
			} else {
				return o.toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}