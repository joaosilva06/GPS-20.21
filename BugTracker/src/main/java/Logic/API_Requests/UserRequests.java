package Logic.API_Requests;

import Logic.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UserRequests {
    public static User registar(String username, String password, String email) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/user/register");
        String params = "uName="+username+"&email="+email+"?pass="+password;

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty( "charset", "utf-8");
        con.setRequestProperty( "Content-Length", Integer.toString( params.getBytes(StandardCharsets.UTF_8).length));
        try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
            wr.write( params.getBytes(StandardCharsets.UTF_8) );
        }

        InputStream in = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(in, User.class);
        return user;
    }
}
