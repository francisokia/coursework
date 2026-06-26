public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();

        // 1. Create 3 Books (Using overloaded constructors)
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
        Book book2 = new Book("978-0132350884", "Clean Code", "Robert C. Martin");
        Book book3 = new Book("978-0596009205", "Head First Java"); // Uses default author fallback

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // 2. Create 2 Members
        Member member1 = new Member("M001", "Alice Smith");
        Member member2 = new Member("M002", "Bob Jones");

        library.registerMember(member1);
        library.registerMember(member2);

        // 3. Print state before any operations
        System.out.println("--- INITIAL STATE ---");
        library.displayState();

        // 4. Perform sequence of loan operations
        System.out.println("--- PERFORM TRANSACTIONS ---");
        // Transaction A: Successful Loan
        library.lendBook("M001", "978-0134685991"); 
        
        // Transaction B: Member holds multiple loans at once (Valid Rule)
        library.lendBook("M001", "978-0132350884"); 

        // Transaction C: Rejected Loan (Attempting to rent an already-loaned book)
        library.lendBook("M002", "978-0134685991"); 

        // 5. Print intermediate state showing active loans
        System.out.println("\n--- STATE AFTER LOANS ---");
        library.displayState();

        // 6. Return operation
        System.out.println("--- PERFORM RETURN ---");
        library.returnBook("978-0134685991");

        // 7. Print ultimate library state
        System.out.println("\n--- FINAL STATE ---");
        library.displayState();
    }
}
