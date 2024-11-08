package C_preparedstatement_programs.A_assignment;
/*
 
Assignment-4: 

	Step-1 : Create table with name User68 (uname, pword, fname, lname, city, mid, phno)
               primary Key : uname

	step-2 : Construct JDBC Application to perform the following operations based on
               User choice:

				1. AddUserDetails -> Done
				2. ViewUserDetails(based on uname and pword)
				3. UpdateUserDetails(based on uname and pword)(city,mid,phno)
				4. DeleteUserDetails(based on uname and pword)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Assignment_4 {
	
	//Scanner object for getting input from console
	static Scanner sc=new Scanner(System.in);
	
	// Adding Data into user 
	public static boolean addUserData(PreparedStatement ps) throws SQLException
	{
		//Input form user
		System.out.println("Enter the user Name...!");
		String uname=sc.nextLine(); uname=sc.nextLine(); 
		
		System.out.println("Enter the Password...!");
		String pword=sc.nextLine();
		
		System.out.println("Enter the First Name...!");
		String fname=sc.nextLine(); 
		
		System.out.println("Enter the Last Name...!");
		String lname=sc.nextLine();
		
		System.out.println("Enter the City Name...!");
		String city=sc.nextLine();
		
		System.out.println("Enter the Email-Id...!");
		String mid=sc.nextLine();
		
		System.out.println("Enter the Phone Number...!");
		int phno=Integer.parseInt(sc.nextLine());
		
		//Set data to object
		ps.setString(1, uname); 
		ps.setString(2, pword);
		ps.setString(3, fname);
		ps.setString(4, lname);
		ps.setString(5, city);
		ps.setString(6, mid);
		ps.setInt(7, phno);
		
		//Execute query
		int result=ps.executeUpdate();
		
		// Return result
		if(result>0)
		{
			return true;
		}
		
		return false;
	}
	
	
	// View User Data
	public static void viewUserData(PreparedStatement ps) throws SQLException
	{
		//Input from user
		System.out.println("Enter the User Name for View Data...!");
		String uname=sc.nextLine();uname=sc.nextLine();
		
		System.out.println("Enter Password...!");
		String pword=sc.nextLine();
		
		//Set data to object
		ps.setString(1, uname);
		ps.setString(2, pword);
		
		//Execute Query
		ResultSet rs=ps.executeQuery();
		
		// Print Result
		if(rs.next())
		{
			System.out.println("User Name is : "+ rs.getString(1));
			System.out.println("User Password is : "+ rs.getString(2));
			System.out.println("First Name is : "+ rs.getString(3));
			System.out.println("Last Name is : "+ rs.getString(4));
			System.out.println("City is : "+ rs.getString(5));
			System.out.println("Email-Id is : "+ rs.getString(6));
			System.out.println("Phone Number is : "+ rs.getInt(7));
		}
		else
		{
			System.err.println("Invalid User name and Password..!");
		}
	}
	
	
	// Update user Data
	public static void updateUserData(PreparedStatement ...ps) throws SQLException
	{
		//Input from user
		System.out.println("Enter the User Name for Update...!");
		String uname=sc.nextLine();uname=sc.nextLine();
				
		System.out.println("Enter Password...!");
		String pword=sc.nextLine();
		
		//Set data to object
		ps[0].setString(1, uname);
		ps[0].setString(2, pword);
		
		//Execute Query
		ResultSet rs=ps[0].executeQuery();
		
		//Check User is Present or not
		if(rs.next())
		{
			//Input from user
			System.out.println("User Old city is : "+rs.getString(5));
			System.out.println("Enter the new city name : ");
			String city=sc.nextLine();city=sc.nextLine();
			
			System.out.println("User Old Email-id is : "+rs.getString(6));
			System.out.println("Enter new email-id : ");
			String mid=sc.nextLine();
			
			System.out.println("User Old Phone Number is : "+rs.getString(7));
			System.out.println("Enter new Phone Number : ");
			int phno=Integer.parseInt(sc.nextLine());
			
			//Set data to object
			ps[1].setString(1, city);
			ps[1].setString(2, mid);
			ps[1].setInt(3, phno);
			
			ps[1].setString(4, uname);
			ps[1].setString(5, pword);
			
			//Execute Query
			int result = ps[1].executeUpdate();
			
			// Return result
			if(result>0)
			{
				System.out.println("Data Updated Successfully..!");
			}
			else
			{
				System.out.println("Data is not Updated Try again..!");
			}
		}
		else
		{
			System.err.println("Invalid User name and Password..!");
		}
		
	}
	
	
	//Delete User Data
	public static void deleteUserData(PreparedStatement ...ps) throws SQLException
	{
		//Input from user
		System.out.println("Enter the User Name for Delete Record...!");
		String uname=sc.nextLine();uname=sc.nextLine();
				
		System.out.println("Enter Password...!");
		sc.nextLine();
		String pword=sc.nextLine();
		
		
		//Set data to object
		ps[0].setString(1, uname);
		ps[0].setString(2, pword);
		
		//Execute Query
		ResultSet rs=ps[0].executeQuery();
		
		if(rs.next())
		{
			ps[1].setString(1, uname);
			ps[1].setString(2,pword);
			
			int result = ps[1].executeUpdate();
			
			if(result>0)
			{
				System.out.println("Data Deleted Successfully..!");
			}
			else
			{
				System.out.println("Data not delete Try again..");
			}
		}
		else
		{
			System.err.println("Invalid user name and password");
		}
		
	}
	
	// Main method
	public static void main(String[] args) 
	{
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Gokul","9112");
			
			PreparedStatement ps1=con.prepareStatement("insert into User68 values(?,?,?,?,?,?,?)");
			
			PreparedStatement ps2=con.prepareStatement("select * from user68 where uname=? AND pword=?");
			
			PreparedStatement ps3=con.prepareStatement("update user68 set city=?, mid=?, phno=? where uname=? AND pword=?");
			
			PreparedStatement ps4=con.prepareStatement("delete from user68 where uname=? AND pword=?");
			
			while(true)
			{
				System.out.println("\n-------------* WELCOME TO YOUR APPLICATION *-------------\n");
				
				System.out.println(" -> 1. Add User Details ");
				System.out.println(" -> 2. View User Details ");
				System.out.println(" -> 3. Update User Details");
				System.out.println(" -> 4. Delete User Details ");
				System.out.println(" -> 5. Exit Application ");
				System.out.println();
				
				System.out.println("--- Please Enter your choice ---");
				int choice=sc.nextInt();
				sc.nextLine();
				
				switch(choice)
				{
					case 1:
							if(addUserData(ps1))
							{
								System.out.println("User Information added Successfully..!");
							}
							else
							{
								System.out.println("User Information Not added Try again...");
							}
							break;
					
					case 2:
							viewUserData(ps2);
							break;
					
					case 3:
							updateUserData(ps2,ps3);
							break;
							
					case 4:
							deleteUserData(ps2,ps4);
							break;
					case 5:
							System.out.println("Application Closed");
							System.exit(0);
						
				}
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println("You are not Enter the Write Input Try again Your mistak is :");
			e.printStackTrace();
			
			String arr[]= {"Only for calling main method"};
			main(arr);
		}

	}
}
