public class User implements Comparable<User> {

    private String firstName;
    private String surname;
    private int booksHeld;

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

    @Override
    public String toString() {
        return String.format("User name: %s %s \n" +
                "Books held: %d", firstName, surname, booksHeld);
    }

    @Override
    public int compareTo(User user) {
        int lncmp = surname.compareTo(user.getSurname());
        if (lncmp != 0) {
            return lncmp;
        }
        int fncmp = firstName.compareTo(user.getFirstName());
        return fncmp;
    }
}
