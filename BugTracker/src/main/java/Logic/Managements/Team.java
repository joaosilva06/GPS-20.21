package Logic.Managements;

import Logic.Project;
import Logic.Role;

public class Team {

    private Project proj;

    public Team(Project proj){
        this.proj = proj;
    }

    public void addMember(String search, Role role){
        //pedido de id do user
        //pedido para adicionar
        //se ocorreu sem erro adicionar ao array do proj
    }
    public void removeMember(int id){
        //pedido para remover o user com o id selecionado deste projeto
    }

    public void changeRole(int id, Role role){
        //pedido para alterar a role do user selecionado
    }
}
