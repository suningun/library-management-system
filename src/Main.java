import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String> bookList = new ArrayList<>();
    static ArrayList<String> memberList = new ArrayList<>();

    static String borrowedUserName = "";
    static String borrowedBookTitle = "";
    static boolean hasBorrowed = false;
    static LocalDate savedDueDate = null;

    public static void main(String[] args) {

        bookList.add("Java Basics");
        bookList.add("OOP in Java");

        memberList.add("John Doe");
        memberList.add("Jane Smith");

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
                    System.out.println("Logged out.");
                    break;

                case 7:
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

        String correctUsername = "aupp_librarian";
        String correctPassword = "lib@#@!123";

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
        System.out.println("6. Logout");
        System.out.println("7. Exit");

        System.out.print("Enter choice: ");
    }

    // BOOK MANAGEMENT
    static void bookManagement() {

        System.out.println("\n--- Book Management ---");
        System.out.println("1 Add Book");
        System.out.println("2 Update Book");
        System.out.println("3 Delete Book");
        System.out.println("4 Search Book");

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
                System.out.print("Enter book to update: ");
                String oldBook = scanner.nextLine();

                if (bookList.contains(oldBook)) {

                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();

                    int index = bookList.indexOf(oldBook);
                    bookList.set(index, newTitle);

                    System.out.println("Book updated.");

                } else {
                    System.out.println("Book not found.");
                }

                break;

            case 3:
                System.out.print("Enter book to delete: ");
                String deleteBook = scanner.nextLine();

                if (bookList.remove(deleteBook)) {
                    System.out.println("Book removed.");
                } else {
                    System.out.println("Book not found.");
                }

                break;

            case 4:
                System.out.print("Search book: ");
                String searchBook = scanner.nextLine();

                if (bookList.contains(searchBook)) {
                    System.out.println("Book available.");
                } else {
                    System.out.println("Book not found.");
                }
        }
    }

    // MEMBER MANAGEMENT
    static void memberManagement() {

        System.out.println("\n--- Member Management ---");
        System.out.println("1 Add Member");
        System.out.println("2 Update Member");
        System.out.println("3 Delete Member");
        System.out.println("4 Search Member");

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
                System.out.print("Enter member to update: ");
                String oldMember = scanner.nextLine();

                if (memberList.contains(oldMember)) {

                    System.out.print("Enter new name: ");
                    String updated = scanner.nextLine();

                    int index = memberList.indexOf(oldMember);
                    memberList.set(index, updated);

                    System.out.println("Member updated.");

                } else {
                    System.out.println("Member not found.");
                }

                break;

            case 3:
                System.out.print("Enter member to delete: ");
                String deleteMember = scanner.nextLine();

                if (memberList.remove(deleteMember)) {
                    System.out.println("Member deleted.");
                } else {
                    System.out.println("Member not found.");
                }

                break;

            case 4:
                System.out.print("Search member: ");
                String searchMember = scanner.nextLine();

                if (memberList.contains(searchMember)) {
                    System.out.println("Member exists.");
                } else {
                    System.out.println("Member not found.");
                }
        }
    }

    // BORROW BOOK
    static void borrowBook() {

        System.out.print("Enter user name: ");
        String user = scanner.nextLine();

        System.out.print("Enter book title: ");
        String book = scanner.nextLine();

        if (memberList.contains(user) && bookList.contains(book)) {

            borrowedUserName = user;
            borrowedBookTitle = book;
            hasBorrowed = true;

            LocalDate borrowDate = LocalDate.now();
            savedDueDate = borrowDate.plusDays(14);

            bookList.remove(book);

            System.out.println("Borrow successful.");
            System.out.println("Due date: " + savedDueDate);

        } else {

            System.out.println("Borrow failed.");

            if (!memberList.contains(user))
                System.out.println("Member not registered.");

            if (!bookList.contains(book))
                System.out.println("Book not available.");
        }
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
}