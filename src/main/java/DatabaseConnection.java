import java.sql.*;

public class DatabaseConnection {
    public static void main(String password, String command, int utility) {


        try {
            Class driver = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kbank", "root", password);
            Statement statement = connection.createStatement();
            switch (utility){
                case 1:
                    statement.executeUpdate(command);
                    break;
                case 2:
                    ResultSet resultSet = statement.executeQuery(command);
                    while(resultSet.next()) {
                        String name = resultSet.getString("firstName");
                        System.out.println(name);
                    }
                    break;
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
