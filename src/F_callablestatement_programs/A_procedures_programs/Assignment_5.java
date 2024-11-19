package F_callablestatement_programs.A_procedures_programs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class Assignment_5 {

	public static void main(String[] args) 
	{
		Scanner sc =new Scanner(System.in);
		
		try(sc;)
		{
			Connection con=DatabaseConnection.getConnection();
			
			CallableStatement cs=con.prepareCall("{call InsertEmpDetails68(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter the Employee Id: ");
			String id=sc.nextLine();
			System.out.println("Enter the Employee Name: ");
			String name=sc.nextLine();
			System.out.println("Enter the Employee Degination: ");
			String desg=sc.nextLine();
			System.out.println("Enter the Employee House Number: ");
			String houseNumber=sc.nextLine();
			System.out.println("Enter the Employee S-Name: ");
			String sname=sc.nextLine();
			System.out.println("Enter the Employee City: ");
			String city=sc.nextLine();
			System.out.println("Enter the State Name: ");
			String st=sc.nextLine();
			System.out.println("Enter Your City Pincode: ");
			int pincode=Integer.parseInt(sc.nextLine());
			System.out.println("Enter your Mail Id: ");
			String mid=sc.nextLine();
			System.out.println("Enter the Phone Number: ");
			long phno=Long.parseLong(sc.nextLine());
			System.out.println("Enter your Base Salary: ");
			float bsal=Float.parseFloat(sc.nextLine());
			System.out.println();
			
			float hra=(float)(0.63*bsal);
			float da=(float)(0.91*bsal);
			float totSal=(bsal+hra+da);
	
			
			cs.setString(1, id);
			cs.setString(2, name);
			cs.setString(3, desg);
			cs.setString(4, houseNumber);
			cs.setString(5, sname);
			cs.setString(6, city);
			cs.setString(7, st);
			cs.setInt(8, pincode);
			cs.setString(9, mid);
			cs.setLong(10, phno);
			cs.setFloat(11, bsal);
			cs.setFloat(12, hra);
			cs.setFloat(13, da);
			cs.setFloat(14, totSal);
			
			cs.execute();
			
			System.out.println("Employee Details Stored Successfully...!");
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
