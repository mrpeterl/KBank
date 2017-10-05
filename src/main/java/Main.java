import java.util.Scanner;

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
                //Create account
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
        System.out.println("Thank you, "+firstName + secondName + ", where do you live?");
        address = infoScanner.next();
        System.out.println("Great! Now please tell us your date of birth in the form yyyy-mm-dd");
        dateOfBirth = infoScanner.next();
        //Make sure a date passable is made
        System.out.println("What is your phone number?");
        phone = infoScanner.next();
        //Make sure it's an 11 digit integer
        System.out.println("And lastly, what is your email address?");
        //Email verification
        
    }

}

