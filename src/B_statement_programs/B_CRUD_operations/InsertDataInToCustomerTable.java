package B_statement_programs.B_CRUD_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class InsertDataInToCustomerTable {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try (s;) {

			System.out.println("Enter Customer Account Number..!");
			long accountNumber = Long.parseLong(s.nextLine());

			System.out.println("Enter Customer Name..!");
			String customerName = s.nextLine();

			System.out.println("Enter Balance..!");
			int balance = Integer.parseInt(s.nextLine());

			System.out.println("Enter Account Type..!!");
			String accountType = s.nextLine();

			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "Gokul", "9112");

			Statement stm = con.createStatement();

			int result = stm.executeUpdate(
					"insert into Bankcustomer(accno,custname,balance,acctype) 					  						values("
							+ accountNumber + ",'" + customerName + "'," + balance + ",'" + accountType + "')");

			if (result > 0) {
				System.out.println("Data Inserted Successfully");
			} else {
				System.out.println("Data not Inserted Try again..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
