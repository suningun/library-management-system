import java.util.Scanner;
public class login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String correctUsername = "librarian";
        String correctPassword = "lib123";

        System.out.println("===== Library Management System =====");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if(username.equals(correctUsername) && password.equals(correctPassword)) {
            System.out.println("Login Successful!");
            System.out.println("Welcome to the Library System.");
        }
        else {
            System.out.println("Invalid Username or Password.");
        }

        scanner.close();

    }
}
