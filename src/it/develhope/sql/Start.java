package it.develhope.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) {

        try {
            List<Student> italianStudents = new ArrayList<>();
            List<Student> germanStudents = new ArrayList<>();

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "developer", "developerpass1");
            Statement statement = conn.createStatement();

            //creating and executing the views:
            String itaView = "CREATE VIEW italian_students AS SELECT first_name, last_name FROM students WHERE country = 'Italy';";
            statement.execute(itaView);
            String deuView = "CREATE VIEW german_students AS SELECT first_name, last_name FROM students WHERE country = 'Germany';";
            statement.execute(deuView);

            //writing queries from the views
            String selectItaStudents = "SELECT * FROM newdb.italian_students";
            String selectDeuStudents = "SELECT * FROM newdb.german_students";

            ResultSet rsITA = statement.executeQuery(selectItaStudents);
            while (rsITA.next()) {
                //getting each set result from the queries to create a Student and set values to his variables
                //then adding it to the arrayList
                String nome = rsITA.getString("first_name");
                String cognome = rsITA.getString("last_name");
                Student student = new Student(nome, cognome);
                italianStudents.add(student);
            }

            ResultSet rsDEU = statement.executeQuery(selectDeuStudents);
            while (rsDEU.next()) {
                String nome = rsDEU.getString("first_name");
                String cognome = rsDEU.getString("last_name");
                Student student = new Student(nome, cognome);
                germanStudents.add(student);
            }

            //printing each student value using getters
            System.out.println("Printing italian students:");
            for (Student itaStudent : italianStudents) {
                System.out.println("Nome: " + itaStudent.getName() + ", cognome: " + itaStudent.getSurname());
            }

            System.out.println("Printing german students:");
            for (Student deuStudent : germanStudents) {
                System.out.println("Nome: " + deuStudent.getName() + ", cognome: " + deuStudent.getSurname());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
