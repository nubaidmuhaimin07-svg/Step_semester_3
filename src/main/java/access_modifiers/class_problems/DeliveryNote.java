public abstract class DeliveryNote {
    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        if (signature == null || signature.trim().isEmpty()) {
            return confirmDelivery();
        }
        return confirmDelivery() + ", signed by " + signature;
    }
}
