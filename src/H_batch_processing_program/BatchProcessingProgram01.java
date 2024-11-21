package H_batch_processing_program;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class BatchProcessingProgram01 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc;)
		{
			Connection con=DatabaseConnection.getConnection();
			
			Statement stm=con.createStatement();
			
			System.out.println("Enter the Product Code to Delete Record");
			String pCode=sc.nextLine();
			
			//Deleting Record form Product68 query-1
			String query1="delete from Product68 where code='"+pCode+"'";
			stm.addBatch(query1);
			
			System.out.println("Enter the Account Number");
			long accNo=Long.parseLong(sc.nextLine());
			System.out.println("Enter the User Name");
			String name=sc.nextLine();
			System.out.println("Enter the balance");
			float bal=Float.parseFloat(sc.nextLine());
			System.out.println("Enter the Account Type");
			String accType=sc.nextLine();
			
			//Inserting Record in to Bank68 query-2
			String query2="Insert into Bank68 Values("+accNo+",'"+name+"',"+bal+",'"+accType+"')";
			stm.addBatch(query2);
			
			//Executing query-1 and query-2
			int results[]=stm.executeBatch();
			
			String status="No";
			for(int result: results)
			{
				if(result==1)status="Yes";
				System.out.println("Query Executed: "+status);
			}
			
			stm.clearBatch();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
