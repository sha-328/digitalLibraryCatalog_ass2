abstract class LibraryItem {
    // Encapsulation: Private attributes
    private String title;
    private String itemId;
    private boolean isAvailable;

    // Constructor
    public LibraryItem(String title, String itemId) {
        this.title = title;
        this.itemId = itemId;
        this.isAvailable = true;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public String getItemId() { return itemId; }
    public boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(boolean status) { this.isAvailable = status; }

    // Polymorphism: Abstract method to be overridden
    public abstract void showDetails();
}

class Book extends LibraryItem {
    private String author;

    public Book(String title, String itemId, String author) {
        super(title, itemId);
        this.author = author;
    }

    @Override
    public void showDetails() {
        System.out.println("[BOOK] " + getTitle() + " by " + author + " (ID: " + getItemId() + ")");
    }
}

class DVD extends LibraryItem {
    private int runtime;

    public DVD(String title, String itemId, int runtime) {
        super(title, itemId);
        this.runtime = runtime;
    }

    @Override
    public void showDetails() {
        System.out.println("[DVD] " + getTitle() + " - " + runtime + " mins (ID: " + getItemId() + ")");
    }
}

// Member 2
// Class 1: Manages User Data
// Member 2
// Class 1: Manages User Data
class Member {
    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void showMemberDetails() {
        System.out.println("Member ID: " + memberId + ", Name: " + name);
    }
}

// Class 2: Handles the logic of a "Loan"
class LoanRecord {
    private LibraryItem item;
    private Member member;
    private String loanDate;
    private String returnDate;

    public LoanRecord(LibraryItem item, Member member, String loanDate) {
        this.item = item;
        this.member = member;
        this.loanDate = loanDate;
        this.returnDate = "Not returned";
        
        item.setIsAvailable(false); // When loaned, item becomes unavailable
    }

    public void returnItem(String returnDate) {
        this.returnDate = returnDate;
        item.setIsAvailable(true); // Item becomes available again
    }

    public void showLoanDetails() {
        System.out.println("\n--- Loan Record ---");
        member.showMemberDetails();
        item.showDetails();
        System.out.println("Loan Date: " + loanDate);
        System.out.println("Return Date: " + returnDate);
    }
}

// Class 3: System Security/Validator
class LibrarySecurity {

    // Validate item ID format (example: must start with B or D)
    public static boolean validateItemId(String itemId) {
        if (itemId == null || itemId.length() < 2) {
            return false;
        }
        return itemId.startsWith("B") || itemId.startsWith("D");
    }

    // Validate member ID (example: must start with M)
    public static boolean validateMemberId(String memberId) {
        if (memberId == null || memberId.length() < 2) {
            return false;
        }
        return memberId.startsWith("M");
    }

    // Validate date format (simple check: YYYY-MM-DD)
    public static boolean validateDate(String date) {
        if (date == null) return false;

        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}

// Member 3
public class LibraryApp {
}