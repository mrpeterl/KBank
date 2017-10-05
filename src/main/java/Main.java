import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        menu();
    }

    public static void menu(){
        System.out.println("Welcome to KBank!");
        String mainMessage = String.format("Please enter the number corresponding to your desired action"
        +"\n1.I would like to create a new account"
        +"\n2.I would like to access my account"
        +"\n3.Exit");
        System.out.println(mainMessage);
        Scanner menuScanner = new Scanner(System.in);
        String selection = menuScanner.next();
        switch (selection){
            case "1":
                //Create account
                break;
            case "2":
                //Access account via login
                break;
            case "3":
                //Exit application
                break;
            default:
                System.out.println("Error. Please enter a number between 1 and 3");
                menu();//Show menu again if invalid input
        }
    }
}

