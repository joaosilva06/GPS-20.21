/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Observables;

/**
 *
 * @author joao_
 */
public enum PropsID {
    CHANGE_SCREEN("op_screen"),
    USER_REG_SUCCESS("user_reg_success"),
    USER_REG_FAIL("user_reg_fail"),
    LOGIN_FAIL("login_fail"),
    REQUEST_FAIL("request_fail"),
    PROJECT_ADDED("project_added"),
    ERROR_EDIT_USER("error_edit_user"),
    GET_PROJECTS("get_projects");
    
    String valor;

    PropsID(String s) {
        valor = s;
    }

    @Override
    public String toString() {
        return valor;
    }
}
