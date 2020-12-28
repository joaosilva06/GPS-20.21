/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugtracker.logic;

/**
 *
 * @author joao_
 */
public enum PropsID {
    CHANGE_SCREEN("op_screen");
    
    String valor;

    PropsID(String s) {
        valor = s;
    }

    @Override
    public String toString() {
        return valor;
    }
}
