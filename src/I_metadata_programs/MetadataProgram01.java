package I_metadata_programs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import A_connection_to_database.DatabaseConnection;

public class MetadataProgram01 {

	public static void main(String[] args) 
	{
		try {
			
			System.out.println("1.Database Metadata: ");
			Connection con=DatabaseConnection.getConnection();
			DatabaseMetaData dm=con.getMetaData();
			System.out.println("  JDBC Driver name is : "+dm.getDriverName());
			System.out.println("  Oracle Version is : "+dm.getDatabaseMajorVersion());
			
			System.out.println("2.Parameter Metadata: ");
			PreparedStatement ps1=con.prepareStatement("Insert into Bank68 values(?,?,?,?)");
			ParameterMetaData pm=ps1.getParameterMetaData();
			System.out.println("  Parameter Count is : "+pm.getParameterCount());
			
			System.out.println("3.ResultSet Metadata: ");
			PreparedStatement ps2=con.prepareStatement("Select accno,name,balance,acctype from Bank68");
			ResultSet rs=ps2.executeQuery();
			ResultSetMetaData rm=rs.getMetaData();
			System.out.println("Column Count is : "+rm.getColumnCount());
			System.out.println("Column-1 is : "+rm.getColumnName(1));
			System.out.println("Column-2 is : "+rm.getColumnName(2));
			System.out.println("Column-3 is : "+rm.getColumnName(3));
			System.out.println("Column-4 is : "+rm.getColumnName(4));
			
			System.out.println(" Bank Table information");
			System.out.println("AcountNumber  Name  Balance AcountType");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
			}
			
		}
		catch(Exception e)
		{
			
		}
	}

}
