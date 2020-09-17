package co.edu.escuelaing.sparkdocker.services;

import co.edu.escuelaing.sparkdocker.Mongo.MongoServices;
import static spark.Spark.*;
import com.google.gson.JsonObject;
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
        mongoServices = new MongoServices("localhost", 27017);
    }
    /**
     * Asocia metodos a rutas en spark.
     */
    private void loadToSpark() {
        get("/api/v1/setMessage", (req, res) -> insertMessage(req, res));
    }
    /**
     * Inserta un mensaje en la base de datos.
     * @param req Request
     * @param res Response
     * @return Todos los mensajes en formato JSON
     */
    public JsonObject insertMessage(Request req, Response res) {
        res.type("application/json");
        String user = req.queryParams("user");
        String message = req.queryParams("message");
        mongoServices.insertMessage(user, message);
        return getMessages();
    }
    /**
     * Pide todos los mensajes en formato JSON.
     * @return
     */
    private JsonObject getMessages() {
        return mongoServices.getMessages();
    }
}
