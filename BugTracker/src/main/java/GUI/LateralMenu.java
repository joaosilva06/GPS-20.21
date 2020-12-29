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

        Menu menu = new Menu();
        menu.setAlignment(Pos.TOP_CENTER);
        setTop(menu);

        Logout logout = new Logout();
        logout.setAlignment(Pos.BOTTOM_CENTER);
        setBottom(logout);
    }

    class Menu extends VBox{
        public Menu(){
            Label lbDashboard = new Label("Dashboard");
            Label lbProjects = new Label("Projects");
            lbDashboard.getStyleClass().add("menuOpts");
            lbProjects.getStyleClass().add("menuOpts");
            this.setSpacing(10);


            Image image = new Image(Menu.class.getResourceAsStream("/user.jpg"));
            ImageView imgUser = new ImageView(image);
            imgUser.setFitWidth(150);
            imgUser.setFitHeight(150);
            this.getChildren().addAll(imgUser,lbDashboard,lbProjects);


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

            String cssLayout = "-fx-border-color: black;\n" +
                    "-fx-border-insets: 5;\n" +
                    "-fx-border-width: 1 0 0 0;";
            this.setStyle(cssLayout);
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
