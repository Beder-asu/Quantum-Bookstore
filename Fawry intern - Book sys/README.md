Quantum Bookstore System
=========================



To compile and run the bookstore application from the command line:

1. Open Command Prompt and navigate to your project directory:  
   cd "<your-project-directory>"
2. Compile the Java source files (no external libraries required):  
   javac -d bin src\App.java src\test\BookstoreTest.java src\model\*.java src\services\*.java
3. Run the application:  
   java -cp "bin" App

This will execute the App.java file, which runs your test suite and showcases the bookstore logic.  
Replace <your-project-directory> with the path to your project folder.  
AOR, you can import the project into any Java IDE (e.g., IntelliJ, Eclipse) and run `App.java` directly.


Folder Structure
----------------
- src/ — Java source code
  - model/     — domain classes (Book, PaperBook, EBook, ShowcaseBook, etc.)
  - services/  — business logic (InventoryManager, MailService, ShippingService)
  - test/      — test classes (BookstoreTest, etc.)
  - App.java   — main entry point
- bin/ — compiled output (created after build)

PS:
- The `BookstoreTest.java` file contains a complete test suite to validate core features and edge cases.  
- No external libraries (like JDBC) are required for this version.  

