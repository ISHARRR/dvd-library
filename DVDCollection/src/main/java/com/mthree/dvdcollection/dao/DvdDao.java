/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdcollection.dao;

import com.mthree.dvdcollection.model.DvdCollection;
import java.util.List;

/**
 *
 * @author ishar
 */
public interface DvdDao {
    DvdCollection addDvd(DvdCollection dvd) throws DvdDaoExceptions;
    
    DvdCollection removeDvd(String id) throws DvdDaoExceptions;
    
    DvdCollection updateDvd(String id, DvdCollection dvd) throws DvdDaoExceptions;
    
    void getAllDvds() throws DvdDaoExceptions;
    
    List<DvdCollection> getAllDvdsForFile() throws DvdDaoExceptions;
    
    DvdCollection dvdInfo(String id) throws DvdDaoExceptions;
    
    DvdCollection searchDvds(String title) throws DvdDaoExceptions;
    
}
