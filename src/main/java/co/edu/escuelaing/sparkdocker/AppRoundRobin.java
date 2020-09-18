package co.edu.escuelaing.sparkdocker;

import static spark.Spark.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Request;
import spark.Response;

/**
 * @author Ricar8o
 * @version 1.0
 */
public class AppRoundRobin {

    private static List<String> ips;
    private static int flag;

    public static void main(String... args) {
        ips = new ArrayList<String>();
        loadIps(args);
        flag = 0;
        staticFileLocation("/public");
        port(getPort());
        get("hello", (req, res) -> "Hello Docker! :)");
        post("/api/v1/setMessage", (req, res) -> insertMessage(req, res));
        get("/api/v1/getMessages", (req, res) -> getMessages(req, res));
    }

    /**
     * Este metodo agrega las ips de las maquinas que va a consultar
     * 
     * @param args lista de ips a los que se puede consultar
     */
    public static void loadIps(String[] args) {
        for (String s : args) {
            ips.add(s);
        }
    }

    /**
     * Inserta un mensaje en la base de datos.
     * 
     * @param req Request
     * @param res Response
     * @return respuesta de la inserción.
     */
    public static String insertMessage(Request req, Response res) {
        String url = "http://" + ips.get(flag) + "/api/v1/setMessage";
        flag = (flag + 1) % ips.size();
        String cadena = null;
		try {
			cadena = makeInsertMessage(url, req, res);
		} catch (IOException e) {
			res.status(404);
		}
        return cadena;
    }

    /**
     * Pide todos los mensajes en formato JSON.
     * @param req Request
     * @param res Response
     * @return Todos los mensajes en formato JSON
     */
    public static JsonObject getMessages(Request req, Response res) {
        String url = "http://" + ips.get(flag) + "/api/v1/getMessages";
        flag = (flag + 1) % ips.size();
        JsonObject json = null;
        try {
            json = makeGetMessages(url);
            res.type("application/json");
        } catch (Exception e) {
            res.status(404);
        }
        return json;
    }
    
    /**
     * Metodo que envia el mensaje a alguna de las "maquinas" conectada a la base de datos.
     * @param url url de la maquina a que enviara la petición.
     * @param req Request
     * @param res Response
     * @return respuesta del la solicitud remota
     * @throws IOException En el caso de que haya un problema de conexión o con la Url.
     */
    private static String makeInsertMessage(String url,Request req, Response res) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        String jsonInputString = req.body();
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
    /**
     * Metodo que pide todos los mensajes en la base de datos.
     * @param url url de la maquina a que enviara la petición.
     * @return respuesta del la solicitud remota
     * @throws IOException En el caso de que haya un problema de conexión o con la Url.
     */
    private static JsonObject makeGetMessages(String url) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
            in.close();
            JsonObject json = (JsonObject) JsonParser.parseString(response.toString());
            return json;
		} else {
			return (JsonObject) JsonParser.parseString("GET request not worked");
		}
    }

    /**
     * Mira si ya hay un puerto definido, si no entonces usara el puerto 4567.
     * @return Puerto del servicio.
     */
    private static int getPort() {
         if (System.getenv("PORT") != null) {
             return Integer.parseInt(System.getenv("PORT"));
         }
         return 4567;
     }
}
