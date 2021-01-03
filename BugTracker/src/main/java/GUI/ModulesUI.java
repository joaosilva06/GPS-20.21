package GUI;


import Logic.Module;
import Logic.Observables.UIObservable;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

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
            lbAdd.setCursor(Cursor.HAND);
            Label lbDel = new Label("x");
            lbDel.setCursor(Cursor.HAND);
            lbDel.getStyleClass().add("delBtn");
            this.setSpacing(5);
            this.getChildren().addAll(lbAdd,lbDel);

            lbAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dialog dialog = new Dialog();
                    dialog.getDialogPane().setStyle("-fx-pref-height:200px");
                    dialog.setTitle("New Module");
                    dialog.setHeaderText("Enter module name");

                    // Set the button types.
                    ButtonType confirmBtn = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(confirmBtn, ButtonType.CANCEL);

                    // Create the username and password labels and fields.
                    BorderPane pane = new BorderPane();
                    pane.setPadding(new Insets(20, 20, 20, 20));

                    TextField moduleName = new TextField();
                    moduleName.setPromptText("Module Name");
                    moduleName.setPrefSize(300, 40);
                    moduleName.getStyleClass().add("inputsDialogs");
                    pane.setCenter(moduleName);

                    // Enable/Disable login button depending on whether a username was entered.
                    Node confirm = dialog.getDialogPane().lookupButton(confirmBtn);
                    confirm.setDisable(true);

                    // Do some validation (using the Java 8 lambda syntax).
                    moduleName.textProperty().addListener((observable, oldValue, newValue) -> {
                        confirm.setDisable(newValue.trim().isEmpty());
                    });

                    dialog.getDialogPane().setContent(pane);

                    Optional result = dialog.showAndWait();

                    result.ifPresent(module -> {
                        if(result.get() == confirmBtn){
                            System.out.println("Module name: "+moduleName.getText());
                        }
                    });
                }
            });

            lbDel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dialog dialog = new Dialog();
                    dialog.getDialogPane().setStyle("-fx-pref-height:100px");
                    dialog.setTitle("Delete Module");
                    dialog.setHeaderText(null);

                    // Set the button types.
                    ButtonType confirmBtn = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(confirmBtn, ButtonType.CANCEL);

                    // Create the username and password labels and fields.
                    BorderPane pane = new BorderPane();
                    pane.setPadding(new Insets(20, 20, 20, 20));

                    Label lbQuestion = new Label("Sure you want to delete this module?");
                    pane.setCenter(lbQuestion);

                    dialog.getDialogPane().setContent(pane);

                    Optional result = dialog.showAndWait();

                    result.ifPresent(delete -> {
                        if(result.get() == confirmBtn){
                            System.out.println("Module deleted");
                        }
                    });
                }
            });
        }
    }
}
