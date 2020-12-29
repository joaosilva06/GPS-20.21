package Logic;

public class Module {
    private String name;
    private int id;

    public Module(String name, int id){
        this.name = name;
        this.id = id;
    }

    public Module(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
