/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugtracker.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author joao_
 */
public class AuthRegUI extends BorderPane{
    
    public AuthRegUI(){
        Layout layout = new Layout();
        setCenter(layout);
    }
    
    class Layout extends HBox{

        public Layout() {
            LoginForm lForm = new LoginForm();
            Separator sep = new Separator();
            RegisterForm rForm = new RegisterForm();
            this.setAlignment(Pos.CENTER);
            lForm.setMaxWidth(648);
            this.getChildren().addAll(lForm,sep,rForm);
            this.setSpacing(200);
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

            Label lbPassRec = new Label("Forgot passowrd?");
            lbPassRec.setStyle("-fx-text-fill:#2c75e9");
            
            this.getChildren().addAll(lbLogin,lbSlogan,username,passwordField,signInBtn,lbPassRec);
            this.setAlignment(Pos.CENTER);
            this.setSpacing(10);
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
            
            this.getChildren().addAll(lbReg,username,mail,passwordField,passConfirm,signUpBtn);
            this.setAlignment(Pos.CENTER);
            this.setSpacing(10);
        }
        
    }
    
}
