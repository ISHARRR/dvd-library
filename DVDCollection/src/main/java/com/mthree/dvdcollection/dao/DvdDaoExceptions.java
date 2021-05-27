/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdcollection.dao;

/**
 *
 * @author ishar
 */
public class DvdDaoExceptions extends Exception {

    public DvdDaoExceptions(String message) {
        super(message);
    }

    public DvdDaoExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}
