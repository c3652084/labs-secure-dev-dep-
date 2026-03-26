package com.example.hospitalapp.ehr;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ehr_records")
public class EhrRecord {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String patientName;
    public String allergies;
    public String heartRate;
    public String temperature;
    public String glucose;

    public EhrRecord(String patientName, String allergies, String heartRate, String temperature, String glucose) {
        this.patientName = patientName;
        this.allergies = allergies;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.glucose = glucose;
    }
}
