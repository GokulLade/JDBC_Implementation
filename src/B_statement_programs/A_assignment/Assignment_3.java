package B_statement_programs.A_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Assignment_3 {

	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		try (s;){
			
			System.out.println("Enter Book Code Number..!");
			long bookCode=Long.parseLong(s.nextLine());
			
			System.out.println("Enter Book Name..!");
			String bookName =s.nextLine();
			bookName =s.nextLine();
			
			System.out.println("Enter Book Author name..!");
			String authorName=s.nextLine();
			
			System.out.println("Enter the book price..!!");
			float bookPrice=Float.parseFloat(s.nextLine());
			
			System.out.println("Enter the book Qty..!!");
			int bookQty=Integer.parseInt(s.nextLine());
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Gokul","9112");
			
			Statement stm=con.createStatement();
			
			int result=stm.executeUpdate("insert into Bookdetails68(bcode,bname,bauthor,bprice,bqty)values("+bookCode+",'"+bookName+"','"+authorName+"',"+bookPrice+","+bookQty+")");
			
			
			if(result>0)
			{
				System.out.println("Data Inserted Successfully");
			}
			else {
				System.out.println("Data not Inserted Try again..");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();		
		}

	}
}
