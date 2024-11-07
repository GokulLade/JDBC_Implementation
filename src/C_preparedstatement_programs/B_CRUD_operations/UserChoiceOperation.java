package C_preparedstatement_programs.B_CRUD_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UserChoiceOperation {
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		try(s;){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Gokul","9112");
		
		PreparedStatement ps1 = con.prepareStatement("insert into Product68 values(?,?,?,?)");
		
		PreparedStatement ps2 = con.prepareStatement("select * from Product68");//Compilation
		
		while(true) 
		{
			System.out.println("======Operations Choice======");
			System.out.println("\t1.AddProduct"
			+ "\n\t2.ViewAllProducts"
			+ "\n\t3.ViewProductByCode"
			+ "\n\t4.UpdateProductByCode(price-qty)"
			+ "\n\t5.DeleteProductByCode"
			+ "\n\t6.Exit");
			System.out.println("Enter your Choice:");
			int choice = Integer.parseInt(s.nextLine());
			
			switch(choice) 
			{
			
			case 1:
				System.out.println("-----read product details-----");
				
				System.out.println("Enter the Prod-Code:");
				String pC = s.nextLine();
				System.out.println("Enter the Prod-Name:");
				String pN = s.nextLine();
				System.out.println("Enter the Prod-Price:");
				float pP = Float.parseFloat(s.nextLine());
				System.out.println("Enter the Prod-Qty:");
				int pQ = Integer.parseInt(s.nextLine());
				
				//Loading data to PreparedStatement Object
				ps1.setString(1,pC);
				ps1.setString(2,pN);
				ps1.setFloat(3,pP);
				ps1.setInt(4,pQ);
				int k = ps1.executeUpdate();//Execution
				if(k>0) 
				{
					System.out.println("Product Added Successfully...");
				}
				break;
				
			case 2:
				ResultSet rs = ps2.executeQuery();//Execution
				System.out.println("-----ProductDetails------");
				while(rs.next()) 
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getInt(4));
				}//end of loop
				break;
				
			case 3:
				break;
			
			case 4:
				break;
				
			case 5:
				break;
				
			case 6:
				System.out.println("Operations Stopped....");
				System.exit(0);
				
			default:
				System.out.println("Invalid Choice...");
				
			}//end of switch
		}//end of loop
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
