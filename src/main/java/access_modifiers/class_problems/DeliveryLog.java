public class DeliveryLog {
    public static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            if (note != null) {
                System.out.println(note.confirmDelivery());
            }
        }
    }

    public static void main(String[] args) {
        DeliveryNote parcel = new ParcelNote("TRK-1");
        logAll(new DeliveryNote[] {
            parcel,
            new LetterNote("TRK-2")
        });
    }
}
