package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManagementSystem {
	public static void main(String[] args) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "@Nethesh06");

	        createTable(connection);
	        insertData(connection);
	        updateData(connection);
	        deleteData(connection);
	        selectData(connection);

	        connection.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

	private static void createTable(Connection connection) throws SQLException {
	    String createTableQuery = "CREATE TABLE IF NOT EXISTS student (studentId INT, name VARCHAR(50), grades DECIMAL(10, 2))";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
	        preparedStatement.executeUpdate();
	        System.out.println("Table created successfully");
	    }
	}

	private static void insertData(Connection connection) throws SQLException {
	    String insertQuery = "INSERT INTO student (studentId, name, grades) VALUES (?, ?, ?)";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
	        preparedStatement.setInt(1, 1);
	        preparedStatement.setString(2, "John Doe");
	        preparedStatement.setDouble(3, 85.5);
	        

	        preparedStatement.executeUpdate();
	        preparedStatement.setInt(1, 2);
	        preparedStatement.setString(2, "Nethesh");
	        preparedStatement.setDouble(3, 85.5);
	        

	        preparedStatement.executeUpdate();
	        System.out.println("Data inserted successfully");
	    }
	}

	private static void updateData(Connection connection) throws SQLException {
	    String updateQuery = "UPDATE student SET grades = ? WHERE studentId = ?";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	        preparedStatement.setDouble(1, 90.0);
	        preparedStatement.setInt(2, 1);

	        preparedStatement.executeUpdate();
	        System.out.println("Data updated successfully");
	    }
	}

	private static void deleteData(Connection connection) throws SQLException {
	    String deleteQuery = "DELETE FROM student WHERE studentId = ?";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
	        preparedStatement.setInt(1, 1);

	        preparedStatement.executeUpdate();
	        System.out.println("Data deleted successfully");
	    }
	}

	private static void selectData(Connection connection) throws SQLException {
	    String selectQuery = "SELECT * FROM student";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
	        ResultSet resultSet = preparedStatement.executeQuery();

	        System.out.println("Student Details:");

	        while (resultSet.next()) {
	            int studentId = resultSet.getInt("studentId");
	            String name = resultSet.getString("name");
	            double grades = resultSet.getDouble("grades");

	            System.out.println("Student ID: " + studentId);
	            System.out.println("Name: " + name);
	            System.out.println("Grades: " + grades);
	            System.out.println("-----------------------");
	        }
	    }
	}

}
