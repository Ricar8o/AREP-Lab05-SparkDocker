package co.edu.escuelaing.sparkdocker.Mongo;

import java.util.Iterator;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * @author Ricar8o
 * @version 1.0
 */
public class MongoServices {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoServices(String host, int port){
        // Creating a Mongo client 
        mongoClient = new MongoClient( host, port); 
        database = mongoClient.getDatabase("arepDockerDB"); 
    }

    public void insertMessage( String user, String message){
        MongoCollection<Document> collection = database.getCollection("messages");
        Document document = new Document("", user)
        .append("content", message );
        
        //Inserting document into the collection
        collection.insertOne(document);
    }

	public String getMessages() {
        MongoCollection<Document> collection = database.getCollection("messages");
        FindIterable<Document> iterDoc = collection.find();
		int i = 1;
		// Getting the iterator
        Iterator it = iterDoc.iterator();
        String cadena = "";
		while (it.hasNext()) {
            Document doc = (Document) it.next();
            System.out.println(doc.toJson());
            cadena = doc.toJson();
			i++;
		}
		return cadena;
	}
}
