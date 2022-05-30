package Modules;

public class Medic {
    private String name ;
    private String pills_per_day;

    public Medic() {
    }

    public Medic(String name, String pills_per_day) {
        this.name = name;
        this.pills_per_day = pills_per_day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPills_per_day() {
        return pills_per_day;
    }

    public void setPills_per_day(String pills_per_day) {
        this.pills_per_day = pills_per_day;
    }
}
