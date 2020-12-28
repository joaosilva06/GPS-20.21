/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Observables.UIObservable;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author joao_
 */
public class LateralMenu extends VBox{
    UIObservable observable;
    public LateralMenu(UIObservable obs){
        this.observable = obs;

        this.setStyle("-fx-background-color:\"#dedddc\";\n" +
                "    -fx-pref-width:200px;");

        Label lbDashboard = new Label("Dashboard");
        Label lbProjects = new Label("Projects");
        Label lbLogout = new Label("Logout");
        lbDashboard.setStyle("-fx-font-size:15px;");
        lbProjects.setStyle("-fx-font-size:15px;");
        lbLogout.setStyle("-fx-font-size:15px;");
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
