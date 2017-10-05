import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    public static void main(String password, String command) {
        Scanner test = new Scanner(System.in);
        System.out.println("Please enter password");
        password = test.nextLine();
        String passwordTest = password;
        System.out.println("Please enter sql command");
        command = test.nextLine();

        try {
            Class driver = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:://localhost/demo", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(command);
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(name);

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
