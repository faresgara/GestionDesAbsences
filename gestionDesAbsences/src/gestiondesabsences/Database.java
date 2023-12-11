/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestiondesabsences;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class Database {

    int studentId, teacherId, adminId;
    String dbName = "gestiondesabsences";
    String USER = "root";
    String PASS = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/";
    String studentFirstName, studentLastName;
    String teacherFirstName, teacherLastName;
    String userName, password;
    Scanner sc = new Scanner(System.in);

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void getConnection() throws Exception {

        String url = "jdbc:mysql://localhost/";
        Connection conn = null;
    }

    public boolean checkStudentAbsence(int studentId) throws Exception {
        Class.forName(driver).newInstance();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestiondesabsences", "root", "");
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + dbName + ";");
            // String select = "SELECT idAbsence,idEnseignant,idMatiere,idClasse,numSeance,date FROM absence WHERE idEtudiant = ?";

            String select = "SELECT etudiant.prenom,etudiant.nom ,matiere.libelle,absence.numSeance ,enseignant.nom, absence.date FROM absence JOIN etudiant ON etudiant.idEtudiant=absence.idEtudiant JOIN matiere ON matiere.idMatiere=absence.idMatiere JOIN enseignant ON enseignant.idEnseignant=absence.idEnseignant WHERE etudiant.idEtudiant= ?";

            PreparedStatement query = conn.prepareStatement(select);
            query.setInt(1, studentId);
            ResultSet rs = query.executeQuery();
            if (!rs.next()) {
                return false;
            }
            while (rs.next()) {

                String studentFirstName = rs.getString("etudiant.prenom");
                String studentLastName = rs.getString("etudiant.nom");
                String subject = rs.getString("matiere.libelle");
                int sessionNum = rs.getInt("absence.numSeance");
                String teacherLastName = rs.getString("enseignant.nom");
                Date date = rs.getDate("absence.date");

                System.out.println("Absences for student: " + studentFirstName + " " + studentLastName);
                System.out.printf("%-20s %-20s %-20s %-20s %n", "Subject", "Session number", "Teacher", "Date");
                System.out.printf("%-20s %-20s %-20s %-20s %n", subject, sessionNum, teacherLastName, date);

            }
            query.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    public boolean login() throws Exception {
        System.out.println("Please enter your username");
        String userName = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();
        if (userCheck(userName, password) == true) {
            this.setUserName(userName);
            this.setPassword(password);
            System.out.print("Perfect");
            return true;
        } else {
            System.out.println("Wrong credentials, try again please");
            return false;
        }
    }

    public boolean userCheck(String userName, String password) throws Exception {
        Connection conn = null;
        PreparedStatement queryTeacher = null;
        ResultSet rsTeacher = null;
        PreparedStatement queryAdmin = null;
        ResultSet rsAdmin = null;
        PreparedStatement queryStudent = null;
        ResultSet rsStudent = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/", USER, PASS);
            Statement stmt = conn.createStatement();

            stmt.execute("USE " + dbName + ";");
            String selectTeacher = "SELECT * FROM enseignant WHERE login = ? AND pwd = ?";
            queryTeacher = conn.prepareStatement(selectTeacher);
            queryTeacher.setString(1, userName);
            queryTeacher.setString(2, password);
            rsTeacher = queryTeacher.executeQuery();

            if (rsTeacher.next()) {
                menu mn = new menu(this);
                mn.teacherMenu();
                return true;
            } else {
                String selectAdmin = "SELECT * FROM responsable WHERE login = ? AND pwd = ?";
                queryAdmin = conn.prepareStatement(selectAdmin);
                queryAdmin.setString(1, userName);
                queryAdmin.setString(2, password);
                rsAdmin = queryAdmin.executeQuery();

                if (rsAdmin.next()) {
                    menu mn = new menu(this);
                    mn.adminMenu();
                    return true;
                } else {
                    String selectStudent = "SELECT idEtudiant FROM etudiant WHERE login = ? AND pwd = ?";
                    queryStudent = conn.prepareStatement(selectStudent);
                    queryStudent.setString(1, userName);
                    queryStudent.setString(2, password);
                    rsStudent = queryStudent.executeQuery();

                    if (rsStudent.next()) {
                        this.setStudentId(rsStudent.getInt("idEtudiant"));
                        menu mn = new menu(this);
                        mn.studentMenu();
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsTeacher != null) {
                    rsTeacher.close();
                }
                if (queryTeacher != null) {
                    queryTeacher.close();
                }
                if (rsAdmin != null) {
                    rsAdmin.close();
                }
                if (queryAdmin != null) {
                    queryAdmin.close();
                }
                if (rsStudent != null) {
                    rsStudent.close();
                }
                if (queryStudent != null) {
                    queryStudent.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // handle the exception
            }
        }
        return false;
    }

    public void addAbsence(int studentId, int teacherId, int subjectId, int classId, int numSession, Date date) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestiondesabsences", USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + dbName + ";");
            // Get the student_id for the student with the given name
            String sql = "INSERT INTO absence (idEtudiant,idEnseignant,idMatiere,idClasse,numSeance,date)" + "values(?,?,?,?,?,?)";
            PreparedStatement insert = conn.prepareStatement(sql);
            insert.setInt(1, studentId);
            insert.setInt(2, teacherId);
            insert.setInt(3, subjectId);
            insert.setInt(4, classId);
            insert.setInt(5, numSession);
            insert.setDate(6, date);
            insert.executeUpdate();
            conn.close();
            insert.close();
            System.out.println("Successful addition");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getSubjects() {
        List<String> subjects = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestiondesabsences", USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + dbName + ";");
            String sql = "SELECT * FROM matiere";
            PreparedStatement query = conn.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                String subjectName = rs.getString("libelle");
                subjects.add(subjectName);
            }
            for (int i = 0; i < subjects.size(); i++) {
                System.out.println((i + 1) + ": " + subjects.get(i));

            }
        } catch (SQLException e) {
        }
    }

    public void getClasses() {
        List<String> classes = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestiondesabsences", USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + dbName + ";");
            String sql = "SELECT * FROM classe";
            PreparedStatement query = conn.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                String className = rs.getString("libelle");
                classes.add(className);
            }
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ": " + classes.get(i));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void getStudents() {
        List<String> students = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestiondesabsences", USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + dbName + ";");
            String sql = "SELECT * FROM etudiant";
            PreparedStatement query = conn.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("prenom");
                String lastName = rs.getString("nom");
                String studentName = firstName + " " + lastName;
                students.add(studentName);
            }
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ": " + students.get(i));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void removeAbsence(int studentId, int subjectId, Date date) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestiondesabsences", USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + dbName + ";");
            String query = "DELETE FROM absence WHERE idEtudiant = ? AND idMatiere = ? AND date = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, studentId);
            statement.setInt(2, subjectId);
            statement.setDate(3, date);
            statement.executeUpdate();
            conn.close();
            System.out.println("Successful remove absence");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void printAbsencesList() throws Exception {
        Class.forName(driver).newInstance();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestiondesabsences", USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + dbName + ";");
            String select = "SELECT etudiant.prenom,etudiant.nom ,matiere.libelle,absence.numSeance ,enseignant.nom, absence.date FROM absence JOIN etudiant ON etudiant.idEtudiant=absence.idEtudiant JOIN matiere ON matiere.idMatiere=absence.idMatiere JOIN enseignant ON enseignant.idEnseignant=absence.idEnseignant";

            PreparedStatement query = conn.prepareStatement(select);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                String studentFirstName = rs.getString("etudiant.prenom");
                String studentLastName = rs.getString("etudiant.nom");
                String subject = rs.getString("matiere.libelle");
                int sessionNum = rs.getInt("absence.numSeance");
                String teacherLastName = rs.getString("enseignant.nom");
                Date date = rs.getDate("absence.date");

                System.out.println("Absences for student: " + studentFirstName + " " + studentLastName);
                System.out.printf("%-20s %-20s %-20s %-20s %n", "Subject", "Session number", "Teacher", "Date");
                System.out.printf("%-20s %-20s %-20s %-20s %n", subject, sessionNum, teacherLastName, date);
            }
            query.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
