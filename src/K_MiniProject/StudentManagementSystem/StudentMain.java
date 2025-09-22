package K_MiniProject.StudentManagementSystem;

import java.util.Scanner;

public class StudentMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		boolean exit = false;

		while (!exit) {
			System.out.println("------------ Student Management System ----------------");
			System.out.println("1. Add Student");
			System.out.println("2. View All Students");
			System.out.println("3. Update Student");
			System.out.println("4. Delete Student");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			try {
			int choice = 0;
			choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			
			case 1:
				dao.addStudent();
				break;
			case 2:
				dao.viewAllStudents();
				break;
			case 3:
				dao.updateStudent();
				break;
			case 4:
				dao.deleteStudent();
				break;
			case 5:
				exit = true;
				System.out.println("Exiting Student Management System..!");
				break;
			default:
				System.out.println("Invalid choice! Please try again.");
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		sc.close();

	}

}
