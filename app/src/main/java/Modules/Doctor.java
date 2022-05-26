package Modules;

public class Doctor {
    private String name , phone , email , ssn , gender , specialization  , license_image ;


    public Doctor() {
    }

    public Doctor(String name, String phone, String email, String ssn, String gender, String specialization, String license_image) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.ssn = ssn;
        this.gender = gender;
        this.specialization = specialization;
        this.license_image = license_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicense_image() {
        return license_image;
    }

    public void setLicense_image(String license_image) {
        this.license_image = license_image;
    }
    
}
