/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugtracker.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author joao_
 */
public class BugTrackerUI extends BorderPane{
    
    public BugTrackerUI(){
        StackPane pane = new StackPane();
        pane.setPrefSize(1280, 720);
        getStylesheets().addAll(this.getClass().getResource("../resources/styles.css").toExternalForm());
        AuthRegUI authregUI = new AuthRegUI();
        DashboardUI dashUI = new DashboardUI();
        authregUI.setId("authregui");
        authregUI.setVisible(true);
        dashUI.setVisible(false);
        pane.getChildren().addAll(authregUI,dashUI);
        setCenter(pane);
    }
    
}
