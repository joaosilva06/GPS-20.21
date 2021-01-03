package Logic.Managements;

import Logic.API_Requests.ProjectRequests;
import Logic.Exceptions.APIResponseException;
import Logic.Project;
import Logic.User;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UserProjects {

    private final List<Project> projs;
    private User usr;
    private int projectsId = 0;

    public UserProjects(User usr){
        this.usr = usr;
        this.projs = null;
    }

    public List<Project> getProjs() {
        return projs;
    }

    public void projectBugs(int pos){
        int usr_id = usr.getId();
        int id_proj = projs.get(pos).getProjectId();
        try {
            projs.get(pos).setBugs(ProjectRequests.projectBugs(id_proj));
        }catch (IOException e){
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }

    public void projectModules(int pos){
        int usr_id = usr.getId();
        int id_proj = projs.get(pos).getProjectId();
        try {
            projs.get(pos).setModules(ProjectRequests.projectModules(id_proj));
        }catch (IOException e){
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }

    public void addProject(String project_name){
        int usr_id = usr.getId();
        Date date=java.util.Calendar.getInstance().getTime();
        Project p = new Project(projectsId++,project_name,date);
        try {
            if(ProjectRequests.addProject(project_name)) {
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
            if(ProjectRequests.removeProject(id_proj))
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
            if(ProjectRequests.newModule(id_proj, moduleName)){
                projs.get(pos).addModule(moduleName);
            }
        }catch (IOException e){
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }



}
