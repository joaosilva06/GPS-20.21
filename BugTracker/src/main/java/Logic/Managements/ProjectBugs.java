package Logic.Managements;

import Logic.API_Requests.BugRequests;
import Logic.API_Requests.ProjectRequests;
import Logic.Bug;
import Logic.Exceptions.APIResponseException;
import Logic.Project;

import java.io.IOException;
import java.util.List;

public class ProjectBugs {

    private List<Bug> bugs;
    private Project proj;

    public ProjectBugs(Project proj) throws IOException, APIResponseException {
        this.proj = proj;
        bugs = ProjectRequests.projectBugs(this.proj.getProjectId());
    }

    public List<Bug> getBugs(){return bugs;}
    public Project getProj(){return proj;}

    public void editBug(Bug b){
        try{
            BugRequests.editBug(b.getDesc(), b.getTitle(), b.getId());
        } catch (IOException e) {
            //callback
        }
    }

    public void addBug(Bug b){
        try{
            BugRequests.addBug(b.getDesc(), b.getTitle(), proj.getProjectId());
        } catch (IOException e) {
            //callback
        }
    }

    public void markAsSolved(int id){
        try{
            BugRequests.solve(id, proj.getProjectId());
        } catch (IOException e) {
            //callback
        }
    }

}
