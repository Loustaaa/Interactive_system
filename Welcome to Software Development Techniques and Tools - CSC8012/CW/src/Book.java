public class Book implements Comparable<Book> {

    private String title;
    private String authorName;
    private String authorSurname;
    private boolean loaned;

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

    @Override
    public String toString() {
        return String.format("Book: %s \n" +
                "Author: %s %s \n" +
                "On loan: %b", title, authorName, authorSurname, loaned);
    }

    @Override
    public int compareTo(Book book) {
        int authorcmp = authorSurname.compareTo(book.getAuthorSurname());
        if (authorcmp != 0) {
            return authorcmp;
        }
        ;
        return title.compareTo(book.getTitle());
    }
}
