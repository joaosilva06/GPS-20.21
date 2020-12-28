package GUI;

import Logic.Bug;
import Logic.Module;
import Logic.Observables.UIObservable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.nio.charset.StandardCharsets;

public class ModulesUI extends BorderPane{
    UIObservable observable;

    public ModulesUI(UIObservable obs){

        this.observable = obs;

        this.setStyle("-fx-padding:50 50 50 50;-fx-background-color:white");

        Options opt = new Options();
        opt.setAlignment(Pos.TOP_RIGHT);
        setTop(opt);

        TableView modulesTable = new TableView();

        modulesTable.setStyle("-fx-border-color: white");

        TableColumn<Bug, String> moduleName = new TableColumn<>("MODULE");
        moduleName.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        moduleName.setStyle("-fx-background-color:white;-fx-pref-height:50px;-fx-border-color:#c8c6c6;-fx-border-width:0 1 1 0");

        TableColumn<Bug, String> creationDate = new TableColumn<>("CREATION");
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        creationDate.setStyle("-fx-background-color:white;-fx-border-color:#c8c6c6;-fx-border-width:0 1 1 0");

        TableColumn<Bug, String> numBugs = new TableColumn<>("# BUGS");
        numBugs.setCellValueFactory(new PropertyValueFactory<>("numBugs"));
        numBugs.setStyle("-fx-background-color:white;-fx-border-color:#c8c6c6;-fx-border-width:0 0 1 0");


        modulesTable.getColumns().addAll(moduleName,creationDate,numBugs);

        modulesTable.setColumnResizePolicy(modulesTable.CONSTRAINED_RESIZE_POLICY);

        modulesTable.setPlaceholder(new Label("No modules to display"));

        setCenter(modulesTable);

    }

    class Options extends HBox {
        public Options(){
            Label lbAdd = new Label("Add");
            Label lbDel = new Label("Del");
            this.getChildren().addAll(lbAdd,lbDel);
        }
    }
}
