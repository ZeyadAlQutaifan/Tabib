package Modules;

public class Patient {
    private String name , phone , email , ssn , gender , birth_date ,  blood_type ;

    public Patient() {
    }

    public Patient(String name, String phone, String email, String ssn, String gender, String birth_date, String blood_type) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.ssn = ssn;
        this.gender = gender;
        this.birth_date = birth_date;
        this.blood_type = blood_type;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }
}
