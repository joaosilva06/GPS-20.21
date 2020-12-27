package Logic;

public class Bug {
    private int id;
    private String creator;
    private String type;
    private String status;
    private String priority;
    private String solving;
    private String desc;

    public Bug(int id, String creator, String type, String status, String priority, String desc) {
        this.id = id;
        this.creator = creator;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSolving() {
        return solving;
    }

    public void setSolving(String solving) {
        this.solving = solving;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
