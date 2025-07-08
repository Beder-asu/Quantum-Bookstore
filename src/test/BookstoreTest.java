package test;

import model.*;
import services.*;

public class BookstoreTest {
    private final InventoryManager inventoryManager = new InventoryManager();

    public BookstoreTest() {
        this.runAll();
    }

    public void runAll() {
        System.out.println("\n==== Quantum Bookstore Readable Tests ====\n");

        runTest("Happy Bulk PaperBook Purchase", this::testHappyBulkPaperBook);
        runTest("Invalid ISBN", this::testInvalidISBN);
        runTest("Zero Quantity", this::testZeroQuantity);
        runTest("Non-Sellable ShowcaseBook", this::testNonSellable);
        runTest("Boundary Stock Purchase", this::testBoundaryStock);

        // Additional edge cases
        runTest("Duplicate ISBNs in Inventory", this::testDuplicateISBN);
        runTest("Buy from Empty Inventory", this::testEmptyInventory);
        runTest("Extremely Large Quantity", this::testLargeQuantity);
        runTest("Null Delivery Contact", this::testNullContactInfo);
        runTest("Blank Delivery Contact", this::testBlankContactInfo);
        runTest("Remove Book Before Purchase", this::testRemovedBook);

        System.out.println("\n==== All Tests Completed ====");
    }

    private void runTest(String name, Runnable test) {
        System.out.println("---- " + name + " ----");
        test.run();
        System.out.println();
    }

    // Original tests
    private void testHappyBulkPaperBook() {
        printCode("PaperBook pb = new PaperBook(\"P-001\", \"Java 101\", \"A. Author\", 2023, 25.0, 5);");
        printCode("inventoryManager.addBook(pb);");
        printCode("inventoryManager.buyBook(\"P-001\", 3, \"123 Main St\");");

        PaperBook pb = new PaperBook("P-001", "Java 101", "A. Author", 2023, 25.0, 5);
        inventoryManager.addBook(pb);
        inventoryManager.buyBook("P-001", 3, "123 Main St");
        assert pb.getStock() == 2 : "Expected stock 2, got " + pb.getStock();
    }

    private void testInvalidISBN() {
        printCode("inventoryManager.buyBook(\"XXX-999\", 1, \"any\");");
        inventoryManager.buyBook("XXX-999", 1, "any");
    }

    private void testZeroQuantity() {
        PaperBook pb = new PaperBook("P-002", "Kotlin Basics", "B. Builder", 2022, 30.0, 4);
        inventoryManager.addBook(pb);

        printCode("inventoryManager.buyBook(\"P-002\", 0, \"123 Main St\");");
        inventoryManager.buyBook("P-002", 0, "123 Main St");
        assert pb.getStock() == 4 : "Stock should remain 4, got " + pb.getStock();
    }

    private void testNonSellable() {
        ShowcaseBook sb = new ShowcaseBook("S-001", "Demo Title", "C. Creator", 2020, 50.0);
        inventoryManager.addBook(sb);

        printCode("inventoryManager.buyBook(\"S-001\", 1, \"N/A\");");
        inventoryManager.buyBook("S-001", 1, "N/A");
    }

    private void testBoundaryStock() {
        PaperBook pb = new PaperBook("P-003", "Go in Action", "D. Developer", 2021, 40.0, 2);
        inventoryManager.addBook(pb);

        printCode("inventoryManager.buyBook(\"P-003\", 2, \"456 Elm St\");");
        inventoryManager.buyBook("P-003", 2, "456 Elm St");
        assert pb.getStock() == 0 : "Expected stock 0, got " + pb.getStock();

        printCode("inventoryManager.buyBook(\"P-003\", 1, \"456 Elm St\");");
        inventoryManager.buyBook("P-003", 1, "456 Elm St");
    }

    // Additional tests
    private void testDuplicateISBN() {
        PaperBook pb1 = new PaperBook("P-004", "Book A", "Author X", 2020, 15.0, 3);
        PaperBook pb2 = new PaperBook("P-004", "Book B", "Author Y", 2021, 20.0, 5);

        printCode("inventoryManager.addBook(pb1);");
        inventoryManager.addBook(pb1);

        printCode("inventoryManager.addBook(pb2);");
        inventoryManager.addBook(pb2);

        printCode("inventoryManager.buyBook(\"P-004\", 2, \"789 Pine St\");");
        inventoryManager.buyBook("P-004", 2, "789 Pine St");

        // Should use the first match (pb1 or pb2, depending on implementation)
    }

    private void testEmptyInventory() {
        InventoryManager emptyManager = new InventoryManager();
        printCode("emptyManager.buyBook(\"P-999\", 1, \"Empty St\");");
        emptyManager.buyBook("P-999", 1, "Empty St");
    }

    private void testLargeQuantity() {
        PaperBook pb = new PaperBook("P-005", "Big Order Book", "Big Author", 2022, 50.0, 10);
        inventoryManager.addBook(pb);

        printCode("inventoryManager.buyBook(\"P-005\", Integer.MAX_VALUE, \"Big St\");");
        inventoryManager.buyBook("P-005", Integer.MAX_VALUE, "Big St");
    }

    private void testNullContactInfo() {
        EBook eb = new EBook("E-001", "Null Email Book", "E. Writer", 2023, 10.0, "PDF");
        inventoryManager.addBook(eb);

        printCode("inventoryManager.buyBook(\"E-001\", 1, null);");
        inventoryManager.buyBook("E-001", 1, null);
    }

    private void testBlankContactInfo() {
        EBook eb = new EBook("E-002", "Blank Email Book", "E. Writer", 2023, 10.0, "PDF");
        inventoryManager.addBook(eb);

        printCode("inventoryManager.buyBook(\"E-002\", 1, \"   \");");
        inventoryManager.buyBook("E-002", 1, "   ");
    }

    private void testRemovedBook() {
        PaperBook pb = new PaperBook("P-006", "Removed Book", "Removed Author", 2022, 20.0, 2);
        inventoryManager.addBook(pb);

        printCode("inventoryManager.removeBook(pb);");
        inventoryManager.removeBook(pb);

        printCode("inventoryManager.buyBook(\"P-006\", 1, \"Removed St\");");
        inventoryManager.buyBook("P-006", 1, "Removed St");
    }

    private void printCode(String line) {
        System.out.println(">> " + line);
    }
}
