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

public class ProjectRequests{

    public static List<Bug> projectBugs(int project_id, int id) throws IOException, APIResponseException {

        URL url = new URL("http://localhost/GPS_BT/index.php/get/project/bugs");

        String params = "id="+id+"&projId="+project_id;

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


    public static List<User> projectMembers(int project_id, int id) throws IOException, APIResponseException {

        URL url = new URL("http://localhost/GPS_BT/index.php/get/project/members");

        String params = "id="+id+"&projId="+project_id;

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

    public static List<Module> projectModules(int project_id, int id) throws IOException, APIResponseException {

        URL url = new URL("http://localhost/GPS_BT/index.php/get/project/modules");

        String params = "id="+id+"&projId="+project_id;

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

    public static boolean addProject(String project_name,int id) throws IOException, APIResponseException {

        URL url = new URL("http://localhost/GPS_BT/index.php/Update/project/add");

        String params = "id="+id+"&projName="+project_name;

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
            if(resp.equals("no rows selected") || resp.equals("Incomplete data")){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean removeProject(int project_id, int id) throws IOException, APIResponseException {

        URL url = new URL("http://localhost/GPS_BT/index.php/Update/project/remove");//nao existe, mas devia

        String params = "id="+id+"&projID="+project_id;

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
            if(resp.equals("Erro ao remover o projeto")){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean newModule(int project_id, String moduleName, int id) throws IOException, APIResponseException {

        URL url = new URL("http://localhost/GPS_BT/index.php/Update/module/new");

        String params = "id="+id+"&projID="+project_id+"&moduleName="+moduleName;

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
            if(resp.equals("Error adding new module") || resp.equals("register incompleto")){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean addMember(int role, int user, int proj, int id) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/project/addMember");
        String params = "id="+id+"&role="+role+"&user="+user+"&project="+proj;

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
            if(resp.equals("no rows selected") || resp.equals("access denied") || resp.equals("Incomplete data")){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean changeRole(int role, int user, int proj,int id) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/project/role");
        String params = "id="+id+"&role="+role+"&user="+user+"&project="+proj;

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
            if(resp.equals("no rows selected") || resp.equals("access denied") || resp.equals("Incomplete data")){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean removeMember(int user, int proj, int id) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/project/removeMember");
        String params = "id="+id+"&user="+user+"&project="+proj;

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
            if(resp.equals("no rows selected") || resp.equals("access denied") || resp.equals("Incomplete data")){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    //remove module
    public static boolean removeModule(int modId,int id) throws IOException, APIResponseException {
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/module/remove");
        String params = "id="+id+"&idModule="+modId;

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
            if(resp.equals("Deleted")){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }
}
