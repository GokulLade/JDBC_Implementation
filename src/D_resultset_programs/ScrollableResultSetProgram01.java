package D_resultset_programs;

import java.sql.*;
import java.util.*;

import A_connection_to_database.DatabaseConnection;
public class ScrollableResultSetProgram01 {

	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);

		try(s;) 
		{
			Connection con = DatabaseConnection.getConnection();
			
			xyz :while(true) 
				{
					System.out.println("--------choice to create Scrollable ResultSet Object-----");
					System.out.println("\t1.Using Statement"+ "\n\t2.Using PreparedStatement" + "\n\t3.Exit");
					System.out.println("Enter your Choice:");
			
					switch(s.nextInt()) 
					{
						case 1:
							Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							ResultSet rs1 = stm.executeQuery("select * from Product68");
							
							System.out.println("-------4th row-----");
							rs1.absolute(4);
							System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
							
							System.out.println("------relative(-2)-----");
							rs1.relative(-2);
							System.out.println(rs1.getString(1)+"\t" +rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
							
							System.out.println("-------last-row-------");
							rs1.last();
							System.out.println(rs1.getString(1)+"\t" +rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
					
							System.out.println("-------fisrt-row------");
							rs1.first();
							System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
							break;
						case 2:
							PreparedStatement ps = con.prepareStatement("select * from Product68",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							ResultSet rs2 = ps.executeQuery();
							
							System.out.println("-----Display records in reverse----");
							rs2.afterLast();
							while(rs2.previous()) 
							{
								System.out.println(rs2.getString(1)+"\t" +rs2.getString(2)+"\t"+rs2.getFloat(3)+ "\t"+rs2.getInt(4));
							}
							break;
						case 3:
							System.out.println("Program Stopped...");
							break xyz;//stop the loop
						default:
							System.out.println("Invalid Choice....");
					}
		
				}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
		
}





