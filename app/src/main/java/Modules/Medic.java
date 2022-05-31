package Modules;

public class Medic {
    private String name ;
    private String medic_uses_count;

    public Medic() {
    }

    public Medic(String name, String medic_uses_count) {
        this.name = name;
        this.medic_uses_count = medic_uses_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedic_uses_count() {
        return medic_uses_count;
    }

    public void setMedic_uses_count(String medic_uses_count) {
        this.medic_uses_count = medic_uses_count;
    }
}
