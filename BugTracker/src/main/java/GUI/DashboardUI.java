/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugtracker.gui;

import bugtracker.logic.PropsID;
import bugtracker.logic.Screens;
import bugtracker.logic.UIObservable;
import java.beans.PropertyChangeEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author joao_
 */
public class DashboardUI extends BorderPane{
    UIObservable observable;
    public DashboardUI(UIObservable obs){
        this.observable = obs;
        
        Label lb = new Label("DASHBOARD");
        setCenter(lb);
        
        observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                setVisible(observable.getActualScreen() == Screens.OPERATIONS && observable.getActualSubScreen() == Screens.DASHBOARD);
            }
        });
    }
}
