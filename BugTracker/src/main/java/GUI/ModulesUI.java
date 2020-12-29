package GUI;


import Logic.Module;
import Logic.Observables.UIObservable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.Optional;

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

        TableColumn<Module, String> moduleName = new TableColumn<>("MODULE");
        moduleName.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        moduleName.getStyleClass().add("column");

        TableColumn<Module, String> creationDate = new TableColumn<>("CREATION");
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        creationDate.getStyleClass().add("column");

        TableColumn<Module, String> numBugs = new TableColumn<>("# BUGS");
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
            this.setSpacing(5);
            this.getChildren().addAll(lbAdd,lbDel);

            lbAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // create a text input dialog
                    TextInputDialog td = new TextInputDialog("Module Name");
                    td.setHeaderText(null);
                    td.setResizable(true);
                    td.setTitle("New Module");
                    Optional<String> result = td.showAndWait();
                    if (result.isPresent()){
                        System.out.println("Module: " + result.get());
                    }
                }
            });
        }
    }
}
