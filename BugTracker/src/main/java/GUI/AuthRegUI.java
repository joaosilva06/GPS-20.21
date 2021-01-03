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
import javafx.scene.Cursor;
import javafx.scene.control.*;
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

        observable.registaPropertyChangeListener(PropsID.USER_REG_SUCCESS, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                Alert regFail = new Alert(Alert.AlertType.INFORMATION);
                regFail.setHeaderText(null);
                regFail.setContentText(observable.getMessage());
                regFail.showAndWait();
            }
        });

        observable.registaPropertyChangeListener(PropsID.USER_REG_FAIL, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                Alert regSuccess = new Alert(Alert.AlertType.ERROR);
                regSuccess.setHeaderText(null);
                regSuccess.setContentText(observable.getMessage());
                regSuccess.showAndWait();
            }
        });

        observable.registaPropertyChangeListener(PropsID.LOGIN_FAIL, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                Alert loginFail = new Alert(Alert.AlertType.ERROR);
                loginFail.setHeaderText(null);
                loginFail.setContentText(observable.getMessage());
                loginFail.showAndWait();
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
            this.getStyleClass().addAll("authregui");
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
            lbLogin.getStyleClass().add("labelAuthReg");
            lbLogin.setStyle("-fx-font-size:30px; -fx-font-weight: bold");

            Label lbSlogan = new Label("Let's Track Some Bugs");

            TextField username = new TextField ();
            username.setPrefSize(300, 40);
            username.setPromptText("Username");
            username.getStyleClass().add("formsInputs");

            PasswordField passwordField = new PasswordField();
            passwordField.setPrefSize(300, 40);
            passwordField.setPromptText("Password");
            passwordField.getStyleClass().add("formsInputs");

            Button signInBtn = new Button("Sign In");
            signInBtn.getStyleClass().add("authScreenBtns");
            signInBtn.setCursor(Cursor.HAND);

            Label lbPassRec = new Label("Forgot password?");
            lbPassRec.setStyle("-fx-text-fill:#2c75e9");
            lbPassRec.setCursor(Cursor.HAND);

            this.getChildren().addAll(lbLogin,lbSlogan,username,passwordField,signInBtn,lbPassRec);
            this.setAlignment(Pos.CENTER);
            this.setSpacing(10);

            signInBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {

                    if(username.getText().trim().isEmpty()){
                        username.getStyleClass().add("wrongInput");
                    }
                    else if(passwordField.getText().trim().isEmpty()){
                        passwordField.getStyleClass().add("wrongInput");
                    }
                    else {
                        observable.signIn(username.getText(),passwordField.getText());
                        username.getStyleClass().removeAll("wrongInput");
                        passwordField.getStyleClass().removeAll("wrongInput");
                        username.clear();
                        passwordField.clear();
                    }
                }
            });
        }

    }

    class RegisterForm extends VBox{

        public RegisterForm(){
            Label lbReg = new Label("REGISTER");
            lbReg.getStyleClass().add("labelAuthReg");

            TextField username = new TextField ();
            username.setPrefSize(300, 40);
            username.setPromptText("Username");
            username.getStyleClass().add("formsInputs");

            TextField mail = new TextField ();
            mail.setPrefSize(300, 40);
            mail.setPromptText("Email");
            mail.getStyleClass().add("formsInputs");

            PasswordField passwordField = new PasswordField();
            passwordField.setPrefSize(300, 40);
            passwordField.setPromptText("Password");
            passwordField.getStyleClass().add("formsInputs");

            PasswordField passConfirm = new PasswordField();
            passConfirm.setPrefSize(300, 40);
            passConfirm.setPromptText("Confirm Password");
            passConfirm.getStyleClass().add("formsInputs");

            Button signUpBtn = new Button("Sign Up");
            signUpBtn.getStyleClass().add("authScreenBtns");
            signUpBtn.setCursor(Cursor.HAND);
            this.getChildren().addAll(lbReg,username,mail,passwordField,passConfirm,signUpBtn);
            this.setAlignment(Pos.CENTER);
            this.setSpacing(10);

            signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(username.getText().trim().isEmpty()){
                        username.getStyleClass().add("wrongInput");
                        return;
                    }
                    else{
                        username.getStyleClass().removeAll("wrongInput");
                    }

                    if(mail.getText().trim().isEmpty()){
                        mail.getStyleClass().add("wrongInput");
                        return;
                    }
                    else {
                        mail.getStyleClass().removeAll("wrongInput");
                    }

                    if(passwordField.getText().trim().isEmpty()){
                        passwordField.getStyleClass().add("wrongInput");
                        return;
                    }
                    else{
                        passwordField.getStyleClass().removeAll("wrongInput");
                    }

                    if(passConfirm.getText().trim().isEmpty()){
                        passConfirm.getStyleClass().add("wrongInput");
                        return;
                    }
                    else{
                        passConfirm.getStyleClass().removeAll("wrongInput");
                    }

                    if(!passConfirm.getText().equals(passwordField.getText())){
                        Alert mismatch = new Alert(Alert.AlertType.ERROR);
                        mismatch.setHeaderText(null);
                        mismatch.setContentText("Password fields doesn't match");
                        mismatch.showAndWait();
                        return;
                    }
                    else{
                        observable.signUp(username.getText(),passwordField.getText(),mail.getText());
                        username.clear();
                        mail.clear();
                        passwordField.clear();
                        passConfirm.clear();
                    }
                }
            });
        }

    }

}