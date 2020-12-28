package Logic.API_Requests;

public class ProjectRequests {

    public static Bug projectBugs(int id, int project_id) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/project/bugs");
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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        if(resp.hasError())
            return null;
        else{
            return (List<Bug>) resp.getMsg();
        }
    }

    public static User projectMembers(int id, int project_id) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/project/members");
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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        if(resp.hasError())
            return null;
        else{
            return (List<User>) resp.getMsg();
        }
    }

    public static Modules projectModules(int id, int project_id) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/project/modules");
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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        if(resp.hasError())
            return null;
        else{
            return (List<Modules>) resp.getMsg();
        }
    }

    public static boolean addProject(int id, int project_name) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/project/modules");
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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        return resp.hasError();
    }

    public static boolean removeProject(int id, int project_id) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/project/modules");
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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        return resp.hasError();
    }

    public static boolean newModule(int id, int project_id, int moduleName) throws IOException {
        URL url = new URL("http://localhost/GPS_BT/get/project/modules");
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
        APIResponse resp = mapper.readValue(in, APIResponse.class);
        in.close();
        return resp.hasError();
    }
}
