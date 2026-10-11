import java.util.Arrays;

// The prompt says BookingReceipt must be final, but also requires
// GroupBookingReceipt to extend it. Java cannot extend a final class.
// This class is non-final so the required instanceof example works;
// its fields and seat data remain immutable.
public class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public String getBookingId() { return bookingId; }

    public String[] getSeatNumbers() {
        return Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updatedSeats = getSeatNumbers();
        updatedSeats[index] = newSeat;
        return new BookingReceipt(bookingId, updatedSeats);
    }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0, nullSkipped = 0, group = 0, individual = 0;

        for (BookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (receipt instanceof GroupBookingReceipt) group++;
            else individual++;
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        BookingReceipt booking = new BookingReceipt("CH-1001", new String[] {"A1", "A2"});
        String[] seats = booking.getSeatNumbers();
        seats[0] = "X";
        System.out.println(Arrays.toString(booking.getSeatNumbers()));

        BookingReceipt updated = booking.withUpdatedSeat(1, "A3");
        System.out.println(Arrays.toString(booking.getSeatNumbers()));
        System.out.println(Arrays.toString(updated.getSeatNumbers()));

        BookingReceipt[] batch = {
            new GroupBookingReceipt("CH-2002", new String[] {"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[] {"C1"})
        };
        System.out.println(processNightlySettlement(batch));
    }
}
