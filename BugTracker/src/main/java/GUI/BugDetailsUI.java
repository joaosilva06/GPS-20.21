package GUI;

import Logic.Observables.PropsID;
import Logic.Observables.Screens;
import Logic.Observables.UIObservable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;

public class BugDetailsUI extends BorderPane {
    UIObservable observable;
    public BugDetailsUI(UIObservable obs){
        this.observable = obs;

        this.setStyle("-fx-padding:10 10 10 10");

        BugNameArea nameArea = new BugNameArea(observable);
        setTop(nameArea);

        BugInfo info = new BugInfo();
        setCenter(info);

        observable.registaPropertyChangeListener(PropsID.CHANGE_SCREEN, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                setVisible(observable.getActualScreen() == Screens.OPERATIONS && observable.getActualSubScreen() == Screens.BUG_DETAILS);
            }
        });
    }

    class BugNameArea extends HBox{
        UIObservable observable;
        public BugNameArea(UIObservable obs){
            this.observable = obs;

            Label lbBack = new Label("<");
            Label lbDash = new Label("Bug Name");

            lbBack.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-padding:10 0 10 10");
            lbDash.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-padding:10 0 10 10");

            this.setSpacing(5);

            this.getChildren().addAll(lbBack,lbDash);

            lbBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    observable.project();
                }
            });
        }
    }

    class BugInfo extends VBox{
        public BugInfo(){
            this.setStyle("-fx-padding:50 50 50 50");

            JoinLeftRightInfo info = new JoinLeftRightInfo();
            DescriptionArea descArea = new DescriptionArea();

            this.setSpacing(30);
            this.getChildren().addAll(info,descArea);
        }
    }

    class JoinLeftRightInfo extends HBox{
        public JoinLeftRightInfo(){
            LeftInfo leftInfo = new LeftInfo();
            RightInfo rightInfo = new RightInfo();
            this.setSpacing(500);
            this.getChildren().addAll(leftInfo,rightInfo);
        }
    }

    class LeftInfo extends VBox{

        String createdBy = "Created by: ", createdOn = "Created on: ", module="Module: ", type="Type: ";
        Label lbCreatedBy, lbCreatedOn, lbModule, lbType;

        public LeftInfo(){
            lbCreatedBy = new Label(createdBy);
            lbCreatedOn = new Label(createdOn);
            lbModule = new Label(module);
            lbType = new Label(type);
            lbCreatedBy.getStyleClass().add("labelBugDetails");
            lbCreatedOn.getStyleClass().add("labelBugDetails");
            lbModule.getStyleClass().add("labelBugDetails");
            lbType.getStyleClass().add("labelBugDetails");
            this.setSpacing(10);

            this.getChildren().addAll(lbCreatedBy,lbCreatedOn,lbModule,lbType);
        }
    }

    class RightInfo extends VBox{
        String status="Status: ", priority="Priority: ", solving="Solving: ";
        Label lbStatus, lbPriority, lbSolving;

        public RightInfo(){
            lbStatus = new Label(status);
            lbPriority = new Label(priority);
            lbSolving = new Label(solving);
            lbStatus.getStyleClass().add("labelBugDetails");
            lbPriority.getStyleClass().add("labelBugDetails");
            lbSolving.getStyleClass().add("labelBugDetails");
            this.setSpacing(10);

            this.getChildren().addAll(lbStatus,lbPriority,lbSolving);
        }
    }

    class DescriptionArea extends VBox{
        public DescriptionArea(){
            Label lbDescriptionSpan = new Label("Description");
            lbDescriptionSpan.setAlignment(Pos.CENTER_LEFT);
            lbDescriptionSpan.getStyleClass().add("labelBugDetails");

            TextArea descArea = new TextArea();

            this.setSpacing(10);
            this.getChildren().addAll(lbDescriptionSpan,descArea);
        }
    }
}
