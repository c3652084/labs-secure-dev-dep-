package com.example.hospitalapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientDao {
    @Insert
    void insertPatient(Patient patient);

    @Query("SELECT * FROM patients ORDER BY id DESC")
    List<Patient> getAllPatients();

    @Query("SELECT * FROM patients WHERE nhsNumber = :nhsNumber LIMIT 1")
    Patient getPatientByNhsNumber(String nhsNumber);
}
