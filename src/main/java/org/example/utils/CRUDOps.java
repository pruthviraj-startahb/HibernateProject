package org.example.utils;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class CRUDOps {

    int roll,ch,upRoll;
    String name;
    String clg;
    String dept;
    String div;

        Configuration config = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class);

        SessionFactory factory = config.buildSessionFactory();


    Scanner sc=new Scanner(System.in);

    public void insertStudent() {
        Transaction tx = null;

        try (Session session = factory.openSession()) {
            System.out.println("Enter Roll Number : ");
            roll = sc.nextInt();
            sc.nextLine();

            Student existingStudent = session.get(Student.class, roll);

            if (existingStudent != null) {
                throw new RuntimeException("Roll number already exists!");
            }

            System.out.println("\nEnter Student Name : ");
            name = sc.nextLine();

            System.out.println("\nEnter Student College : ");
            clg = sc.nextLine();

            System.out.println("\nEnter Student Department :");
            dept = sc.nextLine();

            System.out.println("\nEnter Student Division :");
            div = sc.next();

            Student student = new Student(roll, name, dept,clg, div);

            tx = session.beginTransaction();
            session.persist(student);
            tx.commit();

            System.out.println(student);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateStudent() {
        Transaction tx = null;

        try (Session session = factory.openSession()) {

            System.out.println("Enter Roll number:");
            upRoll = sc.nextInt();
            sc.nextLine();

            Student student = session.get(Student.class, upRoll);

            if (student == null) {
                System.out.println("Student not found!");
                return;
            }

            System.out.println("1.Name  2.College  3.Department");
            ch = sc.nextInt();
            sc.nextLine();

            tx = session.beginTransaction();

            switch (ch) {
                case 1 -> student.setSname(sc.nextLine());
                case 2 -> student.setClg(sc.nextLine());
                case 3 -> student.setDept(sc.nextLine());
                default -> {
                    System.out.println("Invalid choice!");
                    return;
                }
            }

            session.merge(student);
            tx.commit();

            System.out.println("Updated Successfully!");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void deleteStudent() {
        Transaction tx = null;

        try (Session session = factory.openSession()) {

            System.out.println("Enter Roll number:");
            int roll = sc.nextInt();

            Student student = session.get(Student.class, roll);

            if (student == null) {
                System.out.println("Student not found!");
                return;
            }

            tx = session.beginTransaction();
            session.remove(student);
            tx.commit();

            System.out.println("Deleted successfully!");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getStudents() {
        Session session=factory.openSession();
        List<Student> students = session
                .createQuery("from Student", Student.class)
                .setCacheable(true)
                .list();

        if (students.isEmpty()) {
            System.out.println("No records found!");
            return;
        }

        for (Student s : students) {
            System.out.println(s.getRoll()+" "+s.getSname()+" "+s.getClg()+" "+s.getDept());
        }

        session.close();
    }

    public Student getStudentById(int id) {
        try {
            Session session = factory.openSession();
            Student student = session.get(Student.class, id);
            session.close();
            return student;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    

}
