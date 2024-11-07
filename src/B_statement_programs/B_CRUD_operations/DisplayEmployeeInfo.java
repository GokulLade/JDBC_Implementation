package B_statement_programs.B_CRUD_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DisplayEmployeeInfo {

	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		try (s;){
			
			System.out.println("Enter the Account Number to show Details..!!");
			long aNumber=s.nextLong();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Gokul","9112");
			
			Statement stm=con.createStatement();
			
			ResultSet rs=stm.executeQuery("select * from Bankcustomer where accno="+aNumber+"");
			
			
			if(rs.next())
			{
				System.out.println("Customer-AccNo: "+rs.getInt(1));
				System.out.println("Customer-Name: "+rs.getString(2));
				System.out.println("Customer-Balace: "+rs.getFloat(3));
				System.out.println("Customer-AccType: "+rs.getString(4));
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
