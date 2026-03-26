package com.example.hospitalapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hospitalapp.ehr.EhrDao;
import com.example.hospitalapp.ehr.EhrRecord;

@Database(entities = {Patient.class, Appointment.class, EhrRecord.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PatientDao patientDao();
    public abstract AppointmentDao appointmentDao();
    public abstract EhrDao ehrDao();
}
