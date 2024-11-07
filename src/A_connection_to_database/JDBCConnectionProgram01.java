package A_connection_to_database;
import java.sql.*;
public class JDBCConnectionProgram01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String userName="Gokul";
		String password="9112";
		
		// Step-1
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// Step-2
		Connection con= DriverManager.getConnection(url,userName,password);
		
		System.out.println((con!=null)?"Connected..!!":"Not Connected");

	}

}
