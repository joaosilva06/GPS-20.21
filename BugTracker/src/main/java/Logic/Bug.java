package Logic;

public class Bug {
    private int id;
    private String title;
    private String creator;
    private String type;
    private String status;
    private String priority;
    private String solving;
    private String desc;
    private String module;
    private String project;

    public Bug(int id, String creator, String type, String status, String priority, String desc, String module, String title, String project) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.desc = desc;
        this.module = module;
        this.project = project;
    }

    public Bug(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String module) {
        this.project = project;
    }
}
