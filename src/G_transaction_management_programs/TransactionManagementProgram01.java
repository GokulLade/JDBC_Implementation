package G_transaction_management_programs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class TransactionManagementProgram01 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc) {
			
			Connection con=DatabaseConnection.getConnection();
			
			System.out.println("Commit Status: "+con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("Commit Status: "+con.getAutoCommit());
			
			PreparedStatement ps1 = con.prepareStatement("select * from Bank68 where accno=?");
			PreparedStatement ps2 = con.prepareStatement("update Bank68 set balance=balance+? where accno=?");
			
			Savepoint sp=con.setSavepoint();
			
			System.out.println("Enter the Home Account Number");
			long hAccountNumber=sc.nextLong();
			
			ps1.setLong(1, hAccountNumber);
			ResultSet rs1=ps1.executeQuery();
			
			if(rs1.next())
			{
				float bal=rs1.getFloat(3);
				
				System.out.println("Enter the benifieciery Account number");
				long bAccountNumber=sc.nextLong();
				
				ps1.setLong(1, bAccountNumber);
				ResultSet rs2=ps1.executeQuery();
				
				if(rs2.next())
				{
					System.out.println("Enter the Amount to be transferred");
					float amt=sc.nextFloat();
					if(amt<=bal)
					{
						//Statement-1 : Subtract Amount from the accNo
						ps2.setFloat(1, -amt);
						ps2.setLong(2, hAccountNumber);
						int result1=ps2.executeUpdate();
						
						//Statement-2 : Add Amount to Account
						ps2.setFloat(1, +amt);
						ps2.setLong(2, bAccountNumber);
						int result2=ps2.executeUpdate();
						
						if(result1==1 && result2==1)
						{
							con.commit();
							System.out.println("Transaction Successfull...");
						}
						else
						{
							con.rollback(sp);
							System.out.println("Transaction failed..");
						}
					}
					else
					{
						System.out.println("InSufficient fund");
					}
				}
				else
				{
					System.out.println("Invalid Benifieciery Account number");
				}
			}
			else
			{
				System.out.println("Invalid Home  Account number");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
