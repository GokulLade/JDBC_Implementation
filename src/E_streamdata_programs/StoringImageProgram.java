package E_streamdata_programs;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class StoringImageProgram {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			
			Connection con=DatabaseConnection.getConnection();
			
			PreparedStatement ps=con.prepareStatement("INSERT INTO ImageData01 Values(?,?)");
			
			System.out.println("Enter Id For Image");
			int id=sc.nextInt();sc.nextLine();
			
			ps.setInt(1, id);
			
			System.out.println("Enter the Image name with full path to store");
			String path=sc.nextLine();//C:\Documents\imageid101.png
			
			File f=new File(path);
			
			if(f.exists())
			{
				FileInputStream fs=new FileInputStream(f);
				ps.setBinaryStream(2, fs);
				
				int result=ps.executeUpdate();
				
				if(result>0)
				{
					System.out.println("Image Stored Successfully..!");
				}
				else
				{
					System.out.println("Image Not Stored Try again");
				}
			}
			else
			{
				System.out.println("File is not Present in Path");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}
