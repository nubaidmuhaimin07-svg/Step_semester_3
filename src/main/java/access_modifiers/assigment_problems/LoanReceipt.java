import java.util.Arrays;

// Specification conflict: this prompt asks LoanReceipt to be final
// and also asks ReferenceOnlyLoanReceipt to extend it. Java forbids
// extending a final class, so this class is non-final to support the
// required inheritance and instanceof behavior. Its state remains immutable.
public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] correctedIds = getBookIds();
        correctedIds[index] = newId;
        return new LoanReceipt(memberId, correctedIds);
    }

    public static void main(String[] args) {
        LoanReceipt receipt = new LoanReceipt(
                "LIB-8841", new String[] {"BK-100", "BK-101"});

        String[] ids = receipt.getBookIds();
        ids[0] = "HACKED";
        System.out.println(Arrays.toString(receipt.getBookIds()));

        LoanReceipt corrected = receipt.withCorrectedBookId(1, "BK-102");
        System.out.println(Arrays.toString(receipt.getBookIds()));
        System.out.println(Arrays.toString(corrected.getBookIds()));

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                    "LIB-001", new String[] {"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[] {"BK-201"})
        };

        System.out.println(CirculationLedger.processNightlyCirculation(receipts));
    }
}
