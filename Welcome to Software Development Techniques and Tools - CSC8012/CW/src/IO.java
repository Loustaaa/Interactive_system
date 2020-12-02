import java.util.Scanner;

public class IO {

    private static Library l;
    static Scanner sc;

    public IO() {
        try {
            this.l = new Library("src/Library data.txt", "src/printedData.txt");
        } catch (Exception e) {
            System.out.println("Library data file cannot be found.");
        }
        this.sc = new Scanner(System.in);

    }

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
            }

        }

    }

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

    private static void issueBook() {
        Book book = null;
        int attempts = 3;
        while (attempts != 0) {
            System.out.println("Please enter the book's title: ");
            String title = capitalize(sc.nextLine());
            System.out.println("Please enter author's surname: ");
            String surname = capitalize(sc.nextLine());
            System.out.println("Please enter author's first name and/or initials: ");
            String firstName = capitalize(sc.nextLine());
            book = new Book(title, firstName, surname);
            if (!l.isValidBook(book)) {
                System.out.println("Invalid book.");
                attempts--;
                continue;
            } else if (l.available(book)) {
                System.out.println("Book already loaned");
                l.notifyUser(book);
                return;
            } else {
                break;
            }
        }
        if (attempts == 0) {
            return;
        }

        User user = null;
        attempts = 3;
        while (attempts != 0) {
            System.out.println("Please enter member's first name: ");
            String firstName = capitalize(sc.nextLine());
            System.out.println("Please enter member's surname: ");
            String surname = capitalize(sc.nextLine());
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
        }
        if (attempts == 0) {
            return;
        }

        l.loanBook(book, user);
        System.out.println("Book Issued.");
    }


    private static void returnBook() {
        Book book = null;
        int attempts = 3;
        while (attempts != 0) {
            System.out.println("Please enter the book's title: ");
            String title = capitalize(sc.nextLine());
            System.out.println("Please enter author's surname: ");
            String surname = capitalize(sc.nextLine());
            System.out.println("Please enter author's first name and/or initials: ");
            String firstName = capitalize(sc.nextLine());
            book = new Book(title, firstName, surname);
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
        }
        if (attempts == 0) {
            return;
        }

        User user = null;
        attempts = 3;
        while (attempts != 0) {
            System.out.println("Please enter member's first name: ");
            String firstName = capitalize(sc.nextLine());
            System.out.println("Please enter member's surname: ");
            String surname = capitalize(sc.nextLine());
            user = l.findUser(firstName, surname);
            if (!l.isValidUser(user)) {
                System.out.println("Not a member.");
                attempts--;
                continue;
            } else if (!l.minusBookSpace(user)) {
                System.out.println("User is holding no books.");
                return;
            } else {
                break;
            }
        }
        if (attempts == 0) {
            return;
        }

        l.updateRecords(book, user);
        System.out.println("Book returned");
    }

    private static void printer(String[] printList) throws Exception {
        System.out.println(printList[0]);
        Thread.sleep(2000);
        for (int i = 1; i < printList.length; i++) {
            System.out.println(printList[i]);
        }
    }

    private static String capitalize(String string) {
        String tmp = "";
        if (string.length() == 0){
            return tmp;
        }
        String[] words = string.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            tmp += words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase() + " ";
        }
        return tmp.trim();
    }

}
