package GUI;

import Logic.Exceptions.APIResponseException;
import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.io.IOException;


public class ProfileUI extends BorderPane {
    UIObservable observable;

    public ProfileUI(UIObservable obs){
        this.observable = obs;
        this.setId("profile");

        Label lb = new Label("Edit Profile");
        lb.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-padding:10 0 10 10");
        setTop(lb);

        Container container = new Container(observable);
        container.setAlignment(Pos.CENTER);
        setCenter(container);

        observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                setVisible(observable.getActualScreen() == Screens.OPERATIONS && observable.getActualSubScreen() == Screens.PROFILE);
            }
        });

        observable.registaPropertyChangeListener(PropsID.ERROR_EDIT_USER, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                Alert renameFail = new Alert(Alert.AlertType.ERROR);
                renameFail.setHeaderText(null);
                renameFail.setContentText(observable.getMessage());
                renameFail.showAndWait();
            }
        });
    }

    class Container extends HBox{
        UIObservable observable;
        public Container(UIObservable obs){
            this.observable = obs;
            Image userImg = new Image(Container.class.getResourceAsStream("/user.png"));
            ImageView imgView = new ImageView(userImg);
            imgView.setFitWidth(300);
            imgView.setFitHeight(300);

            Form form = new Form(observable);

            this.setSpacing(15);

            this.getChildren().addAll(imgView,form);

        }
    }

    class Form extends VBox{
        UIObservable observable;
        public Form(UIObservable obs){
            this.observable = obs;
            TextField username = new TextField ();
            username.setPrefSize(300, 40);
            username.setPromptText("Username");
            username.getStyleClass().add("formsInputs");

            TextField mail = new TextField ();
            mail.setPrefSize(300, 40);
            mail.setPromptText("Email");
            mail.getStyleClass().add("formsInputs");

            setAlignment(Pos.CENTER);

            Label lbNewPass = new Label("Change Password");
            lbNewPass.setStyle("-fx-text-fill:#2c75e9");
            lbNewPass.setCursor(Cursor.HAND);

            Button saveBtn = new Button("Save");
            saveBtn.getStyleClass().add("authScreenBtns");
            saveBtn.setCursor(Cursor.HAND);

            this.setSpacing(10);

            this.getChildren().addAll(username,mail,lbNewPass,saveBtn);

            saveBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    observable.rename(username.getText());
                }
            });
        }
    }
}
