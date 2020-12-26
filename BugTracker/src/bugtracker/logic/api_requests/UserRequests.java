/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugtracker.logic.api_requests;
import com.fasterxml.jackson.core.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

/**
 *
 * @author ruizi
 */
public class UserRequests {

    public static void loginRequest(String user, String password) throws MalformedURLException, IOException{
        URL url = new URL("http://localhost/GPS_BT/index.php/user/login");
        String params = "uName="+user+"&pass="+password;
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        OutputStream out = con.getOutputStream();
        out.write(params.getBytes());
        out.flush();
        out.close();
        if(con.getResponseCode()== HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
           ObjectMapper mapper = new ObjectMapper();
        }
        
    }
}
