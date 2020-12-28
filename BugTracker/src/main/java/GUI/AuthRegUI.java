/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author joao_
 */
public class AuthRegUI extends BorderPane{

    UIObservable observable;

    public AuthRegUI(UIObservable obs){
        this.observable = obs;
        Layout layout = new Layout();
        setCenter(layout);

        observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                setVisible(observable.getActualScreen() == Screens.LOGIN);
            }
        });
    }

    class Layout extends HBox{

        public Layout() {
            LoginForm lForm = new LoginForm();
            Separator sep = new Separator();
            RegisterForm rForm = new RegisterForm();
            this.setAlignment(Pos.CENTER);
            lForm.setMaxWidth(648);
            this.setStyle("-fx-background-color: white;");
            this.getChildren().addAll(lForm,sep,rForm);
            this.setSpacing(180);
        }
    }

    class Separator extends VBox{
        public Separator(){
            Line separator = new Line(0, 0, 0, 680);
            separator.setStrokeWidth(2);
            separator.setStyle("-fx-stroke:#c8c6c6");
            this.getChildren().addAll(separator);
            this.setAlignment(Pos.CENTER);
        }
    }

    class LoginForm extends VBox{

        public LoginForm() {
            Label lbLogin = new Label("LOGIN");
            lbLogin.setStyle("-fx-font-size:30px; -fx-font-weight: bold");

            Label lbSlogan = new Label("Let's Track Some Bugs");

            TextField username = new TextField ();
            username.setPrefSize(300, 40);
            username.setPromptText("Username");
            username.setStyle("-fx-font-size:15px;");

            PasswordField passwordField = new PasswordField();
            passwordField.setPrefSize(300, 40);
            passwordField.setPromptText("Password");
            passwordField.setStyle("-fx-font-size:15px;");

            Button signInBtn = new Button("Sign In");
            signInBtn.setStyle("-fx-background-color:\"#2c75e9\";\n" +
                    "    -fx-pref-width:200px;\n" +
                    "    -fx-pref-height:40px;\n" +
                    "    -fx-font-size:15px;\n" +
                    "    -fx-border-radius:5px;\n" +
                    "    -fx-text-fill:\"white\";");

            Label lbPassRec = new Label("Forgot password?");
            lbPassRec.setStyle("-fx-text-fill:#2c75e9");

            this.getChildren().addAll(lbLogin,lbSlogan,username,passwordField,signInBtn,lbPassRec);
            this.setAlignment(Pos.CENTER);
            this.setSpacing(10);

            signInBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    observable.signIn();
                }
            });
        }

    }

    class RegisterForm extends VBox{

        public RegisterForm(){
            Label lbReg = new Label("REGISTER");
            lbReg.setStyle("-fx-font-size:30px; -fx-font-weight: bold");

            TextField username = new TextField ();
            username.setPrefSize(300, 40);
            username.setPromptText("Username");
            username.setStyle("-fx-font-size:15px");

            TextField mail = new TextField ();
            mail.setPrefSize(300, 40);
            mail.setPromptText("Email");
            mail.setStyle("-fx-font-size:15px");

            PasswordField passwordField = new PasswordField();
            passwordField.setPrefSize(300, 40);
            passwordField.setPromptText("Password");
            passwordField.setStyle("-fx-font-size:15px");

            PasswordField passConfirm = new PasswordField();
            passConfirm.setPrefSize(300, 40);
            passConfirm.setPromptText("Confirm Password");
            passConfirm.setStyle("-fx-font-size:15px");

            Button signUpBtn = new Button("Sign Up");
            signUpBtn.setStyle("-fx-background-color:\"#2c75e9\";\n" +
                    "    -fx-pref-width:200px;\n" +
                    "    -fx-pref-height:40px;\n" +
                    "    -fx-font-size:15px;\n" +
                    "    -fx-border-radius:5px;\n" +
                    "    -fx-text-fill:\"white\";");

            this.getChildren().addAll(lbReg,username,mail,passwordField,passConfirm,signUpBtn);
            this.setAlignment(Pos.CENTER);
            this.setSpacing(10);
        }

    }

}