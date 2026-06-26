import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    // Implementation of Part (c) Business Logic
    public boolean lendBook(String memberId, String isbn) {
        Book targetBook = null;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                targetBook = b;
                break;
            }
        }

        Member targetMember = null;
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                targetMember = m;
                break;
            }
        }

        if (targetBook == null) {
            System.out.println("Transaction Failed: Book with ISBN " + isbn + " not found.");
            return false;
        }
        if (targetMember == null) {
            System.out.println("Transaction Failed: Member ID " + memberId + " not found.");
            return false;
        }

        // Business Rule: Enforce one active loan per book max
        if (!targetBook.isAvailable()) {
            System.out.println("Transaction Rejected: \"" + targetBook.getTitle() + "\" is already on loan!");
            return false;
        }

        // Issue loan
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14); // 2 weeks default duration
        Loan newLoan = new Loan(targetMember, targetBook, borrowDate, dueDate);

        targetBook.setAvailable(false);
        targetMember.addLoan(newLoan);
        System.out.println("Success: \"" + targetBook.getTitle() + "\" lent to " + targetMember.getName() + ".");
        return true;
    }

    public boolean returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable()) {
                    System.out.println("Return Failed: \"" + book.getTitle() + "\" is already marked inside the library.");
                    return false;
                }

                // Find and remove the active loan from the corresponding member
                for (Member member : members) {
                    for (Loan loan : member.getCurrentLoans()) {
                        if (loan.getBook().getIsbn().equals(isbn)) {
                            member.removeLoan(loan);
                            book.setAvailable(true);
                            System.out.println("Success: \"" + book.getTitle() + "\" successfully returned.");
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("Return Failed: Book with ISBN " + isbn + " does not exist.");
        return false;
    }

    public List<Book> searchBookByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public void displayState() {
        System.out.println("\n======= CURRENT LIBRARY STATE =======");
        System.out.println("--- Books Inventory ---");
        for (Book b : books) System.out.println("  " + b);
        System.out.println("--- Registered Members ---");
        for (Member m : members) System.out.println("  " + m);
        System.out.println("=====================================\n");
    }
