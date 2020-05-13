package tw.org.iii.lipin.foodsharing.FoodinfoGiver;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GiverMessgingService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Method to send Notifications from server to client end.

        String TakerToken = request.getParameter("TakerToken");
        String UserToken = request.getParameter("UserToken");
        String username = request.getParameter("username");
        String foodname = request.getParameter("foodname");


        final String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
        final String AUTH_KEY_FCM = "AAAA_SQ0U_k:APA91bFUA2vxOA6VCNPkKrXqaB_rifXxdRomgRzyH-uu2DxERnwdC-QIRHOmWrEg1ZoYUle4V2Eor1mWv9Rh8VZ5qFOVX7rL24_1KehNb34P_hPZZN8QxAZ0l1LiRuijmgnr9c-Ki2D5";
        System.out.println(TakerToken);

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
            json.put("to",TakerToken.trim());
            JSONObject info = new JSONObject();
            info.put("title", "來自您想索取的一則"+foodname+"消息"); // Notification title
            info.put("body", username+"已批准你加入排隊,請您再查看聊天室訊息"); // Notification body
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
