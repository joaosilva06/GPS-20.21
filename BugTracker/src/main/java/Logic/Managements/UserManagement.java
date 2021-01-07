package Logic.Managements;

import Logic.API_Requests.ProjectRequests;
import Logic.API_Requests.UserRequests;
import Logic.Exceptions.APIResponseException;
import Logic.User;

import java.io.IOException;

public class UserManagement {
    private User usr;

    public UserManagement(User usr){
        this.usr = usr;
    }

    public UserManagement(){usr = null}

    public User getUsr(){
        return this.usr;
    }

    public void registar(String username, String password, String email) throws IOException {
        try {
        User u = UserRequests.registar(username, password, email);
        if(u != null){
            this.usr = u; //isto provavelmente deve ser alterado, o user nao fica loggado com o register
        }
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    public void login(String username, String password) throws IOException {
        try {
            User u = UserRequests.login(username, password);
            if(u != null){
                this.usr = u;
            }
        }catch (IOException e){
           throw new IOException(e.getMessage());
        }
    }

    public boolean logoff() throws IOException {
        try {
            boolean suc = UserRequests.logoff();
            if(suc){
                this.usr = null;
                return true;
            }

        }catch (IOException e){
            throw new IOException(e.getMessage());
        }
        return false;
    }

    public boolean resetMail(String email) throws IOException {
        try {
            boolean suc = UserRequests.resetMail(email);
            if(suc){
                this.usr.setEmail(email);
                return true;
            }
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }
        return false;
    }

    public boolean rename(String newName) throws Exception {
        try {
            String res = UserRequests.rename(newName,this.usr.getId());
            if(res.equals(newName)){
                this.usr.setUsername(newName);
                return true;
            }
        }catch (IOException | APIResponseException e){
            throw new Exception(e.getMessage());
        }
        return false;
    }

    public boolean repass(String newPass) throws Exception {
        try {
            boolean res = UserRequests.repass(newPass,this.usr.getId());
            if(res){
                this.usr.setPassword(newPass);
                return true;
            }
        }catch (IOException | APIResponseException e){
            throw new Exception(e.getMessage());
        }
        return false;
    }


}
