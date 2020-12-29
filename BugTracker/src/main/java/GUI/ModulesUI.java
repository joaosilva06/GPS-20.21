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

        this.getStyleClass().add("contentPanel");

        Options opt = new Options();
        opt.setAlignment(Pos.TOP_RIGHT);
        setTop(opt);

        TableView modulesTable = new TableView();

        modulesTable.getStyleClass().add("table");

        TableColumn<Bug, String> moduleName = new TableColumn<>("MODULE");
        moduleName.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        moduleName.getStyleClass().add("column");

        TableColumn<Bug, String> creationDate = new TableColumn<>("CREATION");
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        creationDate.getStyleClass().add("column");

        TableColumn<Bug, String> numBugs = new TableColumn<>("# BUGS");
        numBugs.setCellValueFactory(new PropertyValueFactory<>("numBugs"));
        numBugs.getStyleClass().add("lastColumn");


        modulesTable.getColumns().addAll(moduleName,creationDate,numBugs);

        modulesTable.setColumnResizePolicy(modulesTable.CONSTRAINED_RESIZE_POLICY);

        modulesTable.setPlaceholder(new Label("No modules to display"));

        setCenter(modulesTable);

    }

    class Options extends HBox {
        public Options(){
            Label lbAdd = new Label("+");
            lbAdd.getStyleClass().add("addBtn");
            Label lbDel = new Label("x");
            lbDel.getStyleClass().add("delBtn");
            this.getChildren().addAll(lbAdd,lbDel);
        }
    }
}
