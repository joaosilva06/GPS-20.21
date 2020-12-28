package GUI;

import Logic.Bug;
import Logic.Observables.UIObservable;
import Logic.User;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TeamMembersUI extends BorderPane{
    UIObservable observable;

    public TeamMembersUI(UIObservable obs){

        this.observable = obs;

        this.setStyle("-fx-padding:50 50 50 50;-fx-background-color:white");

        Options opt = new Options();
        opt.setAlignment(Pos.TOP_RIGHT);
        setTop(opt);

        TableView membersTable = new TableView();

        membersTable.setStyle("-fx-border-color: white");

        TableColumn<User, String> memberName = new TableColumn<>("MEMBER");
        memberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        memberName.setStyle("-fx-background-color:white;-fx-pref-height:50px;-fx-border-color:#c8c6c6;-fx-border-width:0 1 1 0");

        TableColumn<User, String> addDate = new TableColumn<>("ADDED ON");
        addDate.setCellValueFactory(new PropertyValueFactory<>("addDate"));
        addDate.setStyle("-fx-background-color:white;-fx-border-color:#c8c6c6;-fx-border-width:0 1 1 0");

        TableColumn<User, String> role = new TableColumn<>("ROLE");
        role.setCellValueFactory(new PropertyValueFactory<>("numBugs"));
        role.setStyle("-fx-background-color:white;-fx-border-color:#c8c6c6;-fx-border-width:0 0 1 0");


        membersTable.getColumns().addAll(memberName,addDate,role);

        membersTable.setColumnResizePolicy(membersTable.CONSTRAINED_RESIZE_POLICY);

        membersTable.setPlaceholder(new Label("No team members to display"));

        setCenter(membersTable);

    }

    class Options extends HBox {
        public Options(){
            Label lbAdd = new Label("Add");
            Label lbDel = new Label("Del");
            this.getChildren().addAll(lbAdd,lbDel);
        }
    }
}
