/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdcollection.view;

import com.mthree.dvdcollection.model.Director;
import com.mthree.dvdcollection.model.Dvd;
import com.mthree.dvdcollection.model.DvdCollection;

/**
 *
 * @author ishar
 */
public class DvdView {

    private final UserIO io;

    public DvdView(UserIO io) {
        this.io = io;
    }

//  ------ banners ------
    public void createNewDvdBanner() {
        io.print("=== ADD NEW DVD TO COLLECTION ===");
    }

    public void allDvds() {
        io.print("=== DVD COLLECTION ===");
    }

    public void removeDvdBanner() {
        io.print("=== REMOVE DVD FROM COLLECTION ===");
    }

    public void viewSelectedDvdBanner() {
        io.print("=== VIEW SELECTED DVD ===");
    }

    public void searchDvdBanner() {
        io.print("=== SEARCH DVD ===");
    }

    public void updateDvdBanner() {
        io.print("=== UPDATE DVD ===");
    }

    public void displayErrorMessage() {
        io.print("=== ERROR ===");
    }
    
//    public void displayInvalidEnteryMessage() {
//        io.print("=== INVALID ENTERY, PLEASE TRY AGAIN ===");
//    }
    public void taskCompleted() {
        io.print("=== TASK COMLETED ===");
    }

    public void exitMessage() {
        io.print("=== EXITING ===");
    }

//  ------ Inputs ------
    public DvdCollection createNewDvdInput() {
        String id = io.readId("Dvd ID - 5 Digits");
        String title = io.readString("Dvd title");
        String releaseDate = io.readDate("Enter Date");
        String mpaaRating = io.readMpaa("Enter MPAA Rating: 1 = U, 2 = PG, 3 = 12, 4 = 15, 5 = 18, 6 = R18");
        Director directorName = new Director(io.readPersonName("Enter Directors First Name"), io.readPersonName("Enter Directors Last Name"));
        String studio = io.readString("Enter Studio Name");
        String review = io.readString("Enter Review");
        DvdCollection currentDvd = new DvdCollection(id, title, releaseDate, mpaaRating, directorName, studio, review);
        return currentDvd;
    }

    public DvdCollection updateDvdInput() {
        io.print("Enter the updated version of the Dvd");
        String id = io.readId("Dvd ID - 5 Digits");
        String title = io.readString("Dvd title");
        String releaseDate = io.readDate("Enter Date");
        String mpaaRating = io.readMpaa("Enter MPAA Rating: 1 = U, 2 = PG, 3 = 12, 4 = 15, 5 = 18, 6 = R18");
        Director directorName = new Director(io.readPersonName("Enter Directors First Name"), io.readPersonName("Enter Directors Last Name"));
        String studio = io.readString("Enter Studio Name");
        String review = io.readString("Enter Review");
        DvdCollection currentDvd = new DvdCollection(id, title, releaseDate, mpaaRating, directorName, studio, review);
        return currentDvd;
    }

    public String updateID() {
        return io.readId("Enter ID of DVD you wish to update");
    }

    public String removeDvdInput() {
        return io.readId("Enter ID of DVD you wish to remove");
    }

    public String selectedDvdInput() {
        return io.readId("Enter ID of DVD you wish to view");
    }

    public String searchDvdInput() {
        return io.readString("Enter title of DVD you wish to view");
    }

    public int getMenuSelection() {
        boolean flag = true;
        
        io.print("Main Menu");
        io.print("1. List DVD Collection");
        io.print("2. Add new DVD");
        io.print("3. Remove a DVD");
        io.print("4. Selected a DVD");
        io.print("5. Search for a DVD");
        io.print("6. Update a DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);

    }

}
