/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ae.smartek.cosmosmigrator.service;

import ae.smartek.cosmosmigrator.dto.MongoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author houssem
 */
public class CosmosMigratorService {

    private MongoDTO mdto;

    public CosmosMigratorService() {
        this.mdto = new MongoDTO();
    }

    private void insertDocument(Document doc, String db, String c) {
        this.mdto.insertDocumen(doc, db, c);
    }

    public void startTest2() {
        List<String> nss = getTestingCollections();
        int i;
        String[] tab;
        long wait;
//        Document doc = new Document("name", "test");
        while (true) {
            i = (int) (Math.random() * (nss.size() - 1));
            tab = nss.get(i).split("\\.");
            Document doc = new Document("name", "test");
            insertDocument(doc, tab[0], tab[1]);
            wait = (long) (Math.random() * 1000);
            try {
                Thread.sleep(wait);
            } catch (InterruptedException ex) {
                Logger.getLogger(CosmosMigratorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private List<String> getTestingCollections() {
        String databases = System.getenv("MONGODB_DATABSES");
        String namespaces = System.getenv("MONGODB_NAMESPACES");
        List<String> nss = new ArrayList<>();
        if ((namespaces != null) && (!namespaces.equals(""))) {
            String[] tab = namespaces.split(",");
            if (tab.length > 0) {
                for (String ns : tab) {
                    nss.add(ns.trim());
                }
            } else {
                System.out.println("MONGODB_NAMESPACES entry is not valid. MONGODB_NAMESPACES should contain the namespaces (database.collection) to be migrated seperated by , ");
            }
        } else if ((databases != null) && (!databases.equals(""))) {
            String[] tab = databases.split(",");
            if (tab.length > 0) {
                for (String db : tab) {
                    mdto.getAllCollectionsInDB(db.trim()).forEach((c) -> {
                        nss.add(db.trim().concat(".").concat(c));
                    });
                }

            } else {
                System.out.println("MONGODB_DATABSES entry is not valid. MONGODB_DATABSES should contain the databses names  to be migrated seperated by , ");
            }

        } else {
            mdto.getAllDatabases().forEach((db) -> {
                mdto.getAllCollectionsInDB(db).forEach((c) -> {
                    nss.add(db.concat(".").concat(c));
                });
            });
        }
        return nss;
    }

    public void startTest() {

        List<String> nss = new ArrayList<>();
        String sdb = "sample_mflix";
        mdto.getAllCollectionsInDB(sdb).forEach((c) -> {
            nss.add(sdb.concat(".").concat(c));
        });
//        mdto.getAllDatabases().forEach((db) -> {
//            
//            mdto.getAllCollectionsInDB(db).forEach((c) -> {
//                nss.add(db.concat(".").concat(c));
//            });
//        });
        int i;
        String[] tab;
        long wait;
//        Document doc = new Document("name", "test");
        while (true) {
            i = (int) (Math.random() * (nss.size() - 1));
            tab = nss.get(i).split("\\.");
            Document doc = new Document("name", "test");
            insertDocument(doc, tab[0], tab[1]);
            wait = (long) (Math.random() * 1000);
            try {
                Thread.sleep(wait);
            } catch (InterruptedException ex) {
                Logger.getLogger(CosmosMigratorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
