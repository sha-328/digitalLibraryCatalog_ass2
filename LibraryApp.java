import java.util.Scanner;

abstract class LibraryItem {
    private String title;
    private String itemId;
    private boolean isAvailable;

    public LibraryItem(String title, String itemId) {
        this.title = title;
        this.itemId = itemId;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getItemId() { return itemId; }
    public boolean getIsAvailable() { return isAvailable; }

    public void setIsAvailable(boolean status) { this.isAvailable = status; }
    public void setTitle(String title) { this.title = title; }
    public void setItemId(String itemId) { this.itemId = itemId; }

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

// Class 1: Member management
class Member {
    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }

    public void showMemberDetails() {
        System.out.println("Member ID: " + memberId + ", Name: " + name);
    }
}

// Class 2: Loan management logic
class LoanRecord {
    private LibraryItem item;
    private Member member;
    private String loanDate;
    private String status;

    public LoanRecord(LibraryItem item, Member member, String loanDate, String status) {
        this.item = item;
        this.member = member;
        this.loanDate = loanDate;
        this.status = status;
        item.setIsAvailable(false); 
    }

    public void returnItem() {
        this.status = "Returned";
        item.setIsAvailable(true);
    }

    public void showLoanDetails() {
        System.out.println("\n--- Current Loan Status ---");
        System.out.println("Item: " + item.getTitle() + " | Member: " + member.getName() + " | Status: " + status);
    }
}

// Class 3: Static utility for validation
class LibrarySecurity {
    public static boolean validateMemberId(String id) {
        return id != null && id.startsWith("M");
    }

    public static boolean validateItemId(String id) {
        return id != null && (id.startsWith("B") || id.startsWith("D"));
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instantiate Classes
        Book book1 = new Book("The Great Gatsby", "B001", "F. Scott Fitzgerald");
        DVD dvd1 = new DVD("Oppenheimer", "D001", 180);
        Member member1 = new Member("M001", "Muhammad Reza");
        LoanRecord activeLoan = null; // To track if a loan exists

        boolean accessGranted = false;

        System.out.println("=== Digital Library System ===");
        
        while (!accessGranted) {
            System.out.print("Enter Member ID to Login: ");
            String inputId = scanner.nextLine();

            if (LibrarySecurity.validateMemberId(inputId)) {
                accessGranted = true;
                System.out.println("Welcome, " + member1.getName() + "!");
                
                int choice;
                do {
                    System.out.println("\n1. View Catalog\n2. Borrow Item\n3. Return Item\n4. Exit");
                    System.out.print("Choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (choice) {
                        case 1:
                            book1.showDetails();
                            dvd1.showDetails();
                            break;
                        case 2:
                            System.out.print("Enter Item ID: ");
                            String id = scanner.nextLine();
                            // Logic for Borrowing
                            if (id.equalsIgnoreCase(book1.getItemId()) && book1.getIsAvailable()) {
                                activeLoan = new LoanRecord(book1, member1, "2026-05-04", "Active");
                                System.out.println("Successfully borrowed: " + book1.getTitle());
                            } else if (id.equalsIgnoreCase(dvd1.getItemId()) && dvd1.getIsAvailable()) {
                                activeLoan = new LoanRecord(dvd1, member1, "2026-05-04", "Active");
                                System.out.println("Successfully borrowed: " + dvd1.getTitle());
                            } else {
                                System.out.println("Item unavailable or invalid ID.");
                            }
                            break;
                        case 3:
                            if (activeLoan != null) {
                                activeLoan.returnItem();
                                System.out.println("Item returned successfully.");
                            } else {
                                System.out.println("No active loans found.");
                            }
                            break;
                        case 4:
                            System.out.println("Goodbye!");
                            break;
                    }
                } while (choice != 4);
            } else {
                System.out.println("Invalid ID. (Hint: ID must start with 'M')");
            }
        }
        scanner.close();
    }
}