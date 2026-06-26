import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private List<Loan> currentLoans;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.currentLoans = new ArrayList<>();
    }

    // Getters and Setters
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Loan> getCurrentLoans() { return currentLoans; }

    // Helper methods to manage loans
    public void addLoan(Loan loan) {
        this.currentLoans.add(loan);
    }

    public void removeLoan(Loan loan) {
        this.currentLoans.remove(loan);
    }

    @Override
    public String toString() {
        return "Member [ID=" + memberId + ", Name=\"" + name + "\", Active Loans Count=" + currentLoans.size() + "]";
    }
}
