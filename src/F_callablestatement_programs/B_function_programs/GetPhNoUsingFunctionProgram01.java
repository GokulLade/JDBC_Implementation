package F_callablestatement_programs.B_function_programs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class GetPhNoUsingFunctionProgram01 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc)
		{
			Connection con=DatabaseConnection.getConnection();
			
			CallableStatement cs=con.prepareCall("{call ?:=getPhoneNumber(?)}");
			
			System.out.println("Enter the Customer Id To get Phone number");
			String id=sc.nextLine();
			
			cs.setString(2, id);
			cs.registerOutParameter(1, Types.BIGINT);
			cs.execute();
			
			System.out.println("Customer Id is "+id);
			System.out.println("Customer Mobile Number is "+cs.getLong(1));
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
