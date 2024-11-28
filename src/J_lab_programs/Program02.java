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

/*
 
 Q1. JDBC program to insert player's info with player image into database table.
	Table:Player_info
    Table columns:pId,pName,pDob,pImage.

 Q2. Write a JDBC program to retrieve pImage based on pId and store that image at any location.

 */

public class Program02 {
	
	static Scanner sc=new Scanner(System.in);
	
	
	// Q1 Solution
	public static void storePlayerData(PreparedStatement ps) throws SQLException, FileNotFoundException
	{
		System.out.println("Enter the Player ID: ");
		int id=Integer.parseInt(sc.nextLine());
		System.out.println("Enter the Player Name: ");
		String name=sc.nextLine();
		System.out.println("Enter the Player Image Path : ");
		String path=sc.nextLine();
		
		ps.setInt(1, id);
		ps.setString(2, name);
		
		File f=new File(path);
		if(f.exists())
		{
			FileInputStream fis=new FileInputStream(f);
			ps.setBinaryStream(3, fis);
			
			int result =ps.executeUpdate();
			if(result>0)
			{
				System.out.println("Data Stored successfully...");
			}
			else
			{
				System.err.println("Something is Wrong Try Again..");
			}
		}
		else
		{
			System.out.println("Image Path is Invalid...");
		}
	}
	
	
	// Q2 Solution 
	public static void retrieverImage(PreparedStatement ps) throws SQLException, IOException
	{
		System.out.println("Enter the Player Id number: ");
		int id=Integer.parseInt(sc.nextLine());
		ps.setInt(1, id);
		
		ResultSet rs=ps.executeQuery();
		
		if(rs.next())
		{
			Blob b=rs.getBlob(3);
			byte by[]=b.getBytes(1, (int) b.length());
			
			System.out.println("Enter the Path store the Image: ");
			String path=sc.nextLine();
			
			File f=new File(path);
			FileOutputStream fos=new FileOutputStream(f);
			fos.write(by);
			System.out.println("Image Stored Successfully..");
			fos.close();
		}
		else
		{
			System.out.println("Invalid Id number..");
		}
		
	}

	public static void main(String[] args) 
	{
		
		try {
			Connection con=DatabaseConnection.getConnection();
			PreparedStatement ps1=con.prepareStatement("Insert into Player_Info values(?,?,?)");
			PreparedStatement ps2=con.prepareStatement("Select * from Player_info Where pid=?");
			
			System.out.println("1. Store Player Data");
			System.out.println("2. Retrieve Player Image using Player Id");
			System.out.println("Enter the your Choice: ");
			int ch=Integer.parseInt(sc.nextLine());
			
			switch(ch)
			{
				case 1:
					storePlayerData(ps1);
					break;
				case 2:
					retrieverImage(ps2);
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
