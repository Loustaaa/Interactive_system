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
