package E_streamdata_programs;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class RetrievingImage {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			Connection con=DatabaseConnection.getConnection();
			
			PreparedStatement ps=con.prepareStatement("SELECT * FROM ImageData01 WHERE id=?");
			
			System.out.println("Enter the Image Id name");
			int id=sc.nextInt();sc.nextLine();
			
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Blob b=rs.getBlob(2);
				
				byte by[]=b.getBytes(1,(int) b.length());
				
				System.out.println("Enter Folder Path to save Image");
				String path=sc.nextLine(); //C:\Documents\imageid101.png
				
				File f=new File(path);
				FileOutputStream fos=new FileOutputStream(f);
				fos.write(by);
				
				System.out.println("Image Stored In Your Folder..!");
				fos.close();
			}
			else
			{
				System.out.println("Invalid ID");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
