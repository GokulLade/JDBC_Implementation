package K_MiniProject.StudentManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import A_connection_to_database.DatabaseConnection;

public class StudentDAO {

	// SQL queries
	String insert = "INSERT INTO Studentsminiproj (id, name, age, email, course) "
			+ "VALUES (Studproj_seq.NEXTVAL, ?, ?, ?, ?)";
	String selectAll = "SELECT * FROM Studentsminiproj";
	String update = "UPDATE Studentsminiproj SET name=?, age=?, email=?, course=? WHERE id=?";
	String delete = "DELETE FROM Studentsminiproj WHERE id=?";

	Scanner sc = new Scanner(System.in);

	private Connection conn = DatabaseConnection.getConnection();;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// Add Student Method
	public void addStudent() throws SQLException {

		System.out.println("--- Add New Student ---");
		System.out.print("Enter Name: ");
		String name = sc.nextLine();

		System.out.print("Enter Age: ");
		int age = Integer.parseInt(sc.nextLine());

		System.out.print("Enter Email: ");
		String email = sc.nextLine();

		System.out.print("Enter Course: ");
		String course = sc.nextLine();

		pstmt = conn.prepareStatement(insert);

		// set values
		pstmt.setString(1, name);
		pstmt.setInt(2, age);
		pstmt.setString(3, email);
		pstmt.setString(4, course);

		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			System.out.println("Student added successfully!");
		} else {
			System.out.println("Failed to add student.");
		}

	}

	// View All Students
	public void viewAllStudents() throws SQLException {
		pstmt = conn.prepareStatement(selectAll);
		rs = pstmt.executeQuery();

		System.out.println("\n--- All Students ---");
		System.out.println("ID\tName\t\tAge\tEmail\t\t\tCourse");
		System.out.println("-----------------------------------------------------------------------");

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String email = rs.getString("email");
			String course = rs.getString("course");

			System.out.println(id + "\t" + name + "\t" + age + "\t" + email + "\t" + course);
		}
	}

	// Update Student
	public void updateStudent() throws SQLException {
		System.out.println("--- Update Student ---");
		System.out.print("Enter Student ID to update: ");
		int id = Integer.parseInt(sc.nextLine());

		System.out.print("Enter New Name: ");
		String name = sc.nextLine();

		System.out.print("Enter New Age: ");
		int age = Integer.parseInt(sc.nextLine());

		System.out.print("Enter New Email: ");
		String email = sc.nextLine();

		System.out.print("Enter New Course: ");
		String course = sc.nextLine();

		pstmt = conn.prepareStatement(update);
		pstmt.setString(1, name);
		pstmt.setInt(2, age);
		pstmt.setString(3, email);
		pstmt.setString(4, course);
		pstmt.setInt(5, id);

		int rows = pstmt.executeUpdate();
		if (rows > 0) {
			System.out.println("Student updated successfully!");
		} else {
			System.out.println("Student with ID " + id + " not found.");
		}
	}
	
	
	//Delete Student
    public void deleteStudent() throws SQLException {
        System.out.println("--- Delete Student ---");
        System.out.print("Enter Student ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        pstmt = conn.prepareStatement(delete);
        pstmt.setInt(1, id);

        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }
	
}
