/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugtracker.main;

import bugtracker.gui.BugTrackerUI;
import bugtracker.logic.UIObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joao_
 */
public class BugTracker extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        UIObservable observable = new UIObservable();
        BugTrackerUI ui= new BugTrackerUI(observable);
        Scene scene = new Scene(ui, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("BugTracker");
        stage.setMinHeight(650);
        stage.setMinWidth(350);
        stage.show();
    }
}
