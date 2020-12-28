/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Observables;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author joao_
 */
public class UIObservable {
    PropertyChangeSupport propertyChangeSupport;
    Screens actualScreen;
    Screens actualSubScreen;
    
    public UIObservable(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        startAppication();
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
    
    //funcoes estados
    public void startAppication() {
        actualScreen = Screens.LOGIN;
        disparaEventos(defineEventos(PropsID.CHANGE_SCREEN));
    }
    
    public void signIn(){
        actualScreen = Screens.OPERATIONS;
        actualSubScreen = Screens.DASHBOARD;
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
    
}