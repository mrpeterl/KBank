import java.sql.*;

public class DatabaseConnection {
    public static void main(String password, String command, String fields) {


        try {
            Class driver = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kbank", "root", password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(command);
            while(resultSet.next()) {
                String name = resultSet.getString(fields);
                System.out.println(name);

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
