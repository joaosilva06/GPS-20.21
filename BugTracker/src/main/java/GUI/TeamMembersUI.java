package GUI;

import Logic.Bug;
import Logic.Observables.UIObservable;
import Logic.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamMembersUI extends BorderPane{
    UIObservable observable;

    public TeamMembersUI(UIObservable obs){

        this.observable = obs;

        this.getStyleClass().add("contentPanel");

        Options opt = new Options();
        opt.setAlignment(Pos.TOP_RIGHT);
        setTop(opt);

        TableView membersTable = new TableView();

        membersTable.getStyleClass().add("table");

        TableColumn<User, String> memberName = new TableColumn<>("MEMBER");
        memberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        memberName.getStyleClass().add("column");

        TableColumn<User, String> addDate = new TableColumn<>("ADDED ON");
        addDate.setCellValueFactory(new PropertyValueFactory<>("addDate"));
        addDate.getStyleClass().add("column");

        TableColumn<User, String> role = new TableColumn<>("ROLE");
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        role.getStyleClass().add("lastColumn");


        membersTable.getColumns().addAll(memberName,addDate,role);

        membersTable.setColumnResizePolicy(membersTable.CONSTRAINED_RESIZE_POLICY);

        membersTable.setPlaceholder(new Label("No team members to display"));

        setCenter(membersTable);

    }

    class Options extends HBox {
        public Options(){
            Label lbAdd = new Label("+");
            lbAdd.getStyleClass().add("addBtn");
            Label lbDel = new Label("x");
            lbDel.getStyleClass().add("delBtn");
            this.setSpacing(5);
            this.getChildren().addAll(lbAdd,lbDel);

            lbAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    Dialog dialog = new Dialog();
                    dialog.setTitle("New Team Member");
                    dialog.setHeaderText("Enter team member details");

                    // Set the button types.
                    ButtonType confirmBtn = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(confirmBtn, ButtonType.CANCEL);

                    // Create the username and password labels and fields.
                    BorderPane pane = new BorderPane();
                    pane.setPadding(new Insets(20, 20, 20, 20));

                    VBox form = new VBox();

                    TextField memberName = new TextField();
                    memberName.setPromptText("Member Name");
                    memberName.setPrefSize(300, 40);
                    memberName.getStyleClass().add("inputsDialogs");
                    pane.setCenter(memberName);

                    ComboBox roleChooser = new ComboBox();
                    roleChooser.getItems().addAll("Role 1", "Role 2", "Role 3", "Role 4", "Role 5");
                    roleChooser.setPrefSize(300,40);

                    form.setSpacing(5);
                    form.getChildren().addAll(memberName,roleChooser);

                    pane.setCenter(form);

                    // Enable/Disable login button depending on whether a username was entered.
                    Node confirm = dialog.getDialogPane().lookupButton(confirmBtn);
                    confirm.setDisable(true);

                    roleChooser.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            confirm.setDisable(newValue == null && memberName.getText().trim().isEmpty());
                        }
                    });

                    dialog.getDialogPane().setContent(pane);

                    Optional result = dialog.showAndWait();

                    result.ifPresent(module -> {
                        if(result.get() == confirmBtn){
                            System.out.println("Member name: "+memberName.getText());
                        }
                    });
                }
            });

            lbDel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dialog dialog = new Dialog();
                    dialog.getDialogPane().setStyle("-fx-pref-height:100px");
                    dialog.setTitle("Delete Member");
                    dialog.setHeaderText(null);

                    // Set the button types.
                    ButtonType confirmBtn = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(confirmBtn, ButtonType.CANCEL);

                    // Create the username and password labels and fields.
                    BorderPane pane = new BorderPane();
                    pane.setPadding(new Insets(20, 20, 20, 20));

                    Label lbQuestion = new Label("Sure you want to delete this member?");
                    pane.setCenter(lbQuestion);

                    dialog.getDialogPane().setContent(pane);

                    Optional result = dialog.showAndWait();

                    result.ifPresent(delete -> {
                        if(result.get() == confirmBtn){
                            System.out.println("Member deleted");
                        }
                    });
                }
            });
        }
    }
}
