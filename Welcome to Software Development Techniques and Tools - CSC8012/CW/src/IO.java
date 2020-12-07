/*
 * Code is influenced by examples in lecture materials.
 * - https://ncl.instructure.com/courses/24648/pages/lecture-notes?module_item_id=1212359
 * Original Author: Marta Koutny
 * Modifying Author: Louie Franchino
 * */

import java.util.Scanner;

public class IO {

    final private static Library l = new Library();
    static Scanner sc;

    public IO() {
        this.sc = new Scanner(System.in);
    }

    // Program starts here.
    public static void main(String[] args) throws Exception {
        IO io = new IO();
        l.readFile();

        char input = 'a';
        while (input != 'f') {
            menu();
            input = sc.next().charAt(0);
            sc.nextLine();

            switch (input) {
                case 'f':
                    System.out.println("Quitting...");
                    l.close();
                    break;
                case 'b':
                    printer(l.printBooks());
                    Thread.sleep(2000);
                    break;
                case 'u':
                    printer(l.printUsers());
                    Thread.sleep(2000);
                    break;
                case 'i':
                    issueBook();
                    Thread.sleep(2000);
                    break;
                case 'r':
                    returnBook();
                    Thread.sleep(2000);
                    break;
                default:
                    System.out.println("Invalid entry, try again.");
                    Thread.sleep(1000);
            }

        }

    }

    // Used in main class for displaying menu to user.
    private static void menu() {
        System.out.println("------------------------------");
        System.out.println("MENU");
        System.out.println("f - Finish");
        System.out.println("b - Display books held");
        System.out.println("u - Display user information");
        System.out.println("i - Issue book");
        System.out.println("r - Return a book");
        System.out.println("------------------------------");
    }

    // Used for issuing books to members.
    private static void issueBook() {
        Book book = null;
        int attempts = 3;
        while (attempts != 0) {
            try {
                System.out.println("Please enter the book's title: ");
                String title = capitalize(sc.nextLine());
                System.out.println("Please enter author's surname: ");
                String surname = capitalize(sc.nextLine());
                System.out.println("Please enter author's first name and/or initials: ");
                String firstName = capitalize(sc.nextLine());
                isEmpty(title);
                isEmpty(firstName);
                isEmpty(surname);
                book = l.findBook(title, firstName, surname);
                if (!l.isValidBook(book)) {
                    System.out.println("Invalid book.");
                    attempts--;
                    continue;
                } else if (l.available(book)) {
                    System.out.println("Book already loaned.");
                    l.notifyUser(book);
                    return;
                } else {
                    break;
                }
            } catch (EmptyInputException e) {
                System.out.println("Please do not leave any fields empty.");
                attempts--;
                continue;
            }
        }
        if (attempts == 0) {
            return;
        }

        User user = null;
        attempts = 3;
        while (attempts != 0) {
            try {
                System.out.println("Please enter member's first name: ");
                String firstName = capitalize(sc.nextLine());
                System.out.println("Please enter member's surname: ");
                String surname = capitalize(sc.nextLine());
                isEmpty(firstName);
                isEmpty(surname);
                user = l.findUser(firstName, surname);
                if (!l.isValidUser(user)) {
                    System.out.println("Not a member.");
                    attempts--;
                    continue;
                } else if (!l.bookSpace(user)) {
                    System.out.println("This member has reached the maximum limit of books.");
                    return;
                } else {
                    break;
                }
            } catch (EmptyInputException e) {
                System.out.println("Please do not leave any fields empty.");
                attempts--;
                continue;
            }
        }
        if (attempts == 0) {
            return;
        }

        l.loanBook(book, user);
        System.out.println("Book Issued.");
    }

    // Used for returning books from members to the library.
    private static void returnBook() {
        Book book = null;
        int attempts = 3;
        while (attempts != 0) {
            try {
                System.out.println("Please enter the book's title: ");
                String title = capitalize(sc.nextLine());
                System.out.println("Please enter author's surname: ");
                String surname = capitalize(sc.nextLine());
                System.out.println("Please enter author's first name and/or initials: ");
                String firstName = capitalize(sc.nextLine());
                isEmpty(title);
                isEmpty(surname);
                isEmpty(firstName);
                book = l.findBook(title, firstName, surname);
                if (!l.isValidBook(book)) {
                    System.out.println("Invalid book.");
                    attempts--;
                    continue;
                } else if (!l.available(book)) {
                    System.out.println("Cannot return a book that isn't loaned.");
                    return;
                } else {
                    break;
                }
            } catch (EmptyInputException e) {
                System.out.println("Please do not leave any fields empty.");
                attempts--;
                continue;
            }
        }
        if (attempts == 0) {
            return;
        }

        User user = null;
        attempts = 3;
        while (attempts != 0) {
            try {
                System.out.println("Please enter member's first name: ");
                String firstName = capitalize(sc.nextLine());
                System.out.println("Please enter member's surname: ");
                String surname = capitalize(sc.nextLine());
                isEmpty(firstName);
                isEmpty(surname);
                user = l.findUser(firstName, surname);
                if (!l.isValidUser(user)) {
                    System.out.println("Not a member.");
                    attempts--;
                    continue;
                } else if (!l.isBorrowing(book, user)) {
                    System.out.println("User is not borrowing this book");
                    attempts--;
                    continue;
                } else if (!l.minusBookSpace(user)) {
                    System.out.println("User is holding no books.");
                    return;
                } else {
                    break;
                }
            } catch (EmptyInputException e) {
                System.out.println("Please do not leave any fields empty.");
                attempts--;
                continue;
            }
        }
        if (attempts == 0) {
            return;
        }

        l.updateRecords(book, user);
        System.out.println("Book returned");
    }

    // Used for printing out userlist and booklist incrementally.
    private static void printer(String[] printList) throws Exception {
        System.out.println(printList[0]);
        Thread.sleep(2000);
        for (int i = 1; i < printList.length; i++) {
            System.out.println(printList[i]);
        }
    }

    // Capitalizes the first letter of each word and removes any spaces from either side of input.
    private static String capitalize(String string) {
        String tmp = "";
        if (string.length() == 0) {
            return tmp;
        }
        String[] words = string.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            tmp += words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase() + " ";
        }
        return tmp.trim();
    }

    // Makes sure user does not input an empty string.
    private static void isEmpty(String input) throws EmptyInputException {
        if (input == "") {
            throw new EmptyInputException();
        }
    }

}
