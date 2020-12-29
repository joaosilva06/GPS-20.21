package GUI;

import Logic.Bug;
import Logic.Module;
import Logic.Observables.UIObservable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class BugUI extends BorderPane {
    UIObservable observable;

    public BugUI(UIObservable obs){

        this.observable = obs;

        this.getStyleClass().add("contentPanel");

        Options opt = new Options();
        opt.setAlignment(Pos.TOP_RIGHT);
        setTop(opt);

        TableView bugsTable = new TableView();

        bugsTable.getStyleClass().add("table");

        TableColumn<Bug, String> bugName = new TableColumn<>("BUG");
        bugName.setCellValueFactory(new PropertyValueFactory<>("bugName"));
        bugName.getStyleClass().add("column");

        TableColumn<Bug, String> creatorName = new TableColumn<>("CREATOR");
        creatorName.setCellValueFactory(new PropertyValueFactory<>("creatorName"));
        creatorName.getStyleClass().add("column");

        TableColumn<Bug, String> status = new TableColumn<>("STATUS");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        status.getStyleClass().add("column");

        TableColumn<Bug, String> type = new TableColumn<>("TYPE");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.getStyleClass().add("column");

        TableColumn<Module, String> moduleName = new TableColumn<>("MODULE");
        moduleName.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        moduleName.getStyleClass().add("column");

        TableColumn<Bug, String> priority = new TableColumn<>("PRIORITY");
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        priority.getStyleClass().add("lastColumn");

        bugsTable.getColumns().addAll(bugName,creatorName,status,type,moduleName,priority);

        bugsTable.setColumnResizePolicy(bugsTable.CONSTRAINED_RESIZE_POLICY);

        bugsTable.setPlaceholder(new Label("No bugs to display"));

        setCenter(bugsTable);

    }

    class Options extends HBox{
        public Options(){
            Label lbAdd = new Label("+");
            lbAdd.getStyleClass().add("addBtn");
            Label lbDel = new Label("x");
            lbDel.getStyleClass().add("delBtn");
            this.getChildren().addAll(lbAdd,lbDel);
        }
    }
}
