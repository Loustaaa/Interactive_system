/*
 * Code is influenced by examples in lecture materials.
 * - https://ncl.instructure.com/courses/24648/pages/lecture-notes?module_item_id=1212359
 * Original Author: Marta Koutny
 * Modifying Author: Louie Franchino
 * */
public class User implements Comparable<User> {

    private String firstName;
    private String surname;
    private int booksHeld;

    // Creates user objects.
    public User(String firstName, String surname, int booksHeld) {
        this.firstName = firstName;
        this.surname = surname;
        this.booksHeld = booksHeld;
    }

    public User(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
        this.booksHeld = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBooksHeld() {
        return booksHeld;
    }

    public void setBooksHeld(int booksHeld) {
        this.booksHeld = booksHeld;
    }

    // Returns formatted string with user details.
    @Override
    public String toString() {
        return String.format("User name: %s %s \n" +
                "Books held: %d", firstName, surname, booksHeld);
    }

    /*
    Used for user object comparison in order of importance:
        1. surname
        2. firstName
     */
    @Override
    public int compareTo(User user) {
        int lncmp = surname.compareTo(user.getSurname());
        if (lncmp != 0) {
            return lncmp;
        }
        return firstName.compareTo(user.getFirstName());

    }
}
