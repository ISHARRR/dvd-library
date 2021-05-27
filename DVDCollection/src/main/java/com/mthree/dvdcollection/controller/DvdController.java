/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdcollection.controller;

import com.mthree.dvdcollection.dao.DvdDao;
import com.mthree.dvdcollection.dao.DvdDaoExceptions;
import com.mthree.dvdcollection.model.DvdCollection;
import com.mthree.dvdcollection.view.DvdView;
import com.mthree.dvdcollection.view.UserIO;
import com.mthree.dvdcollection.view.UserIOConsoleImpl;

/**
 *
 * @author ishar
 */
public class DvdController {

    private final DvdView view;
    private final DvdDao dao;
    private final UserIO io = new UserIOConsoleImpl();

    public DvdController(DvdView view, DvdDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = view.getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        allDvds();
                        break;
                    case 2:
                        createDvdEntery();
                        break;
                    case 3:
                        removeDvd();
                        break;
                    case 4:
                        selectDvd();
                        break;
                    case 5:
                        searchDvd();
                        break;
                    case 6:
                        updateDvd();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        io.print("UNKNOWN COMMAND");
                }

            }
            view.exitMessage();
        } catch (DvdDaoExceptions e) {
            view.displayErrorMessage();
        }
    }

    public void createDvdEntery() throws DvdDaoExceptions {
        view.createNewDvdBanner();
        DvdCollection dvd = view.createNewDvdInput();
        dao.addDvd(dvd);
        view.taskCompleted();

    }

    public void allDvds() throws DvdDaoExceptions {
        view.allDvds();
        dao.getAllDvds();
        view.allDvds();

    }

    public void removeDvd() throws DvdDaoExceptions {
        view.removeDvdBanner();
        String id = view.removeDvdInput();
        dao.removeDvd(id);
        view.taskCompleted();

    }

    public void selectDvd() throws DvdDaoExceptions {
        view.viewSelectedDvdBanner();
        String id = view.selectedDvdInput();
        System.out.println(dao.dvdInfo(id));
        view.taskCompleted();

    }

    public void searchDvd() throws DvdDaoExceptions{
        view.searchDvdBanner();
        String title = view.searchDvdInput();
        System.out.println(dao.searchDvds(title));
        view.taskCompleted();

    }

    public void updateDvd() throws DvdDaoExceptions{
        view.updateDvdBanner();
        String idUpdate = view.updateID();
        DvdCollection dvd = view.updateDvdInput();
        dao.updateDvd(idUpdate, dvd);
        view.taskCompleted();
    }

}
