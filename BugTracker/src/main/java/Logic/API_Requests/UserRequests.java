package Logic.API_Requests;

import Logic.Exceptions.APIResponseException;
import Logic.Project;
import Logic.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserRequests {
    public static User registar(String username, String password, String email, String name) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/update/user/register");
        String params = "uName="+username+"&email="+email+"&pass="+password;

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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        if(!resp.hasError()) {
            User user = (User) resp.getMsg();
            return user;
        }
        return null;
    }

    public static boolean login(String username, String password) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/user/login");
        String params = "uName="+username+"&pass="+password;

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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        return resp.hasError();
    }

    public static boolean logoff(String username, String password) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/user/logoff");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty( "charset", "utf-8");
        InputStream in = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        return resp.hasError();
    }

    public static List<Project> projects(String username, String password) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/user/projects");
        String params = "uName="+username+"&pass="+password;

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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        if(resp.hasError())
            return null;
        else{
            return (List<Project>) resp.getMsg();
        }
    }

    public static boolean resetPass(String email) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/user/reset");
        String params = "mail="+email;

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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        return resp.hasError();
    }

    public static String rename(String newName) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/update/user/rename");
        String params = "newName="+newName;

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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        if(resp.hasError())
            throw new APIResponseException((String)resp.getMsg());
        return (String)resp.getMsg();
    }

    public static boolean repass(String newPass) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/update/user/pass");
        String params = "newPass="+newPass;

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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        return resp.hasError();
    }

}
