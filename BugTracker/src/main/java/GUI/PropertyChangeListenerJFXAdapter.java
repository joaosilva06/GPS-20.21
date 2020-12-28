/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author joao_
 */
public abstract class PropertyChangeListenerJFXAdapter implements PropertyChangeListener{
    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(()->{
            onChange(evt);
        });
    }

    abstract public void onChange(PropertyChangeEvent evt);
}