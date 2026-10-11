public class PrintManager {
    public static void printAll(Printable[] items) {
        for (Printable item : items) {
            if (item != null) {
                System.out.println(item.printLabel());
            }
        }
    }

    public static void main(String[] args) {
        Printable[] items = {
            new PackageBox("TRK-88"),
            new Invoice("INV-42")
        };
        printAll(items);
    }
}
