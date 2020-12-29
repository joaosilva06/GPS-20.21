package Logic.Managements;

import Logic.*;
import Logic.API_Requests.ProjectRequests;
import Logic.API_Requests.UserRequests;
import Logic.Exceptions.APIResponseException;
import Logic.Module;

import java.util.Date;
import java.util.List;

import java.io.IOException;

public class UserProjects {

    private final List<Project> projs;
    private User usr;
    private int projectsId = 0;

    public UserProjects(User usr){
        this.usr = usr;
        this.projs = null;
    }

    public void projectBugs(){


    }

    public void projectModules(){


    }

    public void addProject(String project_name){
        int usr_id = usr.getId();
        Date date=java.util.Calendar.getInstance().getTime();
        Project p = new Project(projectsId++,project_name,date);
        try {
            if(ProjectRequests.addProject(usr_id, project_name)) {
                projs.add(p);
            }else{
                projectsId--;
            }

        }catch (IOException e){
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }

    public void removeProject(int pos){
        int id_proj = projs.get(pos).getProjectId();
        try {
            if(ProjectRequests.removeProject(usr.getId(), id_proj))
                projs.remove(pos);
        } catch (IOException e) {
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }

    public void newModule(int pos, String moduleName){
        int usr_id = usr.getId();
        int id_proj = projs.get(pos).getProjectId();
        try {
            if(ProjectRequests.newModule(usr_id,id_proj, moduleName)){
                projs.get(pos).addModule(moduleName);
            }
        }catch (IOException e){
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }



}
