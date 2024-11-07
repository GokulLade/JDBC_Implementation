package B_statement_programs.A_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Assignment_2 {
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		
		try (s;){
			
			System.out.println("Enter the Book code to show Book details..!");
			int bookCode=s.nextInt();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Gokul","9112");
			
			Statement stm=con.createStatement();
			
			ResultSet rs=stm.executeQuery("select * from Bookdetails68 where bcode="+bookCode+"");
			
			
			if(rs.next())
			{
				System.out.println("Book Code : "+rs.getInt(1));
				System.out.println("Book Name : "+rs.getString(2));
				System.out.println("Book Author : "+rs.getString(3));
				System.out.println("Book Price : "+rs.getFloat(4));
				System.out.println("Book Quentity : "+rs.getInt(5));
			}
			else {
				System.out.println("Invalid Account Number..!");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();		
		}

	}

}
