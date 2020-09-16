package co.edu.escuelaing.sparkdocker;
 
import static spark.Spark.*;

import co.edu.escuelaing.sparkdocker.services.SparkWebServices;
 
 
/**
  * A simple web server using Sparkweb
  * @author dnielben
  */

 public class SparkWebServer {
     public static void main(String... args){
        staticFileLocation("/public");
        port(getPort());
        get("hello", (req,res) -> "Hello Docker! :)");
        SparkWebServices sparkWebServices = new SparkWebServices();
     }
 
    private static int getPort() {
         if (System.getenv("PORT") != null) {
             return Integer.parseInt(System.getenv("PORT"));
         }
         return 4567;
     }

 }