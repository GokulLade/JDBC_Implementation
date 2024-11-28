package J_lab_programs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

/*Program_01:
 
	Step01: Create Table:emp_info
	Columns: empId,empName,empAddress,empMailId,empPhNo,empResume(text file).
	
	Step02:Write a JDBC program to insert employee details into database table.
	
	Program_02:
		Write a JDBC program to retrieve empResume based on empPhNo and store that resume(file) at any location in your System.

*/

public class Program01 {
	
	static final Scanner sc=new Scanner(System.in);
	
	//Program-01
	public static void setEmployeeInfo(PreparedStatement ps) throws SQLException, FileNotFoundException
	{
		System.out.println("Enter the Employee Id :");
		int id=Integer.parseInt(sc.nextLine());
		System.out.println("Enter the Employee Addreess: ");
		String address=sc.nextLine();
		System.out.println("Enter the Employee Email ID: ");
		String email=sc.nextLine();
		System.out.println("Enter the Employee Phone Number: ");
		long phNumber=Integer.parseInt(sc.nextLine());
		System.out.println("Enter the Resume Full Path");
		String resumePath=sc.nextLine();
		
		ps.setInt(1, id);
		ps.setString(2, address);
		ps.setString(3, email);
		ps.setLong(4, phNumber);
		
		//Storing Resume
		File f=new File(resumePath);
		
		if(f.exists())
		{
			FileInputStream fis=new FileInputStream(f);
			ps.setBinaryStream(5, fis);
			
			int result =ps.executeUpdate();
			
			if(result>0)
			{
				System.out.println("Employee Information is Stored Successfully...");
			}
			else
			{
				System.out.println("Some thing is Wrong Try Agin..");
			}
			
		}
		else {
			System.out.println("Resume is not Not Available in path");
		}
		
	}
	
	//Program-02
	public static void retrieveEmpResume(PreparedStatement ps) throws SQLException, IOException
	{
		System.out.println("Enter the Mobile Number: ");
		long phNumber=Long.parseLong(sc.nextLine());
		
		ps.setLong(1, phNumber);
		
		ResultSet rs=ps.executeQuery();
	
		if(rs.next())
		{
			Blob b=rs.getBlob(5);
			byte by[]=b.getBytes(1, (int) b.length());
			
			System.out.println("Enter the full path with file name to store the information: ");
			String path=sc.nextLine();
			
			File f=new File(path);
			FileOutputStream fos=new FileOutputStream(f);
			
			fos.write(by);
			System.out.println("File Stored Succcessfully..");
			fos.close();
			
		}
		else
		{
			System.out.println("Invalid Phno number..");
		}
		
	}

	public static void main(String[] args) 
	{
		
		
		try(sc)
		{
			Connection con=DatabaseConnection.getConnection();
			
			PreparedStatement ps1=con.prepareStatement("Insert into emp_info values(?,?,?,?,?)");
			PreparedStatement ps2= con.prepareStatement("Select * from Emp_info WHERE empphno=?");
			
			System.out.println("1. Store Employee Data");
			System.out.println("2. Retrieve Employee Resume base on mobile number");
			System.out.println("Enter your Choice");
			int ch=Integer.parseInt(sc.nextLine());
			
			switch(ch)
			{
				case 1:
					setEmployeeInfo(ps1);
					break;
					
				case 2:
					retrieveEmpResume(ps2);
					break;
				
				default:
					System.out.println("Enter the valid Choice");
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}