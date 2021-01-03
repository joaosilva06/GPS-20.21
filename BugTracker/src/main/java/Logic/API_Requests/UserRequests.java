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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRequests {

    public static User registar(String username, String password, String email) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/update/user/register");
        String params = "uName="+username+"&email="+email+"&pass="+password;

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty( "charset", "utf-8");
        con.setRequestProperty( "Content-Length", Integer.toString( params.getBytes(StandardCharsets.UTF_8).length));
        try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
            wr.write(params.getBytes(StandardCharsets.UTF_8) );
        }
        InputStream in = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        try {
            User rUser = mapper.readValue(in, User.class);
            return rUser;
        } catch (Exception e){
            throw new IOException(e.getMessage());
        }finally{
            in.close();
        }
    }


    public static User login(String username, String password) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/get/user/login");
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
        try {
            User rUser = mapper.readValue(in, User.class);
            return rUser;
        } catch (Exception e){
            throw new IOException(e.getMessage());
        }finally{
            in.close();
        }
    }

    public static boolean logoff(String username, String password) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/get/user/logoff");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty( "charset", "utf-8");
        InputStream in = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String resp = mapper.readValue(in, String.class);
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static List<Project> projects() throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/get/user/projects");
        String params = "";

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
        try {
            List<Project> rProjs = mapper.readValue(in,  mapper.getTypeFactory().constructCollectionType(List.class, Project.class));
            return rProjs;
        } catch (Exception e){
            return null;
        }finally{
            in.close();
        }
    }

    public static boolean resetMail(String email) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/get/user/reset");
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
        try {
            String resp = mapper.readValue(in, String.class);
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static String rename(String newName) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/index.php/update/user/rename");
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
        try {
            String resp = mapper.readValue(in, String.class);
            return resp;
        }catch (Exception e){
            return "Converting error";
        }finally{
            in.close();
        }
    }

    public static boolean repass(String newPass) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/index.php/update/user/pass");
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
        try {
            String resp = mapper.readValue(in, String.class);
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static User search(String search) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/index.php/get/user/search");
        String params = "search="+search;

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
        try {
            User rUser = mapper.readValue(in, User.class);
            return rUser;
        } catch (Exception e){
            throw new IOException(e.getMessage());
        }finally{
            in.close();
        }
    }

}
