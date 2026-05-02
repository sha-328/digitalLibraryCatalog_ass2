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
class Member {
}

// Class 2: Handles the logic of a "Loan"
class LoanRecord {
}

// Class 3: System Security/Validator
class LibrarySecurity {
}

// Member 3
public class LibraryApp {
}