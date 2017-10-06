
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int selection;
        System.out.println("Welcome to KBank!");
        do {
            String mainMessage = String.format("Please enter the number corresponding to your desired action"
                    + "\n1.I would like to create a new account"
                    + "\n2.I would like to access my account"
                    + "\n3.I would like to take out an overdraft"
                    + "\n4.Exit");
            System.out.println(mainMessage);
            Scanner menuScanner = new Scanner(System.in);

            selection = menuScanner.nextInt();
            switch (selection) {
                case 1:
                    createAccount();

                    break;
                case 2:
                    searchById();
                    break;

                case 3:
                    Overdraft();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error. Please enter a number between 1 and 3");
                    menu();//Show menu again if invalid input
            }
        } while (selection != 3);

    }

    static void createAccount() {
        String firstName, secondName, dateOfBirth, address, email, phone;
        char gender = ' ';
        Scanner infoScanner = new Scanner(System.in);
        System.out.println("\nThank you for deciding to create an account with KBank!" +
                "\nWhat is your first name?");
        firstName = infoScanner.nextLine();
        System.out.println("What is your surname?");
        secondName = infoScanner.nextLine();
        System.out.println("Cool! Now what is your gender? Type m or f. If you are non binary" +
                ",\nthen please use another bank. Thank you :)");
        String myG = infoScanner.next().toLowerCase();
        if (myG.equals("m") || myG.equals("f")) {
            gender = myG.charAt(0);
        } else {
            gender = askGenderAgain().charAt(0);
        }
        System.out.println("Thank you, " + firstName + " " + secondName + ", where do you live?");
        address = infoScanner.next();
        System.out.println("Great! Now please tell us what year you were born in in the form yyyy-mm-dd");
        String year = infoScanner.next();
        String day = "";
        String month = "";
        if (validateYear(year)){
            System.out.println("Great! Now please tell us what month you were born in");
            month = infoScanner.next();
            if (validateMonth(month)){
                System.out.println("Great! Now please tell us what day you were born in");
                day = infoScanner.next();
                if (validateDay(day, Integer.parseInt(month),Integer.parseInt(year))){
                    //Do nothing, this means all the values are valid
                }
                else {
                    System.out.println("Error. Please input a valid year");
                    year = infoScanner.next();
                    recursiveYearFunction(year,infoScanner);
                }
            }
            else {
                System.out.println("Error. Please input a valid year");
                year = infoScanner.next();
                recursiveYearFunction(year,infoScanner);
            }
        }
        else {
            System.out.println("Error. Please input a valid year");
            year = infoScanner.next();
            recursiveYearFunction(year,infoScanner);
        }
        String date = year+"-"+month+"-"+day;
        System.out.println("What is your phone number?");
        phone = infoScanner.next();
        phoneNumbers(phone);
        //Make sure it's an 11 digit integer
        System.out.println("And lastly, what is your email address?");
        email = verifyEmail(infoScanner.next());
        String createCustomer = "INSERT INTO customer(firstName, lastName, gender, dateOfBirth, address, email, phone) VALUES ('" + firstName + "','" + secondName + "','" + gender + "','" + date + "','" + address + "','" + email + "','" + phone + "');";
        DatabaseConnection.main("password", createCustomer, 1);
        //INSERT INTO SQL
    }
    private static void searchById(){
        Scanner idScanner = new Scanner(System.in);
        System.out.println("Please enter your account id");
        int id = idScanner.nextInt();
        DatabaseConnection.main("password", "SELECT * FROM account  " +
                "JOIN customer ON account.customerID = customer.id " +
                "WHERE account.accountID = "+id+";", 2);
    }
    public static String askGenderAgain() {
        System.out.println("Error. Please type m or f for your gender");
        Scanner scanner = new Scanner(System.in);
        String gender = scanner.next().toLowerCase();
        if (gender.equals("m") || gender.equals("f")) {
            return gender;
        } else {
            return askGenderAgain();
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    public static boolean validateYear(String year) {
        if (isInteger(year)){
            if (Integer.parseInt(year) >1900 && Integer.parseInt(year) < 2000){
                return true;
            }
        }
        return false;
    }

    public static boolean validateMonth(String month) {
        if (isInteger(month)){
            if (Integer.parseInt(month) <13 && Integer.parseInt(month) > 0){
                return true;
            }
        }
        return false;
    }

    public static boolean validateDay(String day, int month, int year){
        if (isInteger(day)){
            if ((Integer.parseInt(day) <32 && Integer.parseInt(day) > 0) && (!(month == 9 || month == 11 || month ==4 || month == 6))){
                return true;
            }
            else if ((Integer.parseInt(day) <31 && Integer.parseInt(day) > 0) && ((month == 9 || month == 11 || month ==4 || month == 6))){
                return true;
            }
            else if ((Integer.parseInt(day) <30 && Integer.parseInt(day) > 0) && ((month == 2 && (year%4==0)))){
                return true;
            }
            else if ((Integer.parseInt(day) <29 && Integer.parseInt(day) > 0) && ((month == 2))) {
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    public static boolean verifyPhoneNo(String number) {
        String regex = "\\d+";
        if (number.length() == 11) {
            if (number.matches(regex)) {
                return true;
            }
        }
        return false;
    }

    public static void phoneNumbers(String number) {
        if (!verifyPhoneNo(number)) {
            System.out.println("Error. Please enter a valid phone number");
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            phoneNumbers(string);
        }
    }

    public static String verifyEmail(String email) {
        if (!(email.contains('@' + "") && email.contains('.' + ""))) {
            System.out.println("Error. Please enter a valid email address");
            Scanner scanner = new Scanner(System.in);
            String myEmail = scanner.next();
            verifyEmail(myEmail);
            return myEmail;
        }
        return email;
    }
    public static void Overdraft(){
        Scanner overDraftScanner = new Scanner(System.in);
        System.out.println("Please enter the account id of the account you want to add overdraft to");
        int accountId = overDraftScanner.nextInt();
        System.out.println("Please enter the amount of overdraft you want, the maximum is £10,000");
        int overDraft = overDraftScanner.nextInt();
        if (overDraft > 10000) {
            Overdraft();
        } else {
            DatabaseConnection.main("password", "UPDATE account SET balance = balance + " +overDraft+" WHERE accountID = "+ accountId +" ", 3);
        }
    }
    public static void recursiveYearFunction(String year, Scanner infoScanner){
        if (validateYear(year)){
            System.out.println("Great! Now please tell us what month you were born in. Enter a number 1-12");
            String month = infoScanner.next();
            if (validateMonth(month)){
                System.out.println("Great! Now please tell us what day you were born in. Enter a number 1-31");
                String day = infoScanner.next();
                if (validateDay(day, Integer.parseInt(month),Integer.parseInt(year))){
                    //do nothing
                }
                else {
                    System.out.println("Error. Invalid date. Please input a valid year");
                    year = infoScanner.next();
                    recursiveYearFunction(year, infoScanner);
                }
            }
            else {
                System.out.println("Error. Invalid date. Please input a valid year");
                year = infoScanner.next();
                recursiveYearFunction(year,infoScanner);
            }
        }
        else {
            System.out.println("Error. Invalid date. Please input a valid year");
            year = infoScanner.next();
            recursiveYearFunction(year,infoScanner);
        }
    }
}

