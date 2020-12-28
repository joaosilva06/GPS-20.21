package GUI;

import Logic.Bug;
import Logic.Module;
import Logic.Observables.UIObservable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;


public class BugUI extends BorderPane {
    UIObservable observable;

    public BugUI(UIObservable obs){

        this.observable = obs;

        this.setStyle("-fx-padding:50 50 50 50");

        Options opt = new Options();
        opt.setAlignment(Pos.TOP_RIGHT);
        setTop(opt);

        TableView bugsTable = new TableView();

        TableColumn<Bug, String> bugName = new TableColumn<>("BUG");
        bugName.setCellValueFactory(new PropertyValueFactory<>("bugName"));

        TableColumn<Bug, String> creatorName = new TableColumn<>("CREATOR");
        creatorName.setCellValueFactory(new PropertyValueFactory<>("creatorName"));

        TableColumn<Bug, String> status = new TableColumn<>("STATUS");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Bug, String> type = new TableColumn<>("TYPE");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Module, String> moduleName = new TableColumn<>("MODULE");
        moduleName.setCellValueFactory(new PropertyValueFactory<>("moduleName"));

        TableColumn<Bug, String> priority = new TableColumn<>("PRIORITY");
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        bugsTable.getColumns().addAll(bugName,creatorName,status,type,moduleName,priority);

        bugsTable.setColumnResizePolicy(bugsTable.CONSTRAINED_RESIZE_POLICY);

        bugsTable.setPlaceholder(new Label("No bugs to display"));

        setCenter(bugsTable);

    }

    class Options extends HBox{
        public Options(){
            Label lbAdd = new Label("Add");
            Label lbDel = new Label("Del");
            this.getChildren().addAll(lbAdd,lbDel);
        }
    }
}
