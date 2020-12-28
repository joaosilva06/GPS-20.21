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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author joao_
 */
public class BugTrackerUI extends BorderPane{
    UIObservable observable;
    public BugTrackerUI(UIObservable obs){
        this.observable = obs;
        StackPane pane = new StackPane();
        pane.setPrefSize(1280, 720);
        //getStylesheets().addAll(this.getClass().getResource("../resources/styles.css").toExternalForm());
        OperationsPanelUI opPanelUI = new OperationsPanelUI(observable);
        AuthRegUI authregUI = new AuthRegUI(observable);
        authregUI.setId("authregui");
        opPanelUI.setId("operations");
        opPanelUI.setVisible(false);
        pane.getChildren().addAll(authregUI,opPanelUI);
        setCenter(pane);
        
    }
    
}
