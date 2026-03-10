import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String> bookList = new ArrayList<>();
    static ArrayList<String> memberList = new ArrayList<>();
    static ArrayList<String> borrowedBooks = new ArrayList<>();
    static ArrayList<String> borrowers = new ArrayList<>();
    static ArrayList<LocalDate> borrowDates = new ArrayList<>();

    static String borrowedUserName = "";
    static String borrowedBookTitle = "";
    static boolean hasBorrowed = false;
    static LocalDate savedDueDate = null;

    public static void main(String[] args) {

        bookList.add("Harry Potter");
        bookList.add("To Kill a Mockingbird");
        bookList.add("The Great Gatsby");
        bookList.add("1984");
        bookList.add("The Catcher in the Rye");
        bookList.add("The Hunter");
        bookList.add("The Bible");
        bookList.add("The Lord of the Rings");
        bookList.add("The Hobbit");
        bookList.add("The Da Vinci Code");


        memberList.add("un_suning");
        memberList.add("sok_ketoutdom");
        memberList.add("oeun_sopharoth");
        memberList.add("seng_mengkheang");
        memberList.add("chhorn_sovanda");

        login();

        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    bookManagement();
                    break;

                case 2:
                    memberManagement();
                    break;

                case 3:
                    borrowBook();
                    break;

                case 4:
                    returnBook();
                    break;

                case 5:
                    checkOverdue();
                    break;
                case 6:
                    viewBorrowRecords();
                    break;

                case 7:
                    System.out.println("Logged out.");
                    login();
                    break;

                case 8:
                    System.out.println("System closed.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        scanner.close();
    }

    // LOGIN SYSTEM
    static void login() {

        String correctUsername = "h";
        String correctPassword = "l";

        int attempts = 3;

        System.out.println("===== Library Management System =====");

        while (attempts > 0) {

            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (username.equals(correctUsername) && password.equals(correctPassword)) {

                System.out.println("Login Successful!");
                System.out.println("Welcome to the Library System.");
                return;

            } else {

                attempts--;
                System.out.println("Invalid login. Attempts left: " + attempts);
            }
        }

        System.out.println("Too many failed attempts. System locked.");
        System.exit(0);
    }

    // MAIN MENU
    static void showMenu() {

        System.out.println("\n===== DASHBOARD =====");
        System.out.println("1. Book Management");
        System.out.println("2. Member Management");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Overdue List");
        System.out.println("6. View Borrow Records");
        System.out.println("7. Log Out");
        System.out.println("8. Exit");

        System.out.print("Enter choice: ");
    }

    // BOOK MANAGEMENT
    static void bookManagement() {

        System.out.println("\n--- Book Management ---");
        System.out.println("1. Add Book");
        System.out.println("2. Update Book");
        System.out.println("3. Delete Book");
        System.out.println("4. Search Book");
        System.out.println("5. View All Books");
        System.out.print("Enter choice: ");
        int action = scanner.nextInt();
        scanner.nextLine();

        switch (action) {


            case 1:
                System.out.print("Enter book title: ");
                String newBook = scanner.nextLine();
                bookList.add(newBook);
                System.out.println("Book added.");
                break;

            case 2:

                if (bookList.isEmpty()) {
                    System.out.println("No books available.");
                    return;
                }

                System.out.println("\nCurrent Books:");
                for (int i = 0; i < bookList.size(); i++) {
                    System.out.println((i + 1) + ". " + bookList.get(i));
                }

                System.out.print("Select book number to update: ");
                int updateChoice = scanner.nextInt();
                scanner.nextLine();

                if (updateChoice < 1 || updateChoice > bookList.size()) {
                    System.out.println("Invalid selection.");
                    return;
                }

                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();

                bookList.set(updateChoice - 1, newTitle);

                System.out.println("Book updated.");
                break;

            case 3:

                if (bookList.isEmpty()) {
                    System.out.println("No books available.");
                    return;
                }

                System.out.println("\nCurrent Books:");
                for (int i = 0; i < bookList.size(); i++) {
                    System.out.println((i + 1) + ". " + bookList.get(i));
                }

                System.out.print("Select book number to delete: ");
                int deleteChoice = scanner.nextInt();
                scanner.nextLine();

                if (deleteChoice < 1 || deleteChoice > bookList.size()) {
                    System.out.println("Invalid selection.");
                    return;
                }

                String removedBook = bookList.remove(deleteChoice - 1);

                System.out.println("Book '" + removedBook + "' removed.");
                break;

            case 4:
                System.out.print("Search book: ");
                String searchBook = scanner.nextLine();

                if (bookList.contains(searchBook)) {
                    System.out.println("Book available.");
                } else {
                    System.out.println("Book not found.");
                }
            case 5:

                System.out.println("\n===== Book List =====");

                for (int i = 0; i < bookList.size(); i++) {

                    String book = bookList.get(i);
                    boolean isBorrowed = false;

                    for (int j = 0; j < borrowedBooks.size(); j++) {
                        if (borrowedBooks.get(j).equals(book)) {
                            System.out.println((i + 1) + ". " + book + " - Borrowed by " + borrowers.get(j));
                            isBorrowed = true;
                            break;
                        }
                    }

                    if (!isBorrowed) {
                        System.out.println((i + 1) + ". " + book + " - Available");
                    }
                }

                break;
        }
    }

    // MEMBER MANAGEMENT
    static void memberManagement() {

        System.out.println("\n--- Member Management ---");
        System.out.println("1. Add Member");
        System.out.println("2. Update Member");
        System.out.println("3. Delete Member");
        System.out.println("4. Search Member");
        System.out.println("5. View All Members");

        int action = scanner.nextInt();
        scanner.nextLine();

        switch (action) {

            case 1:
                System.out.print("Enter member name: ");
                String newMember = scanner.nextLine();

                memberList.add(newMember);
                System.out.println("Member added.");
                break;

            case 2:

                System.out.println("\nMembers:");
                for (int i = 0; i < memberList.size(); i++) {
                    System.out.println((i + 1) + ". " + memberList.get(i));
                }

                System.out.print("Select member number to update: ");
                int memberUpdate = scanner.nextInt();
                scanner.nextLine();

                if (memberUpdate < 1 || memberUpdate > memberList.size()) {
                    System.out.println("Invalid selection.");
                    return;
                }

                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();

                memberList.set(memberUpdate - 1, newName);

                System.out.println("Member updated.");
                break;

            case 3:

                System.out.println("\nMembers:");
                for (int i = 0; i < memberList.size(); i++) {
                    System.out.println((i + 1) + ". " + memberList.get(i));
                }

                System.out.print("Select member number to delete: ");
                int memberDelete = scanner.nextInt();
                scanner.nextLine();

                if (memberDelete < 1 || memberDelete > memberList.size()) {
                    System.out.println("Invalid selection.");
                    return;
                }

                String removedMember = memberList.remove(memberDelete - 1);

                System.out.println("Member '" + removedMember + "' deleted.");
                break;
            case 4:
                System.out.print("Search member: ");
                String searchMember = scanner.nextLine();

                if (memberList.contains(searchMember)) {
                    System.out.println("Member exists.");
                } else {
                    System.out.println("Member not found.");
                }
            case 5:

                System.out.println("\n===== Member List =====");

                for (int i = 0; i < memberList.size(); i++) {

                    String member = memberList.get(i);
                    boolean borrowing = false;

                    for (int j = 0; j < borrowers.size(); j++) {

                        if (borrowers.get(j).equals(member)) {
                            System.out.println((i + 1) + ". " + member + " - Borrowing: " + borrowedBooks.get(j));
                            borrowing = true;
                            break;
                        }
                    }

                    if (!borrowing) {
                        System.out.println((i + 1) + ". " + member + " - No borrowed book");
                    }
                }

                break;
        }
    }

    // BORROW BOOK
    static void borrowBook() {

        System.out.print("Enter user name: ");
        String user = scanner.nextLine();

        if (!memberList.contains(user)) {
            System.out.println("Member not registered.");
            return;
        }

        if (bookList.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nAvailable Books:");


        for (int i = 0; i < bookList.size(); i++) {
            System.out.println((i + 1) + ". " + bookList.get(i));
        }

        System.out.print("Select book number: ");
        int bookChoice = scanner.nextInt();
        scanner.nextLine();

        if (bookChoice < 1 || bookChoice > bookList.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String book = bookList.get(bookChoice - 1);

        borrowedUserName = user;
        borrowedBookTitle = book;
        hasBorrowed = true;

        borrowers.add(user);
        borrowedBooks.add(book);
        borrowDates.add(LocalDate.now());

        savedDueDate = LocalDate.now().plusDays(14);

        bookList.remove(book);

        System.out.println("\nBorrow successful.");
        System.out.println("Book: " + book);
        System.out.println("Borrower: " + user);
        System.out.println("Due date: " + savedDueDate);
    }
    // RETURN BOOK
    static void returnBook() {

        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        if (!hasBorrowed || !name.equalsIgnoreCase(borrowedUserName)) {

            System.out.println("No record found.");

        } else {

            LocalDate today = LocalDate.now();

            if (today.isAfter(savedDueDate)) {

                long daysLate = java.time.temporal.ChronoUnit.DAYS.between(savedDueDate, today);

                System.out.println("Overdue!");
                System.out.println("Days late: " + daysLate);

            } else {

                System.out.println("Returned successfully.");
            }

            bookList.add(borrowedBookTitle);

            hasBorrowed = false;
            borrowedUserName = "";
            borrowedBookTitle = "";
            savedDueDate = null;
        }
    }

    // OVERDUE CHECK
    static void checkOverdue() {

        if (!hasBorrowed) {

            System.out.println("No active loans.");
            return;
        }

        LocalDate today = LocalDate.now();

        if (today.isAfter(savedDueDate)) {

            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(savedDueDate, today);

            System.out.println("Overdue record:");
            System.out.println("User: " + borrowedUserName);
            System.out.println("Book: " + borrowedBookTitle);
            System.out.println("Days overdue: " + daysLate);

        } else {

            System.out.println("No overdue loans.");
        }
    }
    static void viewBorrowRecords() {

        System.out.println("\n===== Borrow Records =====");

        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrow records.");
            return;
        }

        for (int i = 0; i < borrowedBooks.size(); i++) {

            System.out.println(
                    (i + 1) + ". Book: " + borrowedBooks.get(i) +
                            " | Member: " + borrowers.get(i) +
                            " | Borrow Date: " + borrowDates.get(i)
            );
        }
    }
}
