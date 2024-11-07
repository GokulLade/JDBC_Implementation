package B_statement_programs.A_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Assignment_1 {
	public static void main(String[] args) 
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Gokul","9112");
			
			Statement stm=con.createStatement();
			
			ResultSet rs=stm.executeQuery("select * from BookDetails68");
			
			System.out.println("BookCode BookName BookAuthor BookPrice Bookqty");
			
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getFloat(4)+"\t"+rs.getInt(5));
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
