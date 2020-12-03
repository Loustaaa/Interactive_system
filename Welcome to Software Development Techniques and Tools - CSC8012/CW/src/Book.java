/*
 * A generic class which extends superclass ArrayList. SortedArrayList will hold any object which implements the comparable interface.
 * Code is influenced by examples in lecture materials.
 * - https://ncl.instructure.com/courses/24648/pages/lecture-notes?module_item_id=1212359
 * Original Author: Marta Koutny
 * Modifying Author: Louie Franchino
 * */
public class Book implements Comparable<Book> {

    private String title;
    private String authorName;
    private String authorSurname;
    private boolean loaned;

    // Creates Book objects
    public Book(String title, String authorName, String authorSurname, boolean loaned) {
        this.title = title;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.loaned = loaned;
    }

    public Book(String title, String authorName, String authorSurname) {
        this.title = title;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.loaned = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorFirstName) {
        this.authorName = authorFirstName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public boolean isLoaned() {
        return loaned;
    }

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
    }

    // Returns formatted string with book details.
    @Override
    public String toString() {
        return String.format("Book: %s \n" +
                "Author: %s %s \n" +
                "On loan: %b", title, authorName, authorSurname, loaned);
    }

    /*
    Used for book object comparison in order of importance:
       1. authorSurname
       2. title
       3. authorName
    */
    @Override
    public int compareTo(Book book) {
        int authorcmp = authorSurname.compareTo(book.getAuthorSurname());
        if (authorcmp != 0) {
            return authorcmp;
        }
        int titlecmp = title.compareTo(book.getTitle());
        if (titlecmp != 0) {
            return titlecmp;
        }
        return authorName.compareTo(book.getAuthorName());
    }
}
