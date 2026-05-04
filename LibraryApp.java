import java.util.Scanner;

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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create library items
        Book item1 = new Book("The Great Gatsby", "B001", "F. Scott Fitzgerald");
        DVD item2 = new DVD("Oppenheimer", "D001", 160);

        // Display details of library items
         System.out.println("Available Library Items:");
            item1.showDetails();
            item2.showDetails();

        // Create a member
        Member member1 = new Member("M001", "Muhammad Reza");

        //access control loop
        boolean accessgranted = false;

        while(accessgranted == false) {

            //Verify member
            System.out.println("Enter Member ID : ");
            String memberId = scanner.nextLine();
       
            if(LibrarySecurity.validateMemberId(memberId) == true) {

                accessgranted = true;
                member1.showMemberDetails();
                System.out.println("Member is valid. Access granted.");

                int choice;
                    do {
                        System.out.println("\nMenu:");
                        System.out.println("1. View Loan Records");
                        System.out.println("2. Borrow an Item");
                        System.out.println("3. Return an Item");
                        System.out.println("4. Exit");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
    
                        switch (choice) {
                            case 1:
                                if (!item1.getIsAvailable() == true) {
                                    LoanRecord loan1 = new LoanRecord(item1, member1, "2026-04-25", "Not returned");
                                    loan1.showLoanDetails();
                                    item1.setIsAvailable(false);

                                } else {
                                    System.out.println("You have no current loans.");
                                }
                                break;

                            case 2:
                                item1.showDetails();
                                item2.showDetails();
                                System.out.print("Enter the ID of the item you want to borrow: ");
                                String itemId = scanner.nextLine();         
                                if (LibrarySecurity.validateItemId(itemId)) {
                                    if (itemId.equals(item1.getItemId()) && item1.getIsAvailable()) {
                                        LoanRecord loan2 = new LoanRecord(item1, member1, "2026-04-25", "Not returned");
                                        System.out.println("You have borrowed: " + item1.getTitle());
                                    } else if (itemId.equals(item2.getItemId()) && item2.getIsAvailable()) {
                                        LoanRecord loan3 = new LoanRecord(item2, member1, "2026-04-25", "Not returned");
                                        System.out.println("You have borrowed: " + item2.getTitle());
                                    } else {
                                        System.out.println("Sorry, that item is currently unavailable.");
                                    }
                                } else {
                                    System.out.println("Invalid Item ID. Please try again.");
                                }

                                break;
                            case 3:
                                if (!item1.getIsAvailable()) {
                                    LoanRecord loan3 = new LoanRecord(item1, member1, "2026-04-25", "Not returned");
                                    loan3.returnItem("2026-04-15");
                                    System.out.println("You have returned: " + item1.getTitle());
                                } else {
                                    System.out.println("You have no items to return.");
                                }
                                break;
                            case 4:
                                System.out.println("Exiting the system. See you next time!");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (choice != 4);

            }

            else if (LibrarySecurity.validateMemberId(memberId) == false) {
                System.out.println("Member is invalid. Access denied. Please enter a valid Member ID:");
                accessgranted = false;
            }
        }
    }
}