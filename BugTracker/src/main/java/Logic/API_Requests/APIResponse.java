package Logic.API_Requests;

public class APIResponse {
    private boolean error;
    private Object msg;

    public APIResponse(boolean error, Object msg){
        this.error = error;
        this.msg = msg;
    }

    public boolean hasError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
