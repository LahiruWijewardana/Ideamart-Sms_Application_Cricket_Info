package httpRequests;

import util.Utility;
import model.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.io.OutputStream;

public class RegisterRequest {
	
	public void registerUser(Users user){
        try {
            URL obj = new URL(Utility.registerUserUrl);
            URLConnection con = obj.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);

            Gson gson = new GsonBuilder().create();

            System.out.println(gson.toJson(user));

            byte[] out = gson.toJson(user).getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            System.out.println("\nSending 'POST' request to URL : " + Utility.registerUserUrl);

            OutputStream os = http.getOutputStream();
            os.write(out);

            System.out.println(http.getResponseCode());

            InputStream inputStream = http.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String output = null;

            while ((output = reader.readLine()) != null) {

                System.out.println(output);
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
