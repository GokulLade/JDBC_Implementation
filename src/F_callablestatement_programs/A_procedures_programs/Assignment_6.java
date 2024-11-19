package F_callablestatement_programs.A_procedures_programs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class Assignment_6 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc;)
		{
			Connection con=DatabaseConnection.getConnection();
			
			CallableStatement cs=con.prepareCall("{call RetrieveEmpDetails68(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter the Employee Id: ");
			int id=sc.nextInt();
			cs.setInt(1, id);
			
			cs.registerOutParameter(2,Types.VARCHAR);
			cs.registerOutParameter(3,Types.VARCHAR);
			cs.registerOutParameter(4,Types.VARCHAR);
			cs.registerOutParameter(5,Types.VARCHAR);
			cs.registerOutParameter(6,Types.VARCHAR);
			cs.registerOutParameter(7,Types.VARCHAR);
			cs.registerOutParameter(8,Types.INTEGER);
			cs.registerOutParameter(9,Types.VARCHAR);
			cs.registerOutParameter(10,Types.INTEGER);
			cs.registerOutParameter(11,Types.FLOAT);
			cs.registerOutParameter(12,Types.FLOAT);
			cs.registerOutParameter(13,Types.FLOAT);
			cs.registerOutParameter(14,Types.FLOAT);
			
			cs.execute();
			
			System.out.println("----------- Employee Details --------------------");
			System.out.println("Employee ID: "+id);
			System.out.println("Employee Name: "+cs.getString(2));
			System.out.println("Employee Degination: "+cs.getString(3));
			System.out.println("Employee House Number: "+cs.getString(4));
			System.out.println("Employee SName: "+cs.getString(5));
			System.out.println("Employee City Name: "+cs.getString(6));
			System.out.println("Employee State Name: "+cs.getString(7));
			System.out.println("Employee Pincode: "+cs.getInt(8));
			System.out.println("Employee Mail Id: "+cs.getString(9));
			System.out.println("Employee Phone Number: "+cs.getLong(10));
			System.out.println("Employee Base Salary: "+cs.getFloat(11));
			System.out.println("Employee HRA Salary: "+cs.getFloat(12));
			System.out.println("Employee DA Salary: "+cs.getFloat(13));
			System.out.println("Employee Total Salary: "+cs.getFloat(14));
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
