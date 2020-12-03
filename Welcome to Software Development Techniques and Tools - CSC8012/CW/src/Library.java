/*
 * Code is influenced by examples in lecture materials.
 * - https://ncl.instructure.com/courses/24648/pages/lecture-notes?module_item_id=1212359
 * Original Author: Marta Koutny
 * Modifying Author: Louie Franchino
 * */

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    SortedArrayList<User> userList;
    SortedArrayList<Book> bookList;
    ArrayList<Borrows> borrowList;
    static Scanner inFile;
    static PrintWriter outfile;

    public Library(String inPathName, String outPathName) throws Exception {
        this.userList = new SortedArrayList<>();
        this.bookList = new SortedArrayList<>();
        this.borrowList = new ArrayList();
        this.inFile = new Scanner(new FileReader(inPathName));
        this.outfile = new PrintWriter(outPathName);
    }

    //Parses input file for user and book data. Adds all data to relevant lists.
    public void readFile() {
        ArrayList stringList = new ArrayList();
        while (inFile.hasNextLine()) {
            stringList.add(inFile.nextLine());
        }

        int userListStart = 0;
        String title = "";
        String name = "";
        String surname = "";
        for (int i = 1; i < stringList.size(); i++) {
            String tmp = (String) stringList.get(i);
            if (tmp.matches(".*\\d.*")) {
                userListStart = i + 1;
                break;
            }

            if ((i % 2) != 0) {
                title = tmp;
            } else {
                String[] nameArray = tmp.split("\\s+");
                if (nameArray.length > 2) {
                    for (int j = 0; j < nameArray.length - 1; j++) {
                        name = name + nameArray[j] + " ";
                    }
                    name = name.trim();
                    surname = nameArray[nameArray.length - 1];
                } else {
                    name = nameArray[0];
                    surname = nameArray[1];
                }
            }
            if ((i % 2) == 0) {
                Book book = new Book(title, name, surname);
                bookList.insert(book);
                title = "";
                name = "";
                surname = "";
            }
        }

        for (int i = userListStart; i < stringList.size(); i++) {
            String tmp = (String) stringList.get(i);
            String[] nameArray = tmp.split("\\s+");
            User user = new User(nameArray[0], nameArray[1]);
            userList.insert(user);

        }
    }

    // Prints reminder to txt file. Notifying user to return book.
    public void notifyUser(Book book) {
        for (Borrows b : borrowList) {
            if (b.getBook().compareTo(book) == 0) {
                User user = b.getUser();
                String receipt = String.format("Dear %s %s \n" +
                        "Your copy of %s by %s %s has been requested by another library member. \n" +
                        "Please could you return it as soon as possible. \n \n" +
                        "Regards, \n" +
                        "Fake Town Library \n \n", user.getFirstName(), user.getSurname(), book.getTitle(), book.getAuthorName(), book.getAuthorSurname());
                outfile.write(receipt);
            }
        }
    }

    // Returns a String array containing the contents of user list for printing in IO class.
    public String[] printUsers() {
        String[] tmp = new String[userList.size() + 1];
        tmp[0] = String.format("%d User(s) Found.\n \n", userList.size());
        for (int i = 0; i < userList.size(); i++) {
            tmp[i + 1] = userList.get(i).toString() + "\n \n";
        }
        return tmp;
    }

    // Returns a String array containing the contents of book list for printing in IO class.
    public String[] printBooks() {
        String[] tmp = new String[bookList.size() + 1];
        tmp[0] = String.format("%d Book(s) Found.\n \n", bookList.size());
        for (int i = 0; i < bookList.size(); i++) {
            tmp[i + 1] = bookList.get(i).toString() + "\n \n";
        }
        return tmp;
    }

    // Checks that user exists in user list.
    public boolean isValidUser(User user) {
        for (Object u : userList) {
            User person = (User) u;
            if (person.compareTo(user) == 0) {
                return true;
            }
        }
        return false;
    }

    // Checks that book exists in book list.
    public boolean isValidBook(Book book) {
        for (Object b : bookList) {
            Book text = (Book) b;
            if (text.compareTo(book) == 0) {
                return true;
            }
        }
        return false;
    }

    // Checks that book has not been loaned.
    public boolean available(Book book) {
        for (Object b : bookList) {
            Book text = (Book) b;
            if (text.compareTo(book) == 0) {
                return text.isLoaned();
            }
        }
        return false;
    }

    // Checks whether a user has already reached their max number of books or not. If not, increments user's booksHeld value by one.
    public boolean bookSpace(User user) {
        for (int i = 0; i < userList.size(); i++) {
            User person = (User) userList.get(i);
            if (person.compareTo(user) == 0) {
                if (user.getBooksHeld() == 3) {
                    return false;
                } else {
                    int amount = user.getBooksHeld() + 1;
                    user.setBooksHeld(amount);
                    return true;
                }
            }
        }
        return true;
    }

    // Checks whether a user has books held. Decrements by one if user has any books.
    public boolean minusBookSpace(User user) {
        for (int i = 0; i < userList.size(); i++) {
            User person = (User) userList.get(i);
            if (person.compareTo(user) == 0) {
                if (user.getBooksHeld() == 0) {
                    return false;
                } else {
                    int amount = user.getBooksHeld() - 1;
                    user.setBooksHeld(amount);
                    return true;
                }
            }
        }
        return true;
    }

    // Updates books isLoaned field to show book is loaned. Adds book and user to borrowlist to show ownership.
    public void loanBook(Book book, User user) {
        for (Object b : bookList) {
            Book text = (Book) b;
            if (text.compareTo(book) == 0) {
                text.setLoaned(true);
                Borrows borrows = new Borrows(book, user);
                borrowList.add(borrows);
                return;
            }
        }
    }

    // Updates books isLoaned field to show book has been returned. Calls unborrow function to remove items from borrowlist.
    public void updateRecords(Book book, User user) {
        for (Object b : bookList) {
            Book text = (Book) b;
            if (text.compareTo(book) == 0) {
                text.setLoaned(false);
                unborrow(user, book);
                return;
            }
        }
    }

    // Finds borrows object in borrowlist and removes it.
    private void unborrow(User user, Book book) {
        for (Borrows borrows : borrowList) {
            Book borrowBook = borrows.getBook();
            User borrowUser = borrows.getUser();
            if ((borrowBook.compareTo(book) == 0) && (borrowUser.compareTo(user) == 0)) {
                borrowList.remove(borrows);
                return;
            }
        }
    }

    // Looks for existing user in user list and returns it if found. Returns new user object if not found.
    public User findUser(String fName, String sName) {
        for (Object u : userList) {
            User user = (User) u;
            String firstName = user.getFirstName();
            String surname = user.getSurname();
            if ((fName.matches(firstName)) && (sName.matches(surname))) {
                return user;
            }
        }
        return new User(fName, sName);
    }

    // Closes filewriter object.
    public void close() {
        outfile.close();
    }
}
