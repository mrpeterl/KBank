import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Scanner;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Main {

    public static void main(String[] args)
    {
        menu();
    }

    public static void menu() {
        System.out.println("Welcome to KBank!");
        String mainMessage = String.format("Please enter the number corresponding to your desired action"
                + "\n1.I would like to create a new account"
                + "\n2.I would like to access my account"
                + "\n3.Exit");
        System.out.println(mainMessage);
        Scanner menuScanner = new Scanner(System.in);
        String selection = menuScanner.next();
        switch (selection) {
            case "1":
                createAccount();
                break;
            case "2":
                //Access account via login
                break;
            case "3":
                System.exit(0);
                break;
            default:
                System.out.println("Error. Please enter a number between 1 and 3");
                menu();//Show menu again if invalid input
        }
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
        if (infoScanner.next().toLowerCase().equals('m') || infoScanner.next().toLowerCase().equals('f')){
            gender = infoScanner.next().charAt(0);
        }
        else {
            gender = askGenderAgain().charAt(0);
        }
        System.out.println("Thank you, "+firstName + secondName + ", where do you live?");
        doAddress(infoScanner);
        System.out.println("Great! Now please tell us your date of birth in the form yyyy-mm-dd");
        dateOfBirth = infoScanner.next();
        Date theDate = getDob(dateOfBirth);
        System.out.println("What is your phone number?");
        phone = infoScanner.next();
        phoneNumbers(phone);
        //Make sure it's an 11 digit integer
        System.out.println("And lastly, what is your email address?");
        //Email verification
    }
    public static String askGenderAgain(){
        System.out.println("Error. Please type m or f for your gender");
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().toLowerCase().equals('m') || scanner.next().toLowerCase().equals('f')){
            return scanner.next();
        }
        else {
            askGenderAgain();
            return "";
        }
    }
    public static boolean checkAddress(String address){
        String[] check = new String[3];//Address should have 2 spaces and therefore 3 elements in an array split with " "
        check = address.split(" ");
        if (isInteger(check[0])){//First element should be int for address
            if (check[0].isEmpty() || check[1].isEmpty() || check[2].isEmpty()){
                return false;
            }
            else {
                return true;
            }
        }
        return false;
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
    public static void doAddress(Scanner scanner){
        String address = "";
        if (checkAddress(scanner.next())){
            address = scanner.next();
        }
        else {
            //ask address again
            System.out.println("Error. Please type in a valid address \"## road name\"");
            doAddress(scanner);
        }
    }
    public static Date getDob(String date){
        Date theDate = new Date();
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            theDate = formatter.parse(date);
            return theDate;
        } catch (Exception ex){
            System.out.println("Error. Please type your date of birth again");
            Scanner scanner = new Scanner(System.in);
            String dateofBirth = scanner.next();
            getDob(dateofBirth);
        }
        return theDate;
    }
    public static boolean verifyPhoneNo(String number) {
        if (number.length() != 11) {
            if (isInteger(number)){
                return true;
            }
        }
        return false;
    }
    public static void phoneNumbers(String number) {
        if (!verifyPhoneNo(number)){
            System.out.println("Error. Please enter a valid phone numeber");
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            phoneNumbers(string);
        }

    }
}

