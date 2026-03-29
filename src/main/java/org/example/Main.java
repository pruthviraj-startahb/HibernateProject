package org.example;

import org.example.entity.Student;
import org.example.utils.CRUDOps;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CRUDOps crudOps = new CRUDOps();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== STUDENT MANAGEMENT SYSTEM ======");
            System.out.println("1. Insert Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Display Student By ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1 -> crudOps.insertStudent();

                case 2 -> crudOps.updateStudent();

                case 3 -> crudOps.deleteStudent();

                case 4 -> crudOps.getStudents();

                case 5 -> {
                    Student student;
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    student=crudOps.getStudentById(id);
                    System.out.println(student.getRoll()+" "+student.getSname()+" "+student.getClg()+" "+student.getDept());
                }

                case 0 -> System.out.println("Exiting program...");

                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}