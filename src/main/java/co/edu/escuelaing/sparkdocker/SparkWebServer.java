package co.edu.escuelaing.sparkdocker;
 
import static spark.Spark.*;
import co.edu.escuelaing.sparkdocker.services.MessageService;

 
/**
  * web server usoing Sparkweb
  * @author Ricar
  */

 public class SparkWebServer {
     public static void main(String... args){
        staticFileLocation("/public");
        port(getPort());
        get("hello", (req,res) -> "Hello Docker! :)");
        MessageService sparkMessageService = new MessageService();
     }
 
    private static int getPort() {
         if (System.getenv("PORT") != null) {
             return Integer.parseInt(System.getenv("PORT"));
         }
         return 4567;
     }

 }