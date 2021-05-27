/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdcollection;

import com.mthree.dvdcollection.controller.DvdController;
import com.mthree.dvdcollection.dao.DvdDao;
import com.mthree.dvdcollection.dao.DvdDaoImpl;
import com.mthree.dvdcollection.view.DvdView;
import com.mthree.dvdcollection.view.UserIO;
import com.mthree.dvdcollection.view.UserIOConsoleImpl;


/**
 *
 * @author ishar
 */
public class App {
    public static void main(String[] args) {
        UserIO dvdIO = new UserIOConsoleImpl();
        DvdView dvdView = new DvdView(dvdIO);
        DvdDao dvdDao = new DvdDaoImpl();
        DvdController dvdController = new DvdController(dvdView, dvdDao);
        dvdController.run();
        
    }
}
