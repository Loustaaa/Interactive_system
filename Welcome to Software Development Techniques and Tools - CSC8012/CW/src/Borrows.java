/*
A class I made to represent relationship between users and books. One borrows object represent a user having loaned this book.
 */
public class Borrows {

    private Book book;
    private User user;


    public Borrows(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

}
