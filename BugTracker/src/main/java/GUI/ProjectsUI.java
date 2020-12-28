/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author joao_
 */
public class ProjectsUI extends BorderPane{
    UIObservable observable;
    public ProjectsUI(UIObservable obs){
        this.observable = obs;
//tem tabs
        Label lb = new Label("Project Name");
        lb.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-padding:10 0 10 10");
        setTop(lb);
        TabPane tabPane = new TabPane();

        BugUI bugTab = new BugUI(observable);

        Tab tab1 = new Tab("Bugs",bugTab);
        tab1.setStyle("-fx-pref-width:150px;-fx-pref-height:30px");
        Tab tab2 = new Tab("Modules");
        tab2.setStyle("-fx-pref-width:150px;-fx-pref-height:30px");
        Tab tab3 = new Tab("Team Members");
        tab3.setStyle("-fx-pref-width:150px;-fx-pref-height:30px");

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
