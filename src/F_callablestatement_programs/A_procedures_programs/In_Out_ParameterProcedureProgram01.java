package F_callablestatement_programs.A_procedures_programs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class In_Out_ParameterProcedureProgram01 {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc;)
		{
			Connection con=DatabaseConnection.getConnection();
			
			CallableStatement cs=con.prepareCall("{call RetrieveCustDetails68(?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter the Customer ID");
			int cid=sc.nextInt();
			cs.setInt(1, cid);
			
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.BIGINT);
			
			cs.execute();
			
			System.out.println("--------------Customer Details--------------");
			
			System.out.println("Customer Id : "+cid);
			System.out.println("Customer Name : "+cs.getString(2));
			System.out.println("Customer House Number : "+cs.getString(3));
			System.out.println("Customer SName : "+cs.getString(4));
			System.out.println("Customer City : "+cs.getString(5));
			System.out.println("Customer State : "+cs.getString(6));
			System.out.println("Customer PinCode : "+cs.getInt(7));

			System.out.println("Customer MailId : "+cs.getString(8));
			System.out.println("Customer Phone Number :"+cs.getLong(9));
			
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
