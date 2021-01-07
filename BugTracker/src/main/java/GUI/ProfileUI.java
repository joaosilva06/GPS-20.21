package GUI;

import Logic.Exceptions.APIResponseException;
import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.Optional;


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
        String passwordText = "";
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
                    if(!username.getText().trim().isEmpty()){
                        observable.rename(username.getText());
                    }

                    if(!mail.getText().trim().isEmpty()){
                        observable.resetMail(mail.getText());
                    }

                    if(!passwordText.trim().isEmpty()){
                        observable.repass(passwordText);
                    }

                }
            });

            lbNewPass.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dialog dialog = new Dialog();
                    dialog.getDialogPane().setStyle("-fx-pref-height:200px");
                    dialog.setTitle("Change Password");
                    dialog.setHeaderText("Enter new passowrd");

                    // Set the button types.
                    ButtonType confirmBtn = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(confirmBtn, ButtonType.CANCEL);

                    // Create the username and password labels and fields.
                    BorderPane pane = new BorderPane();
                    pane.setPadding(new Insets(20, 20, 20, 20));

                    TextField password = new TextField();
                    password.setPromptText("New Password");
                    password.setPrefSize(300, 40);
                    password.getStyleClass().add("inputsDialogs");
                    pane.setCenter(password);

                    // Enable/Disable login button depending on whether a username was entered.
                    Node confirm = dialog.getDialogPane().lookupButton(confirmBtn);
                    confirm.setDisable(true);

                    // Do some validation (using the Java 8 lambda syntax).
                    password.textProperty().addListener((observable, oldValue, newValue) -> {
                        confirm.setDisable(newValue.trim().isEmpty());
                    });

                    dialog.getDialogPane().setContent(pane);

                    Optional result = dialog.showAndWait();

                    result.ifPresent(module -> {
                        if(result.get() == confirmBtn){
                            passwordText = password.getText();
                        }
                    });
                }
            });
        }
    }
}
