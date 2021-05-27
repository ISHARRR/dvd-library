/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdcollection.model;

import com.mthree.dvdcollection.dao.DvdDaoImpl;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ishar
 */
public class DvdCollection extends Dvd{
   
    private String id;
    private String mpaaRating;
    private Director dir;
    private String studio;
    private String userRating;

    public DvdCollection(String id, String title, String releaseDate, String mpaaRating, Director dir, String studio, String userRating) {
        super(title, releaseDate);
        this.id = id;
        this.mpaaRating = mpaaRating;
        this.dir = dir;
        this.studio = studio;
        this.userRating = userRating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return dir.getfName() + " " + dir.getlName();
    }

    public String getDvdDetails() {
        return "Title - " + getTitle();
    }

    public void setDir(Director dir) {
        this.dir = dir;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
//        String format = "ID:" + id + ", Dvd: " + getDvdDetails() + ", MPAA Rating: " + getMpaaRating() 
//                + ", Director: " + getDirectorName() + ", Studio: " 
//                + getStudio() + ", User Review: " + getUserRating();
        String format = "ID:" + id + ", Dvd: " + getDvdDetails();
        return format;
    }

}


