package J_lab_programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program03 {

	public static void main(String[] args) 
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Gokul","9112");
			
			Statement stm=con.createStatement();
			
			stm.executeUpdate("INSERT INTO Demo01 values(4,'Shyam')");
			stm.executeUpdate("INSERT INTO Demo01 values(2,'Hari')");
			stm.executeUpdate("INSERT INTO Demo01 values(3,'Akash')");
			
			ResultSet rs=stm.executeQuery("Select * from Demo01");
			
			rs.next();
			
			System.out.println(rs.getInt(1));
		}
		catch(SQLException se)
		{
			System.out.println("Error : "+se.getMessage());
			se.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}

	}

}
