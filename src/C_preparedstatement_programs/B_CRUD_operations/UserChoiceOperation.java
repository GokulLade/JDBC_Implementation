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
		
		PreparedStatement ps2 = con.prepareStatement("select * from Product68");
		
		PreparedStatement ps3 = con.prepareStatement("select * from Product68 where code=?");
				
		PreparedStatement ps4 = con.prepareStatement("update Product68 set price=?,qty=qty+? where code=?");
				
		PreparedStatement ps5 = con.prepareStatement("delete from Product68 where code=?");
		
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
				
				
				ps1.setString(1,pC);
				ps1.setString(2,pN);
				ps1.setFloat(3,pP);
				ps1.setInt(4,pQ);
				int k = ps1.executeUpdate();
				if(k>0) 
				{
					System.out.println("Product Added Successfully...");
				}
				break;
				
			case 2:
				ResultSet rs = ps2.executeQuery();
				System.out.println("-----ProductDetails------");
				while(rs.next()) 
				{
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getInt(4));
				}
				break;
				
			case 3:
				System.out.println("Enter the Produc-Code to display details:");
				String pC2 = s.nextLine();
				ps3.setString(1, pC2);
				ResultSet rs2 = ps3.executeQuery();
				if(rs2.next())
				{
					System.out.println(rs2.getString(1)+"\t"+rs2.getString(2)+"\t"+rs2.getFloat(3)+"\t"+rs2.getInt(4));
				}
				else
				{
					System.out.println("Invalid Product-Code....");
				}
				break;
			
			case 4:
				System.out.println("Enter the Product-Code to update Price and qty:");
				String pC3 = s.nextLine();
				ps3.setString(1, pC3);
				ResultSet rs3 = ps3.executeQuery();
				if(rs3.next()) 
				{
					System.out.println("Old Price :"+rs3.getFloat(3));
					System.out.println("Enter the New Price:");
					float nPrice = Float.parseFloat(s.nextLine());
					System.out.println("Existing qty :"+rs3.getInt(4));
	
					System.out.println("Enter the new qty(new stock):");
					int nQty = Integer.parseInt(s.nextLine());
	
					ps4.setFloat(1, nPrice);
					ps4.setInt(2, nQty);
					ps4.setString(3, pC3);
					int k2 = ps4.executeUpdate();
					
					if(k2>0) 
					{
						System.out.println("Product Updated Successfully...");
					}
				}
				else 
				{
					System.out.println("Invalid Product-Code....");
				}
				break;
				
			case 5:
				System.out.println("Enter the Product-Code to delete details:");
				String pC4 = s.nextLine();
				ps3.setString(1, pC4);
				ResultSet rs4 =ps3.executeQuery();
				if(rs4.next()) 
				{
					ps5.setString(1, pC4);
					int k3 = ps5.executeUpdate();
					if(k3>0) 
					{
						System.out.println("Product deleted Successfully...");
					}

				}
				else 
				{
					System.out.println("Invalid Product Code....");
				}
				break;
				
			case 6:
				System.out.println("Operations Stopped....");
				System.exit(0);
				
			default:
				System.out.println("Invalid Choice...");
				
			}
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
