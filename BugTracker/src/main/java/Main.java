import GUI.BugTrackerUI;
import Logic.Observables.UIObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //System.out.println(Main.class.getClassLoader().getResource("logging.properties"));
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
