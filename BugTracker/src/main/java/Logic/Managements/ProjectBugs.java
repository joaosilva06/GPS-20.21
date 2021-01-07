package Logic.Managements;

import Logic.*;
import Logic.API_Requests.BugRequests;
import Logic.API_Requests.ProjectRequests;
import Logic.Exceptions.APIResponseException;

import java.io.IOException;
import java.util.List;

public class ProjectBugs {

    private List<Bug> bugs;
    private Project proj;

    public ProjectBugs(Project proj) throws IOException, APIResponseException {
        this.proj = proj;
        bugs = ProjectRequests.projectBugs(this.proj.getProjectId(), 1);
    }

    public List<Bug> getBugs(){return bugs;}
    public Project getProj(){return proj;}

    public void editBug(int pos, String desc, String title){
        try{
            Bug b = bugs.get(pos);
            boolean suc = BugRequests.editBug(desc, title, b.getId());
            if(suc){
                b.setDesc(desc);
                b.setTitle(title);
            }else{
                //callback
            }
        } catch (IOException e) {
            //callback
        }
    }

    public void addBug(Bug b, Priority prio, Type t, Integer mod){
        try{
            boolean suc = BugRequests.addBug(b.getDesc(), b.getTitle(), proj.getProjectId(), mod, t.ordinal(), prio.ordinal());
            if(suc){
                bugs.add(b);
            }else{
                //callback
            }
        } catch (IOException e) {
            //callback
        }
    }

    public void markAsSolved(int pos){
        try{
            int id = bugs.get(pos).getId();
            boolean suc = BugRequests.solve(id, proj.getProjectId());
            if(suc){
                bugs.get(pos).setStatus(Status.Solved.toString());
            }else{
                //callback
            }
        } catch (IOException e) {
            //callback
        }
    }

    public void markAsUnsolved(int pos){
        try{
            int id = bugs.get(pos).getId();
            if(BugRequests.unsolve(id)){
                bugs.get(pos).setStatus(Status.ToSolve.toString());
            }
        } catch (IOException e) {
            //callback
        }
    }

    public void markAsSolving(int pos, int user){
        try{
            int id = bugs.get(pos).getId();
            if(BugRequests.markSolving(id,user)){
                bugs.get(pos).setStatus(Status.InProgress.toString());
            }
        } catch (IOException e) {
            //callback
        }
    }

    public Bug request(int pos){
        try{
            int id = bugs.get(pos).getId();
            Bug b = BugRequests.getBug(id);

            if (!bugs.contains(b)) {
                bugs.add(b);
            }
            return b;
        } catch (IOException e) {
            //callback
        }
        return null;
    }

    public void check(int pos){
        //check é util?
    }

}
