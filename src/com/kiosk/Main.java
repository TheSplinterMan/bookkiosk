package com.kiosk;

/**
 * Last Edit by Denzell Moss on 4/11/22
 *
 * Beta Functions Completed:
 * Login
 * Browse Books
 * Search for Book (By Title & Author)
 * Borrow Book (accessed via the 2 previous functions)
 * Return Book
 * Donate Used Books
 * Logout
 *
 * What's Next:
 * Reserve Book Function
 * DB Connection
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Book> currentBookInventory = new ArrayList<>();

    public static void main(String[] args) {
	    System.out.println("Welcome to the CSU Libray Kiosk");

        /** Making a student for testing purposes; will need to import a database */
        Student student = new Student(12345, "Jordan", "Doe", "password123", false);

        /** Adding books manually for testing purposes */
        addBookToInventory("The Cat in the Hat", 1, "Dr. Seuss", "978-0-7172-6059-1", "Children");
        addBookToInventory("Computer Science: An Overview", 2, "Gleen Brookshear, Dennis Brylow", "9780134875460", "Computer Science");

        /**Logging into the Kiosk*/
       login();
    }

    //Methods ///////////////////////////////////////////////////////////////////////////////

    public static void login(){
        boolean loggedIn = false;

        /**
         * Assuming that the user is a student for now. Will need to have a
         * separate login for LibraryStaff.
         **/

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter Your Student ID: ");
        String user = scanner.nextLine();

        /**
         * Scanning the database for entered student ID to see if it exists.
         * If it matches a valid ID, find the password and compare to entered
         * password.
         */

        Student student = new Student(Integer.parseInt(user), "Unknown", "Unknown", "password123", false);

        while (loggedIn == false) {
            System.out.print("Please enter your password: ");
            String pass = scanner.nextLine();
            if (pass.equals(student.password)) {
                loggedIn = true;

                //Welcome Message
                System.out.println(" ");
                System.out.println("Welcome, "+student.Fname+" "+student.Lname+"!");
                kioskButtonOptions(student);

            } else {
                System.out.println("Incorrect password. Check for errors and try again.");
            }
        }
    }

    public static void logout(){
        System.out.println("Logout successful."+'\n');
        System.out.println("Welcome to the CSU Libray Kiosk");
        login();
        //Method to log out TO-DO
    }

    /**
     * This method loads books from the DB and places them into the local inventory ArrayList for easy access
     */
    public static void loadBookInventory(){
        //TO-DO
    }

    /**
     * Main Kiosk Functions; all the buttons and method triggers are here
     * @param student
     */
    public static void kioskButtonOptions(Student student){
        boolean quit = false;

        do {
            System.out.println(" ");
            System.out.println("Select what you would like to do:");
            System.out.println("1: Browse Books by Category");
            System.out.println("2: Search by Book Title or Author");
            System.out.println("3: Return Book");
            System.out.println("4: Donate Used Textbook");
            System.out.println("5: Logout");
            System.out.println(" ");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            switch (input){
                case "1":
                    System.out.println("Selected: Browse Books by Category");
                    browseByCategory(student);
                    break;
                case "2":
                    System.out.println("Selected: Search for Book by Title or Author");
                    searchBookByTitleAuthor(student);
                    break;
                case "3":
                    System.out.println("Return Borrowed Book: TO-DO");
                    returnBook(student);
                    break;
                case "4":
                    System.out.println("Selected: Donate used textbook");
                    donateUsedBook();
                    break;
                case "5":
                    System.out.println("Logging Out...");
                    logout();
                    quit = true;
                    break;
                case "inv":
                    System.out.println("SYSTEM OVERRIDE: PRINT ALL INVENTORY");
                    for (int i = 0; i < currentBookInventory.size(); i++){
                        System.out.println(currentBookInventory.get(i).getBookTitle());
                    }
                    break;
                case "stud":
                    System.out.println("SYSTEM OVERRIDE: PRINT STUDENT INVENTORY");
                    for (int i = 0; i < student.getStudentInventory().size(); i++){
                        System.out.println(student.getStudentInventory().get(i).getBookTitle());
                    }
                default:
                    break;
            }

        } while (quit == false);

    }

    /**
     * Adds a book to the DB Inventory and the local inventory ArrayList
     * @param bookTitle
     * @param BookID
     * @param Author
     * @param ISNnum
     * @param category
     */
    public static void addBookToInventory(String bookTitle, int BookID, String Author, String ISNnum, String category){
        Book newBook = new Book(bookTitle, BookID, Author, ISNnum, category, false);
        //Load Book into DB inventory here
        //TO-DO

        //Load Book into local storage
        currentBookInventory.add(newBook);
    }

    /**A method to tag whether a book is available or not based on their isCheckedOut condition
     * */
    public static void bookAvailableTag(Book book){
        if (book.isCheckedOut() == true){
            System.out.println("Currently Not Available");
        } else {
            System.out.println("Currently Available");
        }
    }

    public static void browseByCategory(Student student){
        System.out.println("Select a category:");
        Scanner scan = new Scanner(System.in);
       String categorySelection = scan.nextLine();
       ArrayList<Book> queryList = new ArrayList<>();

       int queryCount = 0;
       queryList.add(null); // The first spot will be null so that we can index correctly

       for (int i = 0; i < currentBookInventory.size(); i++){
           Book cbook = currentBookInventory.get(i);
           if((cbook.getCategory().equalsIgnoreCase(categorySelection))){
               queryCount++;
               queryList.add(cbook);

               System.out.println(queryCount+": "+cbook.getBookTitle());
               bookAvailableTag(cbook);
               System.out.println('\n'+"More Details:");
               System.out.println("Author(s): " + cbook.getAuthor());
               System.out.println("ISN Number: "+ cbook.getISNnum());
               System.out.println("Category: " + cbook.getCategory());
               System.out.println('\n');
           }
       }

       if (queryCount == 0){
           System.out.println("No results found!");
       } else {

           System.out.println("Would you like to borrow a book? If so, choose a corresponding number. " +
                   "If  not, choose BACK");
           String chosenBook = scan.nextLine();

           if (!(chosenBook.equalsIgnoreCase("back"))){
               int option = Integer.parseInt(chosenBook);
               borrowBook(student, queryList.get(option).BookID);
               System.out.println("Successful!");
           }
       }
    }

    public static void searchBookByTitleAuthor(Student student){
        System.out.println("How would you like to search?"+'\n'+"1: Title"+'\n'+"2: Author");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int queryCount = 0;

        switch (input){
            case "1":
                System.out.println("Please enter the title:");
                String titleQuery = scan.nextLine();

                for (int i = 0; i < currentBookInventory.size(); i++){
                    Book cbook = currentBookInventory.get(i);
                    if (cbook.getBookTitle().contains(titleQuery)){
                        queryCount++;
                        System.out.println(queryCount+": "+cbook.getBookTitle());
                        bookAvailableTag(cbook);
                        System.out.println('\n'+"More Details:");
                        System.out.println("Author(s): " + cbook.getAuthor());
                        System.out.println("ISN Number: "+ cbook.getISNnum());
                        System.out.println("Category: " + cbook.getCategory());
                        System.out.println('\n');
                    }
                }

                if (queryCount == 0){
                    System.out.println("No results found!");
                } else {

                    System.out.println("Would you like to borrow a book? If so, choose a corresponding number. " +
                            "If  not, choose BACK");
                    String chosenBook = scan.nextLine();

                    if (!(chosenBook.equalsIgnoreCase("back"))){
                        int option = Integer.parseInt(chosenBook);
                        borrowBook(student, option);
                        System.out.println("Successful!");
                    }
                }
                break;
            case "2":
                System.out.println("Please enter the author:");
                String authorQuery = scan.nextLine();

                for (int i = 0; i < currentBookInventory.size(); i++){
                    Book cbook = currentBookInventory.get(i);
                    if (cbook.getAuthor().contains(authorQuery)){
                        queryCount++;
                        System.out.println(queryCount+": "+cbook.getBookTitle());
                        bookAvailableTag(cbook);
                        System.out.println('\n'+"More Details:");
                        System.out.println("Author(s): " + cbook.getAuthor());
                        System.out.println("ISN Number: "+ cbook.getISNnum());
                        System.out.println("Category: " + cbook.getCategory());
                        System.out.println('\n');
                    }
                }
                if (queryCount == 0){
                    System.out.println("No results found!");
                } else {

                    System.out.println("Would you like to borrow a book? If so, choose a corresponding number. " +
                            "If  not, choose BACK");
                    String chosenBook = scan.nextLine();

                    if (!(chosenBook.equalsIgnoreCase("back"))){
                        int option = Integer.parseInt(chosenBook);
                        borrowBook(student, option);
                        System.out.println("Successful!");
                    }
                }
                break;
            default:
                break;
        }

        if (queryCount == 0){
            System.out.println("No results found!");
        }
    }

    public static void borrowBook(Student student, int id){
        Scanner scan = new Scanner(System.in);
        //Traverse DB/Arraylist for desired book
        for (Book desired_book : currentBookInventory){
            if (desired_book.getBookID() == id){
                if (desired_book.isCheckedOut == true) {
                    System.out.println("This book is currently unavailable.");
                    System.out.println("Would you like to reserve this book? y or n");
                    String input = scan.nextLine();

                    switch (input){
                        case "y":
                            reserveBook(desired_book);
                            break;
                        case "n":
                            System.out.println("Going back to Main Menu...");
                            break;
                        default:
                            System.out.println("Invalid response. Going back to Main Menu...");
                    }

                } else {
                    desired_book.isCheckedOut = true;
                    student.getStudentInventory().add(desired_book);
                }
            }
        }
    }

    public static void reserveBook(Book book){
        System.out.println("Ok, reserving book..."); //TO-DO
    }

    public static void returnBook(Student student){
        System.out.println("Please select the book you would like to return:");
        Scanner scan = new Scanner(System.in);
        ArrayList<Book> queryList = new ArrayList<>();

        int queryCount = 0;
        queryList.add(null); // The first spot will be null so that we can index correctly

        for (int i = 0; i < student.studentInventory.size(); i++){
            Book cbook = student.studentInventory.get(i);
            queryCount++;
            queryList.add(cbook);

            System.out.println(queryCount+": "+cbook.getBookTitle());

        }

        if (queryCount == 0){
            System.out.println("You currently have no books to return!");
        } else {

            System.out.println("Choose a corresponding number. " +
                    "If BACK to cancel the return.");
            String chosenBook = scan.nextLine();

            if (!(chosenBook.equalsIgnoreCase("back"))){
                int option = Integer.parseInt(chosenBook);
                queryList.get(option).isCheckedOut = false;

                // Find the matching item in the main Arraylist and update
                for (int j = 0; j < currentBookInventory.size(); j++){
                    if (currentBookInventory.get(j).BookID == queryList.get(option).BookID){
                        currentBookInventory.get(j).isCheckedOut = false;
                    }
                }

                //Remove from Student's Book Inventory
                for (int k = 0; k < student.studentInventory.size(); k++){
                    if (queryList.get(option).BookID == student.studentInventory.get(k).BookID){
                        student.studentInventory.remove(k);
                    }
                }



                System.out.println("Successful!");
            }
        }
    }

    public static void donateUsedBook(){
        System.out.println("Thank you for your donation! Please enter the valid information:");
        Scanner scan = new Scanner(System.in);
        System.out.println("Book Title: ");
        String title = scan.nextLine();

        System.out.println("Book Author: ");
        String author = scan.nextLine();

        System.out.println("Book ISN Number: ");
        String ISNnum = scan.nextLine();

        System.out.println("Book Category: ");
        String category = scan.nextLine();

        //Get ID based off last element in the DB/Arraylist
        int id = currentBookInventory.size() - 1;

        //Create Book and Add to Inventory
        addBookToInventory(title, id, author, ISNnum, category);
        System.out.println('\n'+"Donation successful!");
    }

} // End of Main

class UnivMember {

    //Variables
    int id;
    String Fname;
    String Lname;
    String password;
    boolean isStaff;

    //Constructor for Univ Member
    public UnivMember(int id, String Fname, String Lname, String password, boolean isStaff){
        this.id = id;
        this.Fname = Fname;
        this.Lname = Lname;
        this.password = password;
        this.isStaff = isStaff;
    }

    //Getters for each variable
    public int getId() {
        return id;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStaff() {
        return isStaff;
    }

    //Setters for each variable
    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }
}

class Student extends UnivMember {

    //Variables
    ArrayList<Book> studentInventory = new ArrayList<>();

    public Student(int id, String Fname, String Lname, String password, boolean isStaff) {
        super(id, Fname, Lname, password, false);
    }

    public ArrayList<Book> getStudentInventory() {
        return studentInventory;
    }

}

class LibraryStaff extends UnivMember {

    public LibraryStaff(int id, String Fname, String Lname, String password, boolean isStaff) {
        super(id, Fname, Lname, password, true);
    }
}

class Book {
    //Variables
    String bookTitle;
    int BookID;
    String Author;
    String ISNnum;
    String category;
    boolean isCheckedOut;

    public Book(String bookTitle, int BookID, String Author, String ISNnum, String category, boolean isCheckedOut){
        this.bookTitle = bookTitle;
        this.BookID = BookID;
        this.Author = Author;
        this.ISNnum = ISNnum;
        this.category = category;
        this.isCheckedOut = isCheckedOut;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getBookID() {
        return BookID;
    }

    public String getAuthor() {
        return Author;
    }

    public String getCategory() {
        return category;
    }

    public String getISNnum() {
        return ISNnum;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

}