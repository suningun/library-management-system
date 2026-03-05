import java.util.Scanner;
public class chooseAction {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Manage Books =====");
            System.out.println("1. Add Book");
            System.out.println("2. Edit Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. Update Books Table");
            System.out.println("6. Back to Dashboard");

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("Adding a new book...");
                    break;
                case 2:
                    System.out.println("Editing book information...");
                    break;
                case 3:
                    System.out.println("Deleting book...");
                    break;
                case 4:
                    System.out.println("Searching book...");
                    break;
                case 5:
                    System.out.println("Updating book...");
                    break;
                case 6:
                    System.out.println("Returning to dashboard...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
        scanner.close();
    }
}
