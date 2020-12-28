/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author joao_
 */
public class OperationsPanelUI extends BorderPane {
    UIObservable observable;
    public OperationsPanelUI(UIObservable obs){

        this.observable = obs;
        // Paineis para serem usados consoante a opcao escolhida do menu
        StackPane screens = new StackPane();
        screens.setPrefSize(1080, 720);
        DashboardUI dashboard = new DashboardUI(observable);
        ProjectsUI projects = new ProjectsUI(observable);
        dashboard.setVisible(false);
        projects.setVisible(false);
        screens.getChildren().addAll(dashboard,projects);
        setCenter(screens);

        // Menu Lateral Fixo
        LateralMenu lMenu = new LateralMenu(observable);
        lMenu.setAlignment(Pos.TOP_CENTER);
        setLeft(lMenu);

        observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                setVisible(observable.getActualScreen() == Screens.OPERATIONS);
            }
        });
    }

}