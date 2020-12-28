/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author joao_
 */
public class LateralMenu extends VBox{
    UIObservable observable;
    public LateralMenu(UIObservable obs){
        this.observable = obs;
        
        this.setId("lMenu");
        
        Label lbDashboard = new Label("Dashboard");
        Label lbProjects = new Label("Projects");
        Label lbLogout = new Label("Logout");
        lbDashboard.getStyleClass().add("menuOpts");
        lbProjects.getStyleClass().add("menuOpts");
        lbLogout.getStyleClass().add("menuOpts");
        this.setSpacing(10);
        this.getChildren().addAll(lbDashboard,lbProjects,lbLogout);
        
        lbDashboard.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                observable.dashboard();
            }
        });
        
        lbProjects.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                observable.project();
            } 
        });
    }
}
