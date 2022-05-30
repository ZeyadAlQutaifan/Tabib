package Modules;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Record {
    private String imageUri  , type , date, additional_notes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Record() {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date= dateObj.format(formatter);
    }

    public Record(String imageUri, String type, String date, String additional_notes) {
        this.imageUri = imageUri;
        this.type = type;
        this.date = date;
        this.additional_notes = additional_notes;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdditional_notes() {
        return additional_notes;
    }

    public void setAdditional_notes(String additional_notes) {
        this.additional_notes = additional_notes;
    }
}
