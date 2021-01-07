/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Observables;

import Logic.API_Requests.ProjectRequests;
import Logic.API_Requests.UserRequests;
import Logic.Exceptions.APIResponseException;
import Logic.Managements.UserManagement;
import Logic.Managements.UserProjects;
import Logic.Project;
import Logic.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author joao_
 */
public class UIObservable {
    PropertyChangeSupport propertyChangeSupport;
    Screens actualScreen;
    Screens actualSubScreen;
    String message;
    UserManagement loggedUser;
    UserProjects userProjects;
    
    public UIObservable(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        loggedUser = new UserManagement(null);
        userProjects = new UserProjects(null);
        startAppication();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setProjects(List<Project> list){
        this.userProjects.setProjs(list);
    }

    public List<Project> getProjectList() {
        try{
            return userProjects.getProjs(loggedUser.getUsr().getId());
        }catch (IOException e){
            disparaEventos(defineEventos(PropsID.REQUEST_FAIL));
            setMessage("Erro ao obter projetos: "+e);
        }
        return null;
    }

    public Screens getActualScreen(){
        return this.actualScreen;
    }
    
    public Screens getActualSubScreen(){
        return this.actualSubScreen;
    }
    
    private Set<PropsID> defineEventos(PropsID... eventosBase){
        Set<PropsID> eventos = new HashSet<>(Set.of(eventosBase));
        return eventos;
    }
    
    public void registaPropertyChangeListener(PropsID prop, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(prop.toString(), listener);
    }
    
    private void disparaEventos(Set<PropsID> eventos) {
        eventos.forEach((e) -> {
            propertyChangeSupport.firePropertyChange(e.toString(), null, null);
        });
    }

    //dados
    public List<Project> getProjectsFromApi(){
        try{
            return userProjects.getProjs(loggedUser.getUsr().getId());
        }catch (IOException e){
            disparaEventos(defineEventos(PropsID.REQUEST_FAIL));
            setMessage("Erro ao obter projetos: "+e);
        }
        return null;
    }

    public Project addProject(String project_name){
        Project project = null;
        try {
            project = userProjects.addProject(project_name);
            if(project != null) {
                disparaEventos(defineEventos(PropsID.PROJECT_ADDED));
            }
        } catch (Exception e) {
            disparaEventos(defineEventos(PropsID.REQUEST_FAIL));
            setMessage("Fail to add project: "+e);
        }
        return project;
    }

    public void newModule(int pos, String moduleName){
        try{
            userProjects.newModule(pos,moduleName);
        }catch (Exception e){
            disparaEventos(defineEventos(PropsID.REQUEST_FAIL));
            setMessage("Error adding module: "+e);
        }
    }

    public void removeModule(int posModule, int posProject){
        try{
            userProjects.removeModule(posModule,posProject);
        }catch (Exception e){
            disparaEventos(defineEventos(PropsID.REQUEST_FAIL));
            setMessage("Error removing module: "+e);
        }
    }
    
    //funcoes estados
    public void startAppication() {
        actualScreen = Screens.LOGIN;
        disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
    }
    
    public void signIn(String username, String password){
        try {
            loggedUser.login(username, password);
            if(loggedUser.getUsr() != null){
                actualScreen = Screens.OPERATIONS;
                actualSubScreen = Screens.DASHBOARD;
                disparaEventos(defineEventos(PropsID.CHANGE_SCREEN,PropsID.GET_PROJECTS));
            }
            else{
                disparaEventos(defineEventos(PropsID.LOGIN_FAIL));
                setMessage("Invalid login");
            }
        } catch (IOException e) {
            disparaEventos(defineEventos(PropsID.LOGIN_FAIL));
            setMessage("Something went wrong: "+e);
        }

    }

    public void signUp(String username, String password, String email){

        try {
            loggedUser.registar(username, password, email);
            if(loggedUser.getUsr() != null){
                disparaEventos(defineEventos(PropsID.USER_REG_SUCCESS));
                setMessage("Successfully registered!");
            }
            else{
                disparaEventos(defineEventos(PropsID.USER_REG_FAIL));
                setMessage("Something went wrong!");
            }
        } catch (IOException e) {
            disparaEventos(defineEventos(PropsID.USER_REG_FAIL));
            setMessage("Something went wrong: "+e);
        }

    }

    public void signOut(){
        try {
            if(loggedUser.logoff()){
                actualScreen = Screens.LOGIN;
                actualSubScreen = null;
                disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
            }else{
                disparaEventos(defineEventos(PropsID.REQUEST_FAIL));
                setMessage("Error logging out");
            }

        } catch (IOException e) {
            disparaEventos(defineEventos(PropsID.REQUEST_FAIL));
            setMessage("Error logging out: "+e);
        }
    }

    public void rename(String username){
        try{
            if(loggedUser.rename(username)){
                setMessage("Username updated");
            }
            else{
                disparaEventos(defineEventos(PropsID.ERROR_EDIT_USER));
                setMessage("Error updating username");
            }
        }catch (Exception e){
            disparaEventos(defineEventos(PropsID.ERROR_EDIT_USER));
            setMessage("Error updating username: "+e);
        }
    }

    public void resetMail(String email){
        try{
            if(loggedUser.resetMail(email)){
                setMessage("Email updated");
            }
            else{
                disparaEventos(defineEventos(PropsID.ERROR_EDIT_USER));
                setMessage("Error updating email");
            }
        }catch (Exception e){
            disparaEventos(defineEventos(PropsID.ERROR_EDIT_USER));
            setMessage("Error updating email: "+e);
        }
    }

    public void repass(String newPassword){
        try {
            if(loggedUser.repass(newPassword)){
                setMessage("Password updated");
            }else{
                disparaEventos(defineEventos(PropsID.ERROR_EDIT_USER));
                setMessage("Error updating password");
            }
        } catch (Exception e) {
            disparaEventos(defineEventos(PropsID.ERROR_EDIT_USER));
            setMessage("Error updating username: "+e);
        }
    }
    
    public void dashboard(){
        actualScreen = Screens.OPERATIONS;
        actualSubScreen = Screens.DASHBOARD;
        disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
    }
    
    public void project(){
        actualScreen = Screens.OPERATIONS;
        actualSubScreen = Screens.PROJECT;
        disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
    }

    public void profile(){
        actualScreen = Screens.OPERATIONS;
        actualSubScreen = Screens.PROFILE;
        disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
    }

    public void bugDetails(){
        actualScreen = Screens.OPERATIONS;
        actualSubScreen = Screens.BUG_DETAILS;
        disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
    }
    
}
