/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Observables.UIObservable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

        Profile profile = new Profile();
        profile.setAlignment(Pos.TOP_CENTER);
        setTop(profile);

        Menu menu = new Menu();
        menu.setAlignment(Pos.TOP_CENTER);
        setCenter(menu);

        Logout logout = new Logout();
        logout.setAlignment(Pos.BOTTOM_CENTER);
        setBottom(logout);
    }

    class Profile extends VBox{
        public Profile(){
            Image image = new Image(Menu.class.getResourceAsStream("/user.jpg"));
            ImageView imgUser = new ImageView(image);
            imgUser.setFitWidth(150);
            imgUser.setFitHeight(150);

            Label lbUser = new Label("Username");
            lbUser.getStyleClass().add("menuOpts");

            setSpacing(10);

            this.getChildren().addAll(imgUser,lbUser);
        }
    }

    class Menu extends VBox{
        public Menu(){

            this.getStyleClass().add("separatorsLMenu");

            Label lbDashboard = new Label("Dashboard");
            Label lbProjects = new Label("Projects");
            lbDashboard.getStyleClass().add("menuOpts");
            lbDashboard.setStyle("-fx-padding:15 0 0 0");
            lbProjects.getStyleClass().add("menuOpts");
            this.setSpacing(5);


            this.getChildren().addAll(lbDashboard,lbProjects);


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

    class Logout extends VBox{
        public Logout(){

            this.getStyleClass().add("separatorsLMenu");
            this.setStyle("-fx-padding:10 0 10 0");
            Label lbLogout = new Label("Logout");
            lbLogout.getStyleClass().add("menuOpts");
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
