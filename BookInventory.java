class BookInventory {
    String title;
    String author;
    int copies;
    BookInventory(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.copies = copies;
    }
    void printEntry() {
        System.out.println(title + " by " + author
                + " - " + copies + " copies available");
    }
    public static void main(String[] args) {
        BookInventory b1 =
                new BookInventory("Clean Code", "Robert C. Martin", 3);
        BookInventory b2 =
                new BookInventory("Effective Java", "Joshua Bloch", 5);
        BookInventory b3 =
                new BookInventory("Refactoring", "Martin Fowler", 0);
        BookInventory b4 =
                new BookInventory("Design Patterns", "GoF", 2);
        b1.printEntry();
        b2.printEntry();
        b3.printEntry();
        b4.printEntry();
    }
}
