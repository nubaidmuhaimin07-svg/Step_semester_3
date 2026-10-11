public class CirculationLedger {
    private static String branchCode;

    static {
        branchCode = "PT-01";
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped
                + " null skipped | " + referenceOnly
                + " reference-only | " + regular + " regular";
    }
}
