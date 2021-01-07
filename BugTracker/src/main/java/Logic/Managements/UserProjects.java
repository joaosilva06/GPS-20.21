package Logic.Managements;

import Logic.API_Requests.ProjectRequests;
import Logic.API_Requests.UserRequests;
import Logic.Exceptions.APIResponseException;
import Logic.Module;
import Logic.Project;
import Logic.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserProjects {

    private List<Project> projs;
    private User usr;
    private int projectsId = 0;

    public UserProjects(User usr){
        this.usr = usr;
        this.projs = null;
    }

    public List<Project> getProjs() {
        try {
            //ver user id
            projs = UserRequests.projects(1);
            return projs;
        }catch (IOException e){
            //uma callback para a interface
        }
        return null;

    }

    public void setProjs(List<Project> proj){
        this.projs = proj;
    }

    public void projectBugs(int pos){
        int usr_id = usr.getId();
        int id_proj = projs.get(pos).getProjectId();
        try {
            //ver user id
            projs.get(pos).setBugs(ProjectRequests.projectBugs(id_proj,1));
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
            //ver user id
            projs.get(pos).setModules(ProjectRequests.projectModules(id_proj,1));
        }catch (IOException e){
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }

    public void projectMembers(int pos){
        int id_proj = projs.get(pos).getProjectId();
        try {
            //ver user id
            projs.get(pos).setMembers(ProjectRequests.projectMembers(id_proj,1));
        }catch (IOException e){
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }

    public Project addProject(String project_name) throws Exception {
        int usr_id = usr.getId();
        Date date=java.util.Calendar.getInstance().getTime();
        Project p = new Project(projectsId++,project_name,date);
        try {
            //ver user id
            if(ProjectRequests.addProject(project_name,1)) {
                projs.add(p);
            }else{
                projectsId--;
            }

        }catch (IOException | APIResponseException e) {
            throw new Exception(e.getMessage());
        }

        return p;
    }

    public void removeProject(int pos){
        int id_proj = projs.get(pos).getProjectId();
        try {
            //ver user id
            if(ProjectRequests.removeProject(id_proj,1))
                projs.remove(pos);
        } catch (IOException e) {
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }

    public void newModule(int pos, String moduleName) throws Exception {
        int usr_id = usr.getId();
        int id_proj = projs.get(pos).getProjectId();
        try {
            //ver user id
            if(ProjectRequests.newModule(id_proj, moduleName,1)){
                projs.get(pos).addModule(moduleName);
            }
        }catch (IOException | APIResponseException e){
            throw new Exception(e.getMessage());
        }
    }

    //remove module
    public void removeModule(int posModule, int posProject) throws Exception {
        List<Module> modules = projs.get(posProject).getModules();
        int id_mod = modules.get(posModule).getId();
        try {
            //ver user id
            if(ProjectRequests.removeModule(id_mod,1)){
                projs.get(posProject).getModules().remove(posModule);
            }
        }catch (IOException | APIResponseException e){
            throw new Exception(e.getMessage());
        }
    }


}
