/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ae.smartek.cosmosmigrator.dto;

import ae.smartek.cosmosmigrator.config.MongoConfig;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

/**
 *
 * @author houssem
 */
public class MongoDTO {

    public MongoIterable<String> getAllDatabases() {
        return MongoConfig.getMongoClientInstance().listDatabaseNames();
    }

    public MongoIterable<String> getAllCollectionsInDB(String db) {
        return MongoConfig.getMongoClientInstance().getDatabase(db).listCollectionNames();
    }

    public void insertDocumen(Document doc, String db, String c) {
        MongoConfig.getMongoClientInstance().getDatabase(db)
                .getCollection(c).insertOne(doc);

    }

}
