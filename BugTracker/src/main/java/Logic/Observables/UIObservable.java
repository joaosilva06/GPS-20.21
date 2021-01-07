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
        return userProjects.getProjs();
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
        return userProjects.getProjs();
    }

    public void addProject(String project_name){
        try {
            if(ProjectRequests.addProject(project_name)) {
                disparaEventos(defineEventos(PropsID.PROJECT_ADDED));
            }
        }catch (IOException | APIResponseException ex){
            disparaEventos(defineEventos(PropsID.REQUEST_FAIL));
            setMessage("Fail to add project: "+ex);
        }

    }
    
    //funcoes estados
    public void startAppication() {
        actualScreen = Screens.LOGIN;
        disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
    }
    
    public void signIn(String username, String password){
        loggedUser.login(username, password);
        if(loggedUser != null){
            actualScreen = Screens.OPERATIONS;
            actualSubScreen = Screens.DASHBOARD;
            disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
        }
        else{
            disparaEventos(defineEventos(PropsID.LOGIN_FAIL));
            setMessage("Invalid login");
        }
    }

    public void signUp(String username, String password, String email){
        loggedUser.registar(username, password, email);
        if(loggedUser.getUsr() != null){
            disparaEventos(defineEventos(PropsID.USER_REG_SUCCESS));
            setMessage("Successfully registered!");
        }
        else{
            disparaEventos(defineEventos(PropsID.USER_REG_FAIL));
            setMessage("Something went wrong!");
        }
    }

    public void signOut(){
        actualScreen = Screens.LOGIN;
        actualSubScreen = null;
        disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
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
