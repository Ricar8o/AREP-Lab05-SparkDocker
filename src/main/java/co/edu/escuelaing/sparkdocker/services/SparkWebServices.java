package co.edu.escuelaing.sparkdocker.services;

import static spark.Spark.*;

import co.edu.escuelaing.sparkdocker.Mongo.MongoServices;
import spark.Request;
import spark.Response;

/**
 * @author Ricar8o
 * @version 1.0
 */
public class SparkWebServices {

    MongoServices mongoServices;

    public SparkWebServices(){
        loadToSpark();
        mongoServices = new MongoServices("localhost",27017);
    }

    private void loadToSpark() {
        get("/api/v1/setMessage", (req,res) -> insertMessage(req,res));
    }

    public String insertMessage(Request req, Response res) {
        String user = req.queryParams("user");
        String message = req.queryParams("message");
        mongoServices.insertMessage(user, message);
        return getMessages();
    }

    private String getMessages() {
        return mongoServices.getMessages();
    }
}
