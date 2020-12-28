package GUI;

import Logic.Observables.UIObservable;
import javafx.scene.layout.BorderPane;

public class BugUI extends BorderPane {
    UIObservable observable;

    public BugUI(UIObservable obs){
        this.observable = obs;
    }
}
