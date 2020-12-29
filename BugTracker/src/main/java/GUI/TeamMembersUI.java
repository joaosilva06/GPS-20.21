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
        }
    }
}
