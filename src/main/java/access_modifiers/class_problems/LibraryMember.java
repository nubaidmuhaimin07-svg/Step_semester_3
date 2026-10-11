import java.util.Arrays;

public class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;
    private int[] fineHistory = new int[10];
    private int fineCount;
    private int totalFine;
    private String lastGenre = "";

    private static int memberCounter = 100;
    private final String memberNumber;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Member ID must have at least 4 non-space characters.");
        }
        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Borrow limit must be positive.");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        memberCounter++;
        this.memberNumber = "LIB-" + memberCounter;
    }

    public LibraryMember(int borrowLimit) {
        this("MEMBER", borrowLimit);
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        lastGenre = genre;
        borrowBook();
    }

    protected void chargeFine(int amount) {
        if (amount > 0 && fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
            totalFine += amount;
        }
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        return totalFine;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public String getLastGenre() {
        return lastGenre;
    }

    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " + booksBorrowed);
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return memberCounter - 100;
    }
}
