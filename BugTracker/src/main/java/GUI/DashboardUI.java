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
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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

            lbNewProject.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dialog dialog = new Dialog();
                    dialog.getDialogPane().setStyle("-fx-pref-height:200px");
                    dialog.setTitle("New Project");
                    dialog.setHeaderText("Enter project name");

                    // Set the button types.
                    ButtonType confirmBtn = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(confirmBtn, ButtonType.CANCEL);

                    // Create the username and password labels and fields.
                    BorderPane pane = new BorderPane();
                    pane.setPadding(new Insets(20, 20, 20, 20));

                    TextField projectName = new TextField();
                    projectName.setPromptText("Project Name");
                    projectName.setPrefSize(300, 40);
                    projectName.getStyleClass().add("inputsDialogs");
                    pane.setCenter(projectName);

                    // Enable/Disable login button depending on whether a username was entered.
                    Node confirm = dialog.getDialogPane().lookupButton(confirmBtn);
                    confirm.setDisable(true);

                    // Do some validation (using the Java 8 lambda syntax).
                    projectName.textProperty().addListener((observable, oldValue, newValue) -> {
                        confirm.setDisable(newValue.trim().isEmpty());
                    });

                    dialog.getDialogPane().setContent(pane);

                    Optional result = dialog.showAndWait();

                    result.ifPresent(module -> {
                        if(result.get() == confirmBtn){
                            observable.addProject(projectName.getText());
                        }
                    });
                }
            });
        }
    }
}
