import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Scanner;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Main {

    public static void main(String[] args)
    {
        //DatabaseConnection.main("password", "INSERT INTO customer(firstName, lastName, gender, dateOfBirth, address, email, phone) VALUES ('Test', 'Data', 'f', '1999-01-01', '123 Rainbow drive', 'test@gmail.com', '01234567891');", 1);
        //DatabaseConnection.main("password", "SELECT * FROM customer WHERE id = 00000001", 2);
        menu();
    }

    public static void menu() {
        int selection;
        System.out.println("Welcome to KBank!");
        do {
            String mainMessage = String.format("Please enter the number corresponding to your desired action"
                    + "\n1.I would like to create a new account"
                    + "\n2.I would like to access my account"
                    + "\n3.Exit");
            System.out.println(mainMessage);
            Scanner menuScanner = new Scanner(System.in);

            selection = menuScanner.nextInt();
            switch (selection) {
                case 1:
                    createAccount();

                    break;
                case 2:
                    //Access account via login
                    break;

                case 3:
                    System.exit(0);

                    break;
                default:
                    System.out.println("Error. Please enter a number between 1 and 3");
                    menu();//Show menu again if invalid input
            }
        }while(selection!=3);

    }

    static void createAccount() {
        String firstName, secondName, dateOfBirth, address, email, phone;
        char gender = ' ';
        Scanner infoScanner = new Scanner(System.in);
        System.out.println("\nThank you for deciding to create an account with KBank!" +
                "\nWhat is your first name?");
        firstName = infoScanner.next();
        System.out.println("What is your surname?");
        secondName = infoScanner.next();
        System.out.println("Cool! Now what is your gender? Type m or f. If you are non binary" +
                ",\nthen please use another bank. Thank you :)");
        String myG = infoScanner.next().toLowerCase();
        if (myG.equals("m") || myG.equals("f")){
            gender = myG.charAt(0);
        }
        else {
            gender = askGenderAgain().charAt(0);
        }
        System.out.println("Thank you, "+firstName + " " + secondName + ", where do you live?");
        address = infoScanner.next();
        System.out.println("Great! Now please tell us your date of birth in the form yyyy-mm-dd");
        dateOfBirth = infoScanner.next();
        Date theDate = getDob(dateOfBirth);
        System.out.println("What is your phone number?");
        phone = infoScanner.next();
        phoneNumbers(phone);
        //Make sure it's an 11 digit integer
        System.out.println("And lastly, what is your email address?");
        email = verifyEmail(infoScanner.next());
        String createCustomer = "INSERT INTO customer(firstName, lastName, gender, dateOfBirth, address, email, phone) VALUES ('"+firstName+"','"+secondName+"','"+gender+"','"+dateOfBirth+"','"+address+"','"+email+"','"+phone+"');";
        DatabaseConnection.main("password", createCustomer, 1);
        //INSERT INTO SQL
        return;
    }
    public static String askGenderAgain(){
        System.out.println("Error. Please type m or f for your gender");
        Scanner scanner = new Scanner(System.in);
        String gender = scanner.next().toLowerCase();
        if (gender.equals("m") || gender.equals("f")){
            return gender;
        }
        else {
            return askGenderAgain();
        }
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    public static Date getDob(String date){
        Date theDate = new Date();
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            theDate = formatter.parse(date);
            return theDate;
        }   catch (Exception ex){
            System.out.println("Error. Please type your date of birth again in the format yyyy-mm-dd");
            Scanner scanner = new Scanner(System.in);
            String dateofBirth = scanner.next();
            getDob(dateofBirth);
        }
        return theDate;
    }
    public static boolean verifyPhoneNo(String number) {
        String regex = "\\d+";
        if (number.length() == 11) {
            if (number.matches(regex)){
                return true;
            }
        }
        return false;
    }
    public static void phoneNumbers(String number) {
        if (!verifyPhoneNo(number)){
            System.out.println("Error. Please enter a valid phone number");
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            phoneNumbers(string);
        }
    }
    public static String verifyEmail(String email){
        if (!(email.contains('@'+"") && email.contains('.'+""))){
            System.out.println("Error. Please enter a valid email address");
            Scanner scanner = new Scanner(System.in);
            String myEmail = scanner.next();
            verifyEmail(myEmail);
            return myEmail;
        }
        return email;
    }

}

