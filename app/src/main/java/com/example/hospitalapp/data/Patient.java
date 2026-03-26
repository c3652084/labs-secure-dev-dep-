package com.example.hospitalapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patients")
public class Patient {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nhsNumber;
    private String fullName;
    private String dateOfBirth;
    private String contactDetails;

    public Patient(String nhsNumber, String fullName, String dateOfBirth, String contactDetails) {
        this.nhsNumber = nhsNumber;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactDetails = contactDetails;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNhsNumber() { return nhsNumber; }
    public String getFullName() { return fullName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getContactDetails() { return contactDetails; }
}
