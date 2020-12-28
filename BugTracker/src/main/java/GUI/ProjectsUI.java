/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import java.beans.PropertyChangeEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author joao_
 */
public class ProjectsUI extends BorderPane{
    UIObservable observable;
    public ProjectsUI(UIObservable obs){
        this.observable = obs;
        
        Label lb = new Label("PROJECTS");
        setCenter(lb);
        
        observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                setVisible(observable.getActualScreen() == Screens.OPERATIONS && observable.getActualSubScreen() == Screens.PROJECT);
            }
        });
    }
}
