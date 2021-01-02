package Logic.API_Requests;

import Logic.Bug;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BugRequests {
    public static Bug getBug(int proj, int bug) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/bug/request");
        String params = "projId=" + proj + "&bugId=" + bug;

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", Integer.toString(params.getBytes(StandardCharsets.UTF_8).length));
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write(params.getBytes(StandardCharsets.UTF_8));
        }
        InputStream in = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Bug rBug = mapper.readValue(in, Bug.class);
            return rBug;
        } catch (Exception e){
            return null;
        }finally{
            in.close();
        }

    }

    public static boolean addBug(String desc, String title, int projId, Integer modId, int type, int prio) throws IOException {//ver
        URL url = new URL("http://localhost/GPS_BT/get/bug/add");
        String params ="bugTitlte="+title+"&bugDescription="+desc+
                "&bugModule="+modId +"&bugType="+type+"&bugPriority="+ prio +
                "&bugProject=" + projId;
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

    public static boolean editBug(String desc, String title, int bugId) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/bug/edit");
        String params = "newBugDescription="+desc+"&newTitle="+title+"&bugI="+bugId;

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

    public static boolean solve(int bugId, int proj) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/bug/solve");
        String params = "idBug="+bugId+"&project="+proj;

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

    public static boolean unsolve(int bugId) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/bug/unsolve");
        String params = "idBug="+bugId;

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
}
