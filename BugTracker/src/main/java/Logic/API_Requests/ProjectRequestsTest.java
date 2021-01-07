package Logic.API_Requests;

import Logic.*;
import Logic.Exceptions.APIResponseException;
import Logic.Module;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.ModuleElement;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRequestsTest{

    @Test
    void projectBugs() {
        List<Bug> lista = new ArrayList<Bug>();
        lista.add(new Bug());

        Project projeto = new Project();
        projeto.setBugs(lista);

        List<Bug> teste = null;

        try{
            teste = ProjectRequests.projectBugs(projeto.getProjectId());
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        //assertNull(teste);
        assertEquals(projeto.getBugs().size(), teste.size());
    }

    @Test
    void projectMembers() {
        //Criar Users
        User user1 = new User();
        user1.setUsername("TesTer");
        User user2 = new User();
        user2.setUsername("Hugo");

        //Criar lista de Users
        List<User> membros = new ArrayList<User>();
        membros.add(user1);
        membros.add(user2);

        //Criar Projeto para testar e colocar a Lista neste projeto
        Project projeto = new Project();
        projeto.setMembers(membros);

        List<User> teste = null;

        try{
            teste = ProjectRequests.projectMembers(projeto.getProjectId());
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        assertEquals(teste.size(),projeto.getMembers().size());
    }

    @Test
    void projectModules() {
        //Criar Modules
        Module module1 = new Module("modelo1",1);
        Module module2 = new Module("modelo2",2);

        //Criar lista e adicionar Modules
        List<Module> lista = new ArrayList<Module>();
        lista.add(module1);
        lista.add(module2);

        //Criar Projeto e adicionar lista de Modules
        Project project = new Project();
        project.setModules(lista);

        List<Module> teste = null;

        try{
            teste = ProjectRequests.projectModules(project.getProjectId());
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        assertEquals(teste.size(),project.getModules().size());
    }

    @Test
    void addProject() {
        Project projeto = new Project();
        projeto.setName("ProjetoTeste");

        boolean teste = false;

        try{
            teste = ProjectRequests.addProject(projeto.getName());
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        assertNotNull(teste);
        assertTrue(teste);
    }

    @Test
    void removeProject() {
        Project projeto = new Project();
        projeto.setName("ProjetoTeste");

        boolean teste = false;

        try{
            teste = ProjectRequests.removeProject(projeto.getProjectId());
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        assertNotNull(teste);
        assertTrue(teste);
    }

    @Test
    void newModule() {
        String module = "ModuleTeste";
        Project projeto = new Project();
        projeto.addModule(module);

        boolean teste = false;

        try{
            teste = ProjectRequests.newModule(projeto.getProjectId(), module);
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        assertNotNull(teste);
        assertTrue(teste);
    }

    @Test
    void addMember() {
        User user = new User();
        user.setUsername("TesTer");
        user.setPassword("testerpass");

        Project project = new Project();

        boolean teste = false;

        try{
            teste = ProjectRequests.addMember(4, user.getId(), project.getProjectId());
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        assertNotNull(teste);
        assertTrue(teste);
    }

    @Test
    void changeRole() {
        //Criacao do User
        User membro = new User();
        membro.setUsername("TesTer");
        membro.setPassword("testerpass");

        //Criacao da lista e adicao de um novo membro
        List<User> lista = new ArrayList<User>();
        lista.add(membro);

        //Criacao do projeto
        Project project = new Project();
        project.setMembers(lista);

        Boolean teste = false;

        try{
            teste = ProjectRequests.addMember(4, membro.getId(), project.getProjectId());
            teste = ProjectRequests.changeRole(3, membro.getId(), project.getProjectId());
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        assertNotNull(teste);
        assertTrue(teste);
    }

    @Test
    void removeMember() {
        //Criacao de um User
        User membro = new User();
        membro.setUsername("TesTer");
        membro.setPassword("testerpass");

        //Criacao de um lista e adicao de um novo Membro
        List<User> lista = new ArrayList<User>();
        lista.add(membro);

        //Criacao do Projeto e adicao da lista de membros
        Project project = new Project();
        project.setMembers(lista);

        boolean teste = false;

        try{
            teste = ProjectRequests.removeMember(membro.getId(), project.getProjectId());
        }catch(IOException | APIResponseException e){
            fail(e.getMessage());
        }

        assertNotNull(teste);
        assertTrue(teste);
    }
}