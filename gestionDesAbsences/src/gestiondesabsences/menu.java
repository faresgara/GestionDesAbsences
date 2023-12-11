/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestiondesabsences;

import java.sql.Date;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class menu {

    Database db = new Database();
    Scanner sc = new Scanner(System.in);
    String enter;

    public menu(Database db) {
        this.db = db;
    }

    public void firstMenu() {
        System.out.println("Hello, please choose one of the following options");
        System.out.println("1 Log in");
        System.out.println("2 Exit");
        int action = sc.nextInt();
        try {
            switch (action) {

                case 1:
                    db.login();

                    break;
                case 2:
                    System.out.println("Thank you for using our Program");
                    break;
                default:
                    System.out.println("Not a valid option, please try again");
                    firstMenu();
            }
        } catch (Exception e) {
        }

    }

    public void studentMenu() throws Exception {
        // check absence 
        System.out.println("Press any key to see your absences");
        enter = sc.nextLine();
        db.checkStudentAbsence(db.getStudentId());
    }

    public void teacherMenu() throws Exception {
        int studentId;
        int teacherId;
        int subjectId;
        int classId;
        int numSession;
        Date date;

        System.out.println("please select one of the following options");
        System.out.println("1- Add absence");
        System.out.println("2- Check Students' absents");
        int action = sc.nextInt();
        switch (action) {
            case 1:
                System.out.println("Please enter the student id");
                studentId = sc.nextInt();
                System.out.println("Please enter the teacher id:");
                teacherId = sc.nextInt();
                System.out.println("Please enter the subject id, please choose the id of the chosen subject");
                db.getSubjects();
                subjectId = sc.nextInt();
                System.out.println("Please enter the class id:");
                db.getClasses();
                classId = sc.nextInt();
                System.out.println("Please enter the number of the session:");
                numSession = sc.nextInt();
                System.out.println("Please enter the date of the absence (yyyy-mm-dd):");
                date = Date.valueOf(sc.next());
                db.addAbsence(studentId, teacherId, subjectId, classId, numSession, date);
                System.out.println("enter any key if you want to go back to the menu");
                enter = sc.nextLine();
                teacherMenu();
                break;
            case 2:
                System.out.println("Please enter the student's id:");
                studentId = sc.nextInt();
                db.checkStudentAbsence(studentId);
                System.out.println("enter any key if you want to go back to the menu");
                enter = sc.nextLine();
                teacherMenu();
                break;
            default:
                System.out.println("Invalid option, please try again");
                teacherMenu();

        }

    }

    public void adminMenu() throws Exception {
        int studentId;
        int teacherId;
        int subjectId;
        int classId;
        int numSession;

        Date date;

        System.out.println("please select one of the following options");
        System.out.println("1- Add absences");
        System.out.println("2- Check Students' absences");
        System.out.println("3- Remove absences");
        System.out.println("4- Print absencences list");
        System.out.println("5- Send email to students");
        System.out.println("6- Print absences graph");
        System.out.println("7- go back");

        int action = sc.nextInt();
        switch (action) {
            case 1:
                System.out.println("Please enter the student id");
                studentId = sc.nextInt();
                System.out.println("Please enter the teacher id:");
                teacherId = sc.nextInt();
                System.out.println("Please enter the subject id, please choose the id of the chosen subject");
                db.getSubjects();
                subjectId = sc.nextInt();
                System.out.println("Please enter the class id:");
                db.getClasses();
                classId = sc.nextInt();
                System.out.println("Please enter the number of the session:");
                numSession = sc.nextInt();
                System.out.println("Please enter the date of the absence (yyyy-mm-dd):");
                date = Date.valueOf(sc.next());
                db.addAbsence(studentId, teacherId, subjectId, classId, numSession, date);
                System.out.println("enter any key if you want to go back to the menu");
                enter = sc.nextLine();
                adminMenu();
                break;
            case 2:
                System.out.println("Please enter the student's id:");
                studentId = sc.nextInt();
                db.checkStudentAbsence(studentId);
                System.out.println("enter any key if you want to go back to the menu");
                enter = sc.nextLine();
                adminMenu();
                break;
            case 3:
                System.out.println("Please enter the student's id:");
                db.getStudents();
                studentId = sc.nextInt();
                System.out.println("Please enter the subject's id:");
                db.getSubjects();
                subjectId = sc.nextInt();
                System.out.println("Please enter the date of the absence to remove (yyyy-mm-dd):");
                date = Date.valueOf(sc.next());
                db.removeAbsence(studentId, subjectId, date);
                adminMenu();
                break;
            case 4:
                db.printAbsencesList();
                break;
            case 5:
                System.out.println("Please enter the student's mail adresse:");
                subjectId = sc.nextInt();
                System.out.println("Please enter the class id:");
                classId = sc.nextInt();
                System.out.println("Please enter the subject id:");
                String subject = sc.next();
                System.out.println("Please enter the email title:");
                String body = sc.next();
                System.out.println("Email sent successfully");
                System.out.println("enter any key if you want to go back to the menu");
                enter = sc.nextLine();
                adminMenu();
                break;
            case 6:
                System.out.println("Please enter the subject id:");
                subjectId = sc.nextInt();
                System.out.println("Please enter the class id:");
                classId = sc.nextInt();
/// normally this will print the graph
                System.out.println("enter any key if you want to go back to the menu");
                enter = sc.nextLine();
                adminMenu();
                break;
            case 7:
                System.out.println("enter any key if you want to go back to the menu");
                enter = sc.nextLine();
                adminMenu();
                break;
            case 8:
                System.out.println("Thank you for using our program");
                break;
            default:
                System.out.println("Invalid option, please try again");
                adminMenu();
        }
    }
}
