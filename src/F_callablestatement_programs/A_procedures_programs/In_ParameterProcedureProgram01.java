package F_callablestatement_programs.A_procedures_programs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class In_ParameterProcedureProgram01 {

	public static void main(String[] args) 
	{
		Scanner s=new Scanner(System.in);
		try(s) {
			
			Connection con=DatabaseConnection.getConnection();
			
			CallableStatement cs=con.prepareCall("{call InsertCustDetails68(?,?,?,?,?,?,?,?,?)}"); 
			
			System.out.println("-----------CustomerDetails----------");
			System.out.println("Enter the Cust-Id:");
			String cId = s.nextLine();
			System.out.println("Enter the Cust-Name:");
			String cName = s.nextLine();
			System.out.println("Enter the Cust-HNO:");
			String hNo = s.nextLine();
			System.out.println("Enter the Cust-SName:");
			String sName = s.nextLine();
			System.out.println("Enter the Cust-City:");
			String city = s.nextLine();
			System.out.println("Enter the Cust-State:");
			String state = s.nextLine();
			System.out.println("Enter the Cust-PinCode:");
			int pinCode = Integer.parseInt(s.nextLine());
			System.out.println("Enter the Cust-MailId:");
			String mId = s.nextLine();
			System.out.println("Enter the Cust-PhoneNo:");
			long phNo = s.nextLong();
			
			cs.setString(1,cId);
			cs.setString(2, cName);
			cs.setString(3, hNo);
			cs.setString(4, sName);
			cs.setString(5, city);
			cs.setString(6, state);
			cs.setInt(7, pinCode);
			cs.setString(8, mId);
			cs.setLong(9, phNo);
			
			cs.execute();
			
			System.out.println("Cust details added Successfully....");
			
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
