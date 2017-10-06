import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    public static void main(String password, String command, int utility) {


        try {
            Class driver = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kbank?useSSL=false", "root", password);
            Statement statement = connection.createStatement();
            switch (utility){
                case 1:
                    Scanner depositScanner = new Scanner(System.in);
                    statement.executeUpdate(command);
                    System.out.println("Please enter the amount you would like to deposit");
                    int deposit = depositScanner.nextInt();
                    statement.executeUpdate("INSERT INTO account VALUES(NULL, LAST_INSERT_ID(), "+ deposit + ");");
                    break;
                case 2:
                    ResultSet resultSet = statement.executeQuery(command);
                    while(resultSet.next()) {
                        String name = resultSet.getString("customer.firstName") + " " + resultSet.getString("account.balance") + " " +resultSet.getString("account.accountID");
                        System.out.println(name);
                    }
                    break;
                case 3:
                    statement.executeUpdate(command);
                    break;
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
