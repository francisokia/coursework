public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    // Constructor 1
    public Book(String isbn, String title) {
        this(isbn, title, "Unknown Author");
    }

    // Constructor 2
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true; // Default status is available
    }

    // Getters and Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Book [ISBN=" + isbn + ", Title=\"" + title + "\", Author=\"" + author + "\", Status=" + (available ? "Available" : "On Loan") + "]";
    }
}