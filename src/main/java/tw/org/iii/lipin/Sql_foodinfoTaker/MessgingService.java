package tw.org.iii.lipin.Sql_foodinfoTaker;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessgingService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Method to send Notifications from server to client end.

        String GiverToken = request.getParameter("GiverToken");
        String UserToken = request.getParameter("UserToken");

        final String AUTH_KEY_FCM = UserToken;
         final String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

        // userDeviceIdKey is the device id you will query from your database
        try {
            String authKey = AUTH_KEY_FCM; // You FCM AUTH key
            String FMCurl = API_URL_FCM;

            URL url = new URL(FMCurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization","key="+authKey);
            conn.setRequestProperty("Content-Type","application/json");

            JSONObject json = new JSONObject();
            json.put("to",GiverToken.trim());
            JSONObject info = new JSONObject();
            info.put("title", "Notificatoin Title"); // Notification title
            info.put("body", "Hello Test notification"); // Notification body
            json.put("notification", info);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            conn.getInputStream();
        }catch (Exception e){
            System.out.println(e.toString());
        }

        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
