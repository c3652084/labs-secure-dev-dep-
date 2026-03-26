package com.example.hospitalapp.ehr;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EhrDao {
    @Insert
    void insertEhr(EhrRecord ehrRecord);

    @Query("SELECT * FROM ehr_records WHERE patientName = :patientName")
    List<EhrRecord> getByPatientName(String patientName);
}
