package co.edu.escuelaing.sparkdocker.mongo;

import java.util.Date;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
    private long maxMessages;

    /**
     * Constructor
     * @param host host de la base de datos.
     * @param port puerto de la base de datos.
     */
    public MongoServices(String host, int port, long maxM) {
        this.maxMessages = maxM;
        // Creating a Mongo client
        mongoClient = new MongoClient(host, port);
        database = mongoClient.getDatabase("arepDockerDB");
    }
    /**
     * Inserta un mensaje en la base de datos Mongo.
     * @param user persona que envio el mensaje.
     * @param message Contenido del mensaje.
     */
    public void insertMessage(String user, String message) {
        MongoCollection<Document> collection = database.getCollection("messages");
        Date fechaActual = new Date(); 
        Document document = new Document("user", user).append("content", message).append("date", fechaActual);

        // Inserting document into the collection
        collection.insertOne(document);
    }

    /**
     * Obtiene los mensajes de la base de datos y los convierte a un Json.
     * @return La información de los mensajes en formato JSON.
     */
    public JsonObject getMessages() {
        MongoCollection<Document> collection = database.getCollection("messages");
        int num = (int) (collection.countDocuments() - maxMessages);
        FindIterable<Document> iterDoc = collection.find().skip(num);
        // Getting the iterator
        Iterator<Document> it = iterDoc.iterator();
        JsonArray jArray = new JsonArray();
        JsonObject mensajes = new JsonObject();
		while (it.hasNext()) {
            JsonObject jsonObject = new JsonObject();
            Document doc = it.next();
            jsonObject.addProperty("Id", doc.get("_id").toString());
            jsonObject.addProperty("User", doc.get("user").toString());
            jsonObject.addProperty("Message", doc.get("content").toString());
            jsonObject.addProperty("Date", doc.get("date").toString());
            jArray.add(jsonObject);
        }
        mensajes.add("Messages", jArray);
		return mensajes;
	}
}
