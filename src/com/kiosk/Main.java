package com.kiosk;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Welcome to the CSU Libray Kiosk");

        /** Making a student for testing purposes; will need to import a database */
        Student student = new Student(12345, "Jordan", "Doe", "password123", false);

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

        while (loggedIn == false) {
            System.out.print("Please enter your password: ");
            String pass = scanner.nextLine();
            if (pass.equals(student.password)) {
                loggedIn = true;

                //Welcome Message
                System.out.println("Welcome, "+student.Fname+" "+student.Lname+"!");

            } else {
                System.out.println("Incorrect password. Check for errors and try again.");
            }
        }

    }
}

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

    public Student(int id, String Fname, String Lname, String password, boolean isStaff) {
        super(id, Fname, Lname, password, false);
    }
}

class LibraryStaff extends UnivMember {

    public LibraryStaff(int id, String Fname, String Lname, String password, boolean isStaff) {
        super(id, Fname, Lname, password, true);
    }
}