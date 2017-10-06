
import java.util.Scanner;


public class Main {

    public static void main(String[] args)
    {
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
                    searchById();
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
        firstName = infoScanner.nextLine();
        System.out.println("What is your surname?");
        secondName = infoScanner.nextLine();
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

    private static void searchById(){
        Scanner idScanner = new Scanner(System.in);
        System.out.println("Please enter your account id");
        int id = idScanner.nextInt();
        DatabaseConnection.main("password", "SELECT * FROM account  " +
                "JOIN customer ON account.customerID = customer.id " +
                "WHERE account.accountID = "+id+";", 2);
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

}

