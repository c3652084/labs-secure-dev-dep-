package com.example.hospitalapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppointmentDao {
    @Insert
    void insertAppointment(Appointment appointment);

    @Query("SELECT * FROM appointments WHERE date = :date")
    List<Appointment> getAppointmentsByDate(String date);
}
