import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author xfn
 *
 */
public class Receivable {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void readDataBase(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost/record?user=administrator&password=passw0rd");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from record.list");
			
			while(resultSet.next()){
				String id = resultSet.getString("id");
				String purchaser = resultSet.getString("purchaser");
				String consignee = resultSet.getString("consignee");
				String orderNO = resultSet.getString("orderNO");
				
				System.out.println(id+purchaser+consignee+orderNO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Receivable().readDataBase();
	}
}
