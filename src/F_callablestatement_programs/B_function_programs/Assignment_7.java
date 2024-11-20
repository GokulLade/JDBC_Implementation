package F_callablestatement_programs.B_function_programs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class Assignment_7 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc)
		{
			Connection con=DatabaseConnection.getConnection();
			
			CallableStatement cs=con.prepareCall("{call ?:=getTotalSalary(?)}");
			
			System.out.println("Enter the Employee Id Number to Show the total salary");
			String id=sc.nextLine();
			
			cs.setString(2, id);
			cs.registerOutParameter(1, Types.FLOAT);
			cs.execute();
			
			System.out.println("Employee Id is: "+id);
			System.out.println("Employee Total salary is: "+cs.getFloat(1));
			
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
