/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Module;
import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author joao_
 */
public class ProjectsUI extends BorderPane{
    UIObservable observable;
    public ProjectsUI(UIObservable obs){
        this.observable = obs;

        this.setId("projects");
        Label lb = new Label("Project Name");
        lb.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-padding:10 0 10 10");
        setTop(lb);
        TabPane tabPane = new TabPane();

        tabPane.getStyleClass().add("floating");

        BugUI bugTab = new BugUI(observable);
        ModulesUI modulesTab = new ModulesUI(observable);
        TeamMembersUI membersTab = new TeamMembersUI(observable);

        Tab tab1 = new Tab("Bugs",bugTab);
        tab1.getStyleClass().add("tab");
        Tab tab2 = new Tab("Modules",modulesTab);
        tab2.getStyleClass().add("tab");
        Tab tab3 = new Tab("Team Members",membersTab);
        tab3.getStyleClass().add("tab");

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        setCenter(tabPane);

        tabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                //System.out.println("TAB Ativa: "+newValue);
            }
        });

        observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                setVisible(observable.getActualScreen() == Screens.OPERATIONS && observable.getActualSubScreen() == Screens.PROJECT);
            }
        });
    }
}
