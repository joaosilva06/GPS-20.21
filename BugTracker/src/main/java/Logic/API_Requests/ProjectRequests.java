package Logic.API_Requests;

import Logic.Bug;
import Logic.Exceptions.APIResponseException;
import Logic.Module;
import Logic.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ProjectRequests {

    public static List<Bug> projectBugs(int project_id) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/get/project/bugs");
        String params = "projId="+project_id;

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
            List<Bug> rBug = mapper.readValue(in,  mapper.getTypeFactory().constructCollectionType(List.class, Bug.class));
            return rBug;
        } catch (Exception e){
            return null;
        }finally{
            in.close();
        }
    }


    public static List<User> projectMembers(int project_id) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/get/project/members");
        String params = "projId="+project_id;

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
            List<User> rUsers = mapper.readValue(in,  mapper.getTypeFactory().constructCollectionType(List.class, User.class));
            return rUsers;
        } catch (Exception e){
            return null;
        }finally{
            in.close();
        }
    }

    public static List<Module> projectModules(int project_id) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/get/project/modules");
        String params = "projId="+project_id;

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
            List<Module> rMods = mapper.readValue(in,  mapper.getTypeFactory().constructCollectionType(List.class, Module.class));
            return rMods;
        } catch (Exception e){
            return null;
        }finally{
            in.close();
        }
    }

    public static boolean addProject(String project_name) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/Update/project/add");
        String params = "projName="+project_name;

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

    public static boolean removeProject(int project_id) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/get/project/remove");//nao existe, mas devia
        String params = "projID="+project_id;

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

    public static boolean newModule(int project_id, String moduleName) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/update/module/new");
        String params = "projID="+project_id+"&moduleName="+moduleName;

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

    public static boolean addMember(int role, int user, int proj) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/update/project/addMember");
        String params = "role="+role+"&user="+user+"&project="+proj;

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

    public static boolean changeRole(int role, int user, int proj) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/update/project/role");
        String params = "role="+role+"&user="+user+"&project="+proj;

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

    public static boolean removeMember(int user, int proj) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/update/project/role");
        String params = "user="+user+"&project="+proj;

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
