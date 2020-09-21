package co.edu.escuelaing.sparkdocker.services;

import co.edu.escuelaing.sparkdocker.mongo.MongoServices;
import static spark.Spark.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Request;
import spark.Response;

/**
 * @author Ricar8o
 * @version 1.0
 */
public class MessageService {

    MongoServices mongoServices;

    /**
     * Constructor
     */
    public MessageService() {
        loadToSpark();
        mongoServices = new MongoServices("102.24.0.5", 27017, 10);
    }

    /**
     * Asocia metodos a rutas en spark.
     */
    private void loadToSpark() {
        post("/api/v1/setMessage", (req, res) -> insertMessage(req, res));
        get("/api/v1/getMessages", (req, res) -> getMessages(req, res));
    }

    /**
     * Inserta un mensaje en la base de datos.
     * 
     * @param req Request
     * @param res Response
     * @return respuesta de la inserción.
     */
    public String insertMessage(Request req, Response res) {
        JsonObject json = (JsonObject) JsonParser.parseString(req.body());
        String user = json.get("user").getAsString();
        String message = json.get("message").getAsString();
        mongoServices.insertMessage(user, message);
        return "ok";
    }
    
    /**
     * Pide todos los mensajes en formato JSON.
     * 
     * @param req Request
     * @param res Response
     * @return Todos los mensajes en formato JSON
     */
    public JsonObject getMessages(Request req, Response res) {
        res.type("application/json");
        return mongoServices.getMessages();
    }
}
