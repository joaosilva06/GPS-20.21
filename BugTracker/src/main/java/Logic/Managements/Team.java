package Logic.Managements;

import Logic.API_Requests.ProjectRequests;
import Logic.API_Requests.UserRequests;
import Logic.Exceptions.APIResponseException;
import Logic.Project;
import Logic.Role;
import Logic.User;

import java.io.IOException;

public class Team {

    private Project proj;

    public Team(Project proj){
        this.proj = proj;
    }

    public void addMember(String search, Role role){
        //pedido de id do user
        //pedido para adicionar
        //se ocorreu sem erro adicionar ao array do proj
        try {
            User u = UserRequests.search(search);
            if (u != null) {
              if(ProjectRequests.addMember(role.ordinal(), u.getId(), proj.getProjectId())){
                proj.getMembers().add(u);
              }
            }
        }catch (IOException e){
            //uma callback para a interface
        }catch (APIResponseException e){
            //callback
        }
    }
    public void removeMember(int pos){
        //pedido para remover o user com o id selecionado deste projeto
        int id = proj.getMembers().get(pos).getId();
        try {
            if(ProjectRequests.removeMember(id, proj.getProjectId()))
                proj.getMembers().remove(pos);
        } catch (IOException e) {
            //uma callback para a interface
        }
    }

    public void changeRole(int pos, Role role){
        int id = proj.getMembers().get(pos).getId();
        try {
            if(ProjectRequests.changeRole(role.ordinal(), id, proj.getProjectId()))
                proj.getMembers().remove(pos);
        } catch (IOException e) {
            //uma callback para a interface
        }
    }
}