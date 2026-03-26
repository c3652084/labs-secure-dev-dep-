package com.example.hospitalapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointments")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String patientName;
    public String clinic;
    public String date;
    public String time;

    public Appointment(String patientName, String clinic, String date, String time) {
        this.patientName = patientName;
        this.clinic = clinic;
        this.date = date;
        this.time = time;
    }
}
