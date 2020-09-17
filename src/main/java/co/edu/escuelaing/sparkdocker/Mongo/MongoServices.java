package co.edu.escuelaing.sparkdocker.Mongo;

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

    /**
     * Constructor
     * @param host host de la base de datos.
     * @param port puerto de la base de datos.
     */
    public MongoServices(String host, int port) {
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
        Document document = new Document("user", user).append("content", message);

        // Inserting document into the collection
        collection.insertOne(document);
    }

    /**
     * Obtiene los mensajes de la base de datos y los convierte a un Json.
     * @return La información de los mensajes en formato JSON.
     */
    public JsonObject getMessages() {
        MongoCollection<Document> collection = database.getCollection("messages");
        FindIterable<Document> iterDoc = collection.find();
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
            jArray.add(jsonObject);
        }
        mensajes.add("Messages", jArray);
		return mensajes;
	}
}
