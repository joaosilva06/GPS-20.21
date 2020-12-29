package Logic;

import java.util.Date;
import java.util.List;

public class Project {
    private int projectId;
    private String name;
    private Date dateCreate;
    private List<User> members;
    private List<Module> modules;
    private List<Bug> bugs;

    private int moduleId = 0;


    public Project(int projectId, String name, Date dateCreate) {
        this.projectId = projectId;
        this.name = name;
        this.dateCreate = dateCreate;
    }

    public Project(){}

    public Project(int projectId, String name, Date dateCreate, List<User> members, List<Module> modules, List<Bug> bugs) {
        this.projectId = projectId;
        this.name = name;
        this.dateCreate = dateCreate;
        this.members = members;
        this.modules = modules;
        this.bugs = bugs;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void addModule (String moduleName){
        //como não estamos a buscar o id do modulo na resposta da api, tratamos dos ids manualmente
        Module m = new Module(moduleName, this.moduleId++);
        this.modules.add(m);
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
}
