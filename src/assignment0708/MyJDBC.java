package assignment0708;

import java.sql.*;

public class MyJDBC {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dummy";

    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
            String sql1 = "SELECT teachers.name, salary, students.name AS student\n" +
                    "FROM teacher_student AS ts JOIN students ON ts.student_id = students.id\n" +
                    "JOIN teachers ON ts.teacher_id = teachers.id;";
            String sql2 = "SELECT teachers.name, COUNT(students.name) AS num_stu\n" +
                    "FROM teacher_student AS ts JOIN students ON ts.student_id = students.id\n" +
                    "JOIN teachers ON ts.teacher_id = teachers.id\n" +
                    "GROUP BY teachers.id;";
            String sql3 = "SELECT name,salary as 2ndHighSalary\n" +
                    "FROM (\n" +
                    "      SELECT name, salary, dense_rank() OVER (ORDER BY salary DESC) dr FROM teachers) t\n" +
                    "WHERE dr = 2;";
            statement = connection.prepareStatement(sql1);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String teacherName = resultSet.getString("teachers.name");
                double salary = resultSet.getDouble("salary");
                String studentName = resultSet.getString("student");
                System.out.println("teacherName: "+teacherName);
                System.out.println("salary: "+salary);
                System.out.println("studentName: "+studentName);
                System.out.println("**********************");
            }
            statement = connection.prepareStatement(sql2);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String teacherName = resultSet.getString("teachers.name");
                int studentNumber = resultSet.getInt("num_stu");
                System.out.println("teacherName: "+teacherName);
                System.out.println("studentNumber: "+studentNumber);
                System.out.println("**********************");
            }
            statement = connection.prepareStatement(sql3);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String teacherName = resultSet.getString("name");
                double salary = resultSet.getDouble("2ndHighSalary");
                System.out.println("teacherName: "+teacherName);
                System.out.println("2ndHighSalary: "+salary);
                System.out.println("**********************");
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
