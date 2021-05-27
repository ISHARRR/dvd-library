/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdcollection.dao;

import com.mthree.dvdcollection.model.Director;
import com.mthree.dvdcollection.model.DvdCollection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author ishar
 */
public class DvdDaoImpl implements DvdDao {

    public DvdCollection dvd;
    public static final String DVD_FILE = "dvdCollection.txt";
    public static final String ID_FILE = "dvdId.txt";
    public static final String DELIMITER = "::";

    private Map<String, DvdCollection> dvds = new HashMap<>();

    @Override
    public DvdCollection addDvd(DvdCollection dvd) throws DvdDaoExceptions {
        loadDvds();
        boolean flag = true;
        Set keys = dvds.keySet();
        Object[] arr = keys.toArray(new String[keys.size()]);
        Arrays.sort(arr);
        int id = 0;
        int keyId = 0;
        String leadingZeros = null;
// input formating
        while (flag) {
            for (int i = 0; i < keys.size(); i++) {
//                uses the set of keys to traverse the dvds and match the ID's to the input 
                if (this.dvds.get(arr[i]).getId().equals(dvd.getId())) {
//                    if a matching id is found the system returns the largest ID and set the new ID to ID+1
                    keyId = Integer.parseInt((String) Collections.max(keys)) - 1;     
                    id = Integer.parseInt((String) this.dvds.get(arr[keyId]).getId()) + 1;
                    leadingZeros = String.format("%05d", id);
                    dvd.setId(leadingZeros);
                    flag = false;
                }
            }
            break;
        }
        DvdCollection newDvd = this.dvds.put(dvd.getId(), dvd);
        writeDvd();
        return newDvd;
    }

    @Override
    public DvdCollection removeDvd(String id) throws DvdDaoExceptions {
        loadDvds();
        DvdCollection removeDvd = null;
        Set keys = dvds.keySet();
        if (keys.contains(id)) {
            removeDvd = this.dvds.remove(id);
        } else {
            System.out.println("ID Not Found");
        }
        writeDvd();
        return removeDvd;
    }

    @Override
    public DvdCollection updateDvd(String id, DvdCollection dvd) throws DvdDaoExceptions {
        loadDvds();
        DvdCollection updateDvd = null;
        Set keys = dvds.keySet();
        if (keys.contains(id)) {
            this.dvds.remove(id);
            updateDvd = this.dvds.put(dvd.getId(), dvd);
        } else {
            System.out.println("\nID to be updated not found");
        }
        writeDvd();
        return updateDvd;
    }

    @Override
    public List<DvdCollection> getAllDvdsForFile() throws DvdDaoExceptions {
        loadDvds();
        return new ArrayList(dvds.values());
    }

    @Override
    public void getAllDvds() throws DvdDaoExceptions {
        loadDvds();
        for (String id : this.dvds.keySet()) {
            String value = this.dvds.get(id).toString();
            System.out.println(value);
        }
    }

    @Override
    public DvdCollection dvdInfo(String id) throws DvdDaoExceptions {
        loadDvds();
        DvdCollection dvdInfo = null;
        Set keys = dvds.keySet();
        if (keys.contains(id)) {
            dvdInfo = this.dvds.get(id);
        } else {
            System.out.println("ID Not Found");
        }
        return dvdInfo;
    }

    @Override
    public DvdCollection searchDvds(String title) throws DvdDaoExceptions {
        loadDvds();
        Set keys = this.dvds.keySet();
//        coverts the set into any array for easier traversion 
        Object[] arrkeys = keys.toArray();

        int id = 0;
        for (int i = 0; i < keys.size(); i++) {
//            while traversing through the array looking for matches against the input and returning the id
            if (this.dvds.get(arrkeys[i]).getTitle().equals(title)) {
                id = i;
            }
        }
        return this.dvds.get(arrkeys[id]);
    }

    private DvdCollection unmarshallDvd(String dvdAsText) {
        String[] dvdtokens = dvdAsText.split(DELIMITER);

        String dvdId = dvdtokens[0];
        String dvdTitle = dvdtokens[1];
        String dvdReleaseDate = dvdtokens[2];
        String dvdMpaa = dvdtokens[3];
        String directorText = dvdtokens[4];
        String[] directorTokens = directorText.split(" ");
        Director dvdDirectorName = new Director(directorTokens[0], directorTokens[1]);
        String dvdStudio = dvdtokens[5];
        String dvdUserReview = dvdtokens[6];

        DvdCollection studentFromFile = new DvdCollection(dvdId, dvdTitle, dvdReleaseDate, dvdMpaa, dvdDirectorName, dvdStudio, dvdUserReview);

        return studentFromFile;
    }

    private void loadDvds() throws DvdDaoExceptions {
        Scanner scanner = null;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdDaoExceptions("Could not load Dvd data into memory.", e);
        }

        String currentLine;

        DvdCollection currentDvd;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            dvds.put(currentDvd.getId(), currentDvd);
        }
        scanner.close();
    }

    private String marshallDvd(DvdCollection advdAsText) {
        String dvdAsText = advdAsText.getId() + DELIMITER;
        dvdAsText += advdAsText.getTitle() + DELIMITER;
        dvdAsText += advdAsText.getReleaseDate() + DELIMITER;
        dvdAsText += advdAsText.getMpaaRating() + DELIMITER;
        dvdAsText += advdAsText.getDirectorName() + DELIMITER;
        dvdAsText += advdAsText.getStudio() + DELIMITER;
        dvdAsText += advdAsText.getUserRating();
        return dvdAsText;
    }

    private void writeDvd() throws DvdDaoExceptions {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdDaoExceptions("Could not save Dvd data.", e);
        }

        String dvdAsText;
        List<DvdCollection> dvdList = this.getAllDvdsForFile();
        for (DvdCollection currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}
