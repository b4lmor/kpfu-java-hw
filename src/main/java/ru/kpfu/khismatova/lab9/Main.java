package ru.kpfu.khismatova.lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stmt = conn.createStatement();

        String dropTableSQL = "DROP TABLE STUDENTS";
        stmt.execute(dropTableSQL);

        String createTableSQL = "CREATE TABLE Students (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "group_name VARCHAR(255), " +
                "first_name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "gender VARCHAR(100), " +
                "age INT)";
        stmt.execute(createTableSQL);

        String insertDataSQL = "INSERT INTO Students (group_name, first_name, last_name, gender, age) VALUES " +
                "('09-263', 'Малика', 'Хабриева', 'Женский', 20), " +
                "('09-262', 'Эмиль', 'Низамов', 'Мужской', 22), " +
                "('09-263', 'Альбина', 'Хисматова', 'Женский', 19), " +
                "('09-363', 'Артём', 'Лисицин', 'Мужской', 21), " +
                "('09-263', 'Дарья', 'Зайцева', 'Женский', 20), " +
                "('09-263', 'Лера', 'Кощеева', 'Женский', 20)";
        stmt.executeUpdate(insertDataSQL);

        String selectGirlsSQL = "SELECT * FROM Students WHERE gender = 'Женский'";
        ResultSet rsGirls = stmt.executeQuery(selectGirlsSQL);
        System.out.println("Все девочки:");
        while (rsGirls.next()) {
            System.out.println("ID: " + rsGirls.getInt("id") +
                    ", Name: " + rsGirls.getString("first_name") +
                    " " + rsGirls.getString("last_name") +
                    ", Group: " + rsGirls.getString("group_name") +
                    ", Age: " + rsGirls.getInt("age"));
        }
        rsGirls.close();

        String selectOlderThan18SQL = "SELECT * FROM Students WHERE age > 20";
        ResultSet rsOlderThan20 = stmt.executeQuery(selectOlderThan18SQL);
        System.out.println("\nВсе студенты старше 20:");
        while (rsOlderThan20.next()) {
            System.out.println("ID: " + rsOlderThan20.getInt("id") +
                    ", Name: " + rsOlderThan20.getString("first_name") +
                    " " + rsOlderThan20.getString("last_name") +
                    ", Group: " + rsOlderThan20.getString("group_name") +
                    ", Age: " + rsOlderThan20.getInt("age"));
        }
        rsOlderThan20.close();

        String selectGroupA_SQL = "SELECT * FROM Students WHERE group_name = '09-263'";
        ResultSet rsGroupA = stmt.executeQuery(selectGroupA_SQL);
        System.out.println("\nВсе студенты группы 09-263:");
        while (rsGroupA.next()) {
            System.out.println("ID: " + rsGroupA.getInt("id") +
                    ", Name: " + rsGroupA.getString("first_name") +
                    " " + rsGroupA.getString("last_name") +
                    ", Age: " + rsGroupA.getInt("age"));
        }
        rsGroupA.close();

        stmt.close();
        conn.close();
    }
}