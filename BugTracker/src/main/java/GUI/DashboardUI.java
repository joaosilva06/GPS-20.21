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

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author joao_
 */
public class DashboardUI extends BorderPane{
    UIObservable observable;
    public DashboardUI(UIObservable obs){
        this.observable = obs;

        this.setId("dashboard");

        TopSection topSection = new TopSection(observable);
        setTop(topSection);


        
        observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                setVisible(observable.getActualScreen() == Screens.OPERATIONS && observable.getActualSubScreen() == Screens.DASHBOARD);
            }
        });
    }

    class TopSection extends HBox {
        UIObservable observable;

        public TopSection(UIObservable obs){
            this.observable = obs;

            Label lbDash = new Label("Dashboard");
            lbDash.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-padding:10 0 10 10");

            Label lbNewProject = new Label("Start a New Project");
            lbNewProject.setStyle("-fx-text-fill:#2c75e9;-fx-font-size:15px;-fx-padding:15 0 10 10");
            lbNewProject.setCursor(Cursor.HAND);

            this.setSpacing(600);

            this.getChildren().addAll(lbDash,lbNewProject);
        }
    }
}
