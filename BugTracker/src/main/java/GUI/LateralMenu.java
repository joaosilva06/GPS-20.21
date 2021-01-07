/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author joao_
 */
public class LateralMenu extends BorderPane {
    UIObservable observable;
    public LateralMenu(UIObservable obs){
        this.observable = obs;

        this.setId("lMenu");

        Profile profile = new Profile(observable);
        profile.setAlignment(Pos.TOP_CENTER);
        setTop(profile);

        Menu menu = new Menu();
        menu.setAlignment(Pos.TOP_CENTER);
        setCenter(menu);

        Logout logout = new Logout();
        logout.setAlignment(Pos.BOTTOM_CENTER);
        setBottom(logout);

        observable.registaPropertyChangeListener(PropsID.GET_PROJECTS, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                observable.setProjects(observable.getProjectsFromApi());
                System.out.println(observable.getProjectList());
            }
        });

        observable.registaPropertyChangeListener(PropsID.PROJECT_ADDED, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                System.out.println(observable.getProjectList());
            }
        });

        observable.registaPropertyChangeListener(PropsID.REQUEST_FAIL, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                Alert addProjFail = new Alert(Alert.AlertType.ERROR);
                addProjFail.setHeaderText(null);
                addProjFail.setContentText(observable.getMessage());
                addProjFail.showAndWait();
            }
        });
    }

    class Profile extends VBox{
        UIObservable observable;
        public Profile(UIObservable obs){
            this.observable = obs;

            Image image = new Image(Menu.class.getResourceAsStream("/user.png"));
            ImageView imgUser = new ImageView(image);
            imgUser.setFitWidth(150);
            imgUser.setFitHeight(150);

            Label lbUser = new Label("Username");
            lbUser.setId("username");
            lbUser.setCursor(Cursor.HAND);

            setSpacing(10);

            this.getChildren().addAll(imgUser,lbUser);

            lbUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    observable.profile();
                }
            });
        }
    }

    class Menu extends VBox{

        public Menu(){

            this.getStyleClass().add("separatorsLMenu");

            Label lbDashboard = new Label("Dashboard");
            Label lbProjects = new Label("Projects");
            lbDashboard.setCursor(Cursor.HAND);
            lbProjects.setCursor(Cursor.HAND);
            lbDashboard.getStyleClass().addAll("menuOpts", "active");
            lbProjects.getStyleClass().add("menuOpts");
            this.setSpacing(5);


            this.getChildren().addAll(lbDashboard,lbProjects);

            observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
                @Override
                public void onChange(PropertyChangeEvent evt) {
                    if (observable.getActualSubScreen() == Screens.DASHBOARD) {
                        lbDashboard.getStyleClass().add("active");
                    } else {
                        lbDashboard.getStyleClass().removeAll("active");
                    }

                    if(observable.getActualSubScreen() == Screens.PROJECT || observable.getActualSubScreen() == Screens.BUG_DETAILS){
                        lbProjects.getStyleClass().add("active");
                    } else {
                        lbProjects.getStyleClass().removeAll("active");
                    }
                }
            });


            lbDashboard.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    lbProjects.getStyleClass().removeAll("active");
                    lbDashboard.getStyleClass().add("active");
                    observable.dashboard();
                }
            });

            lbProjects.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    lbProjects.getStyleClass().add("active");
                    lbDashboard.getStyleClass().removeAll("active");
                    observable.project();
                }
            });
        }
    }

    class Logout extends VBox{
        public Logout(){

            this.getStyleClass().add("separatorsLMenu");
            this.setStyle("-fx-padding:10 0 10 0");
            Label lbLogout = new Label("Logout");
            lbLogout.setId("lbLogout");
            lbLogout.setCursor(Cursor.HAND);
            this.getChildren().addAll(lbLogout);

            lbLogout.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    observable.signOut();
                }
            });
        }
    }
}
