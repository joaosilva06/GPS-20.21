package Logic.API_Requests;

import Logic.Bug;
import Logic.Exceptions.APIResponseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BugRequests {
    public static Bug getBug(int bug, int userId) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/get/bug/request");
        String params = "id="+userId+"&bugId=" + bug;

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

    public static boolean addBug(String desc, String title, int projId, Integer modId, int type, int prio, int userId) throws IOException {//ver
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/bug/addBugProject");
        String params = "id="+userId+"&bugTitlte="+title+"&bugDescription="+desc+
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
            if(resp.equals("Success")){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean editBug(String desc, String title, int bugId, int userId) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/bug/edit");
        String params = "id="+userId+"&newBugDescription="+desc+"&newTitle="+title+"&bugI="+bugId; //não da match com os isset do php

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
            if(resp.equals("Changed")){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean solve(int bugId, int proj, int userId) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/bug/solve");
        String params = "id0"+userId+"&idBug="+bugId+"&project="+proj;

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
            if(resp.equals("Bug Solved")){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean unsolve(int bugId, int userId) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/bug/unsolve");
        String params = "id="+userId+"&idBug="+bugId; //no php pede tambem "projeto"

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
            if(resp.equals("Bug UnSolved")){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    public static boolean markSolving(int bugId, int userId, int proj) throws IOException { //precisa projectId
        URL url = new URL("http://localhost/GPS_BT/index.php/Update/bug/progress");
        String params = "idBug="+bugId+"&id="+userId+"&project="+proj;

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
            if(resp.equals("Bug marked solving")){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }finally{
            in.close();
        }
    }

    //check
    public static String bugCheck(int bugId) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/index.php/get/bug/check"); //é util? não
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
            return resp;
        }catch (Exception e){
            return "Converting error";
        }finally{
            in.close();
        }
    }
}
